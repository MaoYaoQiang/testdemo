package com.example.testdemo.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.*;


public class XML2config {

    public static String jsonToXml(JSONObject jsonObject) {
        synchronized (XML2config.class) {
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("<?xml version='1.0' encoding='UTF-8' ?>");
            strBuilder.append("\n");
            jsonToXmlStr(jsonObject,strBuilder);
            return strBuilder.toString();
        }
    }
    private static String jsonToXmlStr(JSONObject jsonObject,StringBuilder buffer ){
        Set objSet = jsonObject.entrySet();
        for (Iterator it = objSet.iterator(); it.hasNext();) {
            Map.Entry next = (Map.Entry) it.next();
            if(next.getValue() instanceof JSONObject || next.getValue() instanceof LinkedHashMap ||
                    next.getValue() instanceof Map){
                buffer.append("");
                JSONObject jo = jsonObject.getJSONObject((String) next.getKey());
                buffer.append("<"+next.getKey()+">");
                buffer.append("\n");
                jsonToXmlStr(jo,buffer);
                buffer.append("</"+next.getKey()+">");
            }else if(next.getValue() instanceof JSONArray || next.getValue() instanceof ArrayList){
                JSONArray jsonArray = jsonObject.getJSONArray((String) next.getKey());
                if(jsonArray.size()>0){
                    for (int i = 0;i<jsonArray.size();i++){
                        buffer.append("");
                        Object o = jsonArray.get(i);
                        if(o instanceof JSONObject || o instanceof LinkedHashMap ||
                                o instanceof Map){
                            JSONObject jo = jsonArray.getJSONObject(i);
                            if(jo.containsKey("itemName")){
                                if(i==0){
                                    buffer.append("<"+next.getKey()+">");
                                    buffer.append("\n");
                                }
                                buffer.append("<"+jo.getString("itemName")+">");
                                buffer.append("\n");
                            }else{
                                buffer.append("<"+next.getKey()+">");
                                buffer.append("\n");
                            }
                            jsonToXmlStr(jo,buffer);
                            if(jo.containsKey("itemName")){
                                buffer.append("</"+jo.getString("itemName")+">");
                                buffer.append("\n");
                                if(i==jsonArray.size()-1){
                                    buffer.append("</"+next.getKey()+">");
                                    buffer.append("\n");
                                }
                            }else{
                                buffer.append("</"+next.getKey()+">");
                                buffer.append("\n");
                            }
                        }else {
                            buffer.append("<"+next.getKey()+">");
                            buffer.append(o);
                            buffer.append("</"+next.getKey()+">");
                            buffer.append("\n");
                        }

                    }
                }
            }else{
                if(!"itemName".equals(next.getKey())){
                    buffer.append("<"+next.getKey()+">");
                    if(next.getValue()!=null){
                        if(next.getValue() instanceof String){
                            buffer.append(StringEscapeUtils.escapeXml10((String) next.getValue()));
                        }else{
                            buffer.append(next.getValue());
                        }

                    }
                    buffer.append("</"+next.getKey()+">");
                    buffer.append("\n");
                }
            }
        }
        buffer.append("");
        return buffer.toString();
    }

    public static JSONObject xmlToJSON(String str){
        Document document = strToXml(str);
        //获取根节点 并遍历根节点
        StringBuffer jsonStr = new StringBuffer("{");
        //获取根节点
        Element root = document.getDocumentElement();
        child(root,jsonStr,true);
        jsonStr.append("}");
        JSONObject jsonObject = JSONObject.parseObject(jsonStr.toString());
        return jsonObject;
    }
    private static Document strToXml(String str){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(str));
            Document parse = db.parse(is);
            return parse;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 有效节点 元素节点 1 【文本节点 3且 其父节点的子节点为1】
     * 带属性节点 属性和子节点在
     * 不带属性节点
     * 父节点已经处理好 可以不用管
     */
    private static void child(Element element,StringBuffer json,boolean bool){
        String leaf = leaf(element);
        if(leaf == null){
            if(bool){
                json.append("\"").append(element.getNodeName()).append("\":{");
            }else {
                json.append("{");
            }
            if(element.hasAttributes()){
                json.append(attribute(element)).append(",");
            }
            Map<String, List<Node>> map = tree(element);
            Object[] nodeNames = map.keySet().toArray();
            int length = nodeNames.length;
            int i = 0;
            for(;i < length-1;i++){
                List<Node> nodes = map.get(nodeNames[i]);
                int nodesSize = nodes.size();
                if(nodesSize > 1){
                    json.append("\"").append(nodeNames[i]).append("\":[");
                    int j = 0;
                    for(; j < nodesSize-1;j++){
                        child((Element) nodes.get(j),json,false);
                        json.append(",");
                    }
                    child((Element) nodes.get(j),json,false);
                    json.append("],");
                }else {

                    child((Element) nodes.get(0),json,true);
                    json.append(",");


                }
            };
            if(length>0){
                List<Node> nodes = map.get(nodeNames[i]);
                int nodesSize = nodes.size();
                if(nodesSize > 1){
                    json.append("\"").append(nodeNames[i]).append("\":[");
                    int j = 0;
                    for(; j < nodesSize-1;j++){
                        child((Element) nodes.get(j),json,false);
                        json.append(",");
                    }
                    child((Element) nodes.get(j),json,false);
                    json.append("]");
                }else {
                    child((Element) nodes.get(0),json,true);
                }
            }

            json.append("}");
        }else {
            if(bool){
                json.append("\"").append(element.getNodeName()).append("\":").append(leaf);
            }else {
                json.append(leaf);
            }
        }
    }
    /**
     * 筛选非叶子节点的元素节点
     */
    private static Map<String, List<Node>> tree(Element element){
        Map<String,List<Node>> map = new HashMap<>();
        NodeList childNodes = element.getChildNodes();
        int size = childNodes.getLength();
        for(int i = 0;i < size; i++){
            Node node = childNodes.item(i);
            if(node.getNodeType() == 1){
                List<Node> nodes = map.get(node.getNodeName());
                if(nodes == null){
                    nodes = new ArrayList<>();
                    map.put(node.getNodeName(),nodes);
                }
                nodes.add(node);
            }
        }
        return map;
    }
    /**
     * 判断是否是叶子节点
     * 只有一个子节点且子节点为文本节点并且不具备属性节点且文本节点内容不为空
     * 节点名称:文本节点内容
     * 只有一个子节点且子节点为文本节点并且不具备属性节点且文本节点内容为空
     * 直接舍弃
     * 有属性节点且文本节点内容为空
     * 节点名称:{属性节点名称:属性值}
     * 有属性节点且文本节点内容不为空
     * 节点名称:{属性节点名称:属性值,"#text":文本节点内容}
     */
    private static String leaf(Element element){
        //获取子节点
        NodeList childNodes = element.getChildNodes();
        Node node = null;
        //判断是否是叶子节点
        if(element.hasChildNodes()&&childNodes.getLength()==1&&(node=childNodes.item(0)).getNodeType()==3){
            String textContent = node.getTextContent().trim();
            NamedNodeMap attributes = element.getAttributes();
            int length = textContent.length();
            int size = attributes.getLength();
            if(length > 0){
                if(size > 0){
                    return new StringBuffer("{").append(attribute(element)).
                            append(",\"#text\":\"").append(textContent).append("\"}").toString();
                }else {
                    if(StringUtils.hasText(textContent)){
                        return new StringBuffer("\"").append(textContent).append("\"").toString();
                    }else{
                        return new StringBuffer("\"").append("\"").toString();
                    }

                }
            }else {
                if(size > 0){
                    return new StringBuffer("{").append(attribute(element)).append("}").toString();
                }else {
                    return "\"\"";
                }
            }
        }else if(!element.hasChildNodes()&&element.hasAttributes()) {
            return new StringBuffer("{").append(attribute(element)).append("}").toString();
        }
        if(element.getFirstChild()==null){
            return new StringBuffer("\"").append("\"").toString();
        }
        //非叶子节点
        return null;
    }

    /**
     * 获取节点的属性
     * @param element
     * @return
     */
    private static String attribute(Element element){
        NamedNodeMap attributes = element.getAttributes();
        int size = attributes.getLength();
        StringBuffer sb = new StringBuffer();
        int i = 0;
        Node node;
        for(;i < size -1; i++){
            node = attributes.item(i);
            sb.append("\"-").append(node.getNodeName()).append("\":\"").
                    append(node.getNodeValue()).append("\",");
        }
        node = attributes.item(i);
        return sb.append("\"-").append(node.getNodeName()).append("\":\"").
                append(node.getNodeValue()).append("\"").toString();
    }

    public static String map2Xml(Map<String, Object> paramMap) {
        synchronized (XML2config.class) {
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("<?xml version='1.0' encoding='UTF-8' ?>");
            strBuilder.append("");
            Set<String> objSet = paramMap.keySet();
            for (String key : objSet) {
                if (key == null) {
                    continue;
                }
                strBuilder.append("\n");
                strBuilder.append("<").append(key).append(">");
                Object value = paramMap.get(key);
                strBuilder.append(convert(value));
                strBuilder.append("</").append(key).append(">");
            }
            strBuilder.append("");

            return strBuilder.toString();
        }
    }

    public static String convert(Map map) {
        StringBuilder strBuilder = new StringBuilder();
        for (Object o : map.keySet()) {
            strBuilder.append("<").append(o).append(">");
            strBuilder.append(convert(map.get(o)));
            strBuilder.append("</").append(o).append(">\n");
        }
        return strBuilder.toString();
    }

    public static String convert(Collection<?> objects) {
        StringBuilder strBuilder = new StringBuilder("\n");
        for (Object obj : objects) {
            //类名统一用med代替
            strBuilder.append("<").append("med").append(">");
//            strBuilder.append("<").append(obj).append(">");
            strBuilder.append(convert(obj));
//            strBuilder.append("</").append(obj).append(">\n");
            strBuilder.append("</").append("med").append(">\n");
        }
        return strBuilder.toString();
    }

    /**
     * 描述：递归进行转换
     * Created by zjw on 2018-12-11 11:21:37
     *
     * @param object
     * @return String
     */
    public static String convert(Object object) {
        if (object instanceof Map) {
            return convert((Map)object);
        }
        if (object instanceof Collection) {
            return convert((Collection<?>) object);
        }
        StringBuilder strBuilder = new StringBuilder();
        if (isObject(object)) {
            Class<?> clz = object.getClass();
            Field[] fields = clz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value;
                try {
                    value = field.get(object);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    continue;
                }
                //删除右边"
//                strBuilder.append("<").append(fieldName).append("\">");
                strBuilder.append("<").append(fieldName).append(">");
                if (isObject(value)) {
                    strBuilder.append(convert(value));
                } else {
                    strBuilder.append(value==null?"/":value.toString());
                }
                strBuilder.append("</").append(fieldName).append(">");
            }
        } else if (object == null) {
            strBuilder.append("null");
        } else {
            strBuilder.append(object.toString());
        }
        return strBuilder.toString();
    }

    /**
     * 描述：判断是否是对象
     * Created by zjw on 2018-12-11 11:20:48
     *
     * @param obj
     * @return boolean
     */
    private static boolean isObject(Object obj) {
        if (obj == null) return false;
        if (obj instanceof String) return false;
        if (obj instanceof Integer) return false;
        if (obj instanceof Double) return false;
        if (obj instanceof Float) return false;
        if (obj instanceof Byte) return false;
        if (obj instanceof Long) return false;
        if (obj instanceof Character) return false;
        if (obj instanceof Short) return false;
        return !(obj instanceof Boolean);
    }
}
