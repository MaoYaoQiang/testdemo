package com.example.testdemo.util;

import com.google.common.collect.Maps;
import org.junit.platform.commons.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    private static final Pattern URL = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
    private static final Pattern BLANK = Pattern.compile("\\s*|\t|\r|\n");
    private static final Pattern MESSYCODE = Pattern.compile("\\s*|\t*|\r*|\n*");

    public StringUtil() {
    }

    /**
     * 生产UUID
     *
     * @return
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生产MDID
     *
     * @return
     */
    public static String randomMDID() {
        return "flow" + UUID.randomUUID().toString().replace("-", "").substring(4, 32);
    }

    public static String getNicknameFromEmail(String email) {
        int index = email.indexOf('@');
        if (-1 != index) {
            return email.substring(0, index);
        }
        return email;
    }

    /**
     * 从邮件地址从取得邮件服务器地址
     *
     * @param email 邮件地址
     * @return 邮件服务器地址
     */
    public static String emailServer(String email) {
        int index = email.indexOf('@');
        if (-1 != index) {
            return "http://mail." + email.substring(index + 1);
        }
        return email;
    }

    /**
     * 判断字符是否是中文
     *
     * @param c 字符
     * @return 是否是中文
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    /**
     * 判断字符串是否是乱码
     *
     * @param strName 字符串
     * @return 是否是乱码
     */
    public static boolean isMessyCode(String strName) {
        Matcher m = MESSYCODE.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = ch.length;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
            }
        }
        float result = count / chLength;
        return result > 0.0000000000001;
    }

    /**
     * 判断是否为汉字
     *
     * @param str 字符串
     * @return 是否是汉字
     */
    public static boolean isChinese(String str) {
        char[] chars = str.toCharArray();
        boolean isGBK = false;
        for (char aChar : chars) {
            if (isChinese(aChar)) {
                isGBK = true;
            }
            break;
        }
        return isGBK;
    }

    public static String read(Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            content.append(line);
            content.append("\r\n");
        }
        return content.toString();
    }

    /**
     * 自定义参数解析注：此方法暂时没有考虑具有相同名称的一组提交值的情况，如需要，请自己更改下面的代码
     *
     * @param query 查询参数
     * @return 键值对
     */
    public static Map<String, String> parseQuery(String query) {
        Map<String, String> params = Maps.newHashMap();
        if (!isBlank(query)) {
            String[] tmps = query.split("&");
            if (null != tmps && tmps.length > 0) {
                for (String tmp : tmps) {
                    if (!isBlank(tmp)) {
                        String[] keyValue = tmp.split("=");
                        params.put(keyValue[0], keyValue[1]);
                    }
                }
            }
        }
        return params;
    }

    /**
     * 删除字符串两边多余的空格
     *
     * @param str 字符串
     * @return 处理后的字符串
     */
    public static String delSpace(String str) {
        if (str == null) {
            return "";
        }
        String regStartSpace = "^[　 ]*";
        String regEndSpace = "[　 ]*$";
        return str.replaceAll(regStartSpace, "").replaceAll(regEndSpace, "");
    }

    public static String trimEx(String str) {
        if (str == null) {
            return "";
        }
        str = delSpace(str);

        return str.replaceAll("\\s{2,}", " ");
    }

    public static String domain(String str) {
        String domain = "";
        if (StringUtils.isBlank(str)) {
            return "";
        }
        Matcher matcher = URL.matcher(str);
        while (matcher.find()) {
            domain = matcher.group();
        }

        return domain;
    }

    /**
     * 子字符串方法
     *
     * @param from   原始字符串
     * @param len    需要的长度
     * @param append 添加字符串
     * @return 子字符串
     */
    public static String start(String from, int len, char append) {
        String key = "";

        if (null == from) {
            return key;
        }

        if (from.length() < len) {
            StringBuilder sb = new StringBuilder();
            sb.append(from);
            for (int i = 0; i < len - from.length(); ++i) {
                sb.append(append);
            }
            key = sb.toString();
        } else {
            key = from.substring(0, len);
        }

        return key;
    }

    /**
     * 子字符串方法
     *
     * @param from   原始字符串
     * @param len    需要的长度
     * @param append 添加字符串
     * @return 子字符串
     */
//    public static String start(String from, int len, String append) {
//        String ret;
//
//        int strLen = from.length();
//        if (strLen <= len) {
//            ret = from.substring(0, strLen);
//        } else {
//            ret = from.substring(0, len) + append;
//        }
//        if (strLen <= 6) {
//            ret = from.concat(Constants.BIGSPACE).concat(Constants.BIGSPACE).concat(Constants.BIGSPACE).concat(Constants.BIGSPACE).concat(Constants.BIGSPACE).concat(Constants.BIGSPACE).concat(Constants.BIGSPACE);
//        }
//
//        return ret;
//    }

    /**
     * 子字符串方法
     *
     * @param from 原始字符串
     * @param len  需要的长度
     * @return 子字符串
     */
//    public static String start(String from, int len) {
//        return start(from, len, "");
//    }

    /**
     * 去除空白字符
     *
     * @param str 原始字符串
     * @return 去掉后的字符串
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Matcher m = BLANK.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 从字符串中获得列表字符串
     *
     * @param content 原始内容
     * @param space   分隔字符串
     * @return 字符串列表
     */
    public static String[] list(String content, String space) {
        if (null != content) {
            return content.split(space);
        }
        return new String[]{};
    }

    /**
     * 从字符串中获得列表字符串
     *
     * @param content 原始内容
     * @return 字符串列表
     */
    public static String[] list(String content) {
        return list(content, ",");
    }

    /**
     * 高级版的toString方法
     *
     * @param obj 对象
     * @return toString返回值
     */
    public static String toString(Object obj) {
        if (null == obj) {
            return "";
        }
        return obj.toString();
    }

    /**
     * 删除字符串的HTML代码
     *
     * @param from 原始字符串
     * @return 删除HTML标签后的字符串
     */
    public static String clearHTML(String from) {
        //定义script的正则表达式
        String regExSript = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        //定义style的正则表达式
        String regExStyle = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        //定义HTML标签的正则表达式
        String regExHtml = "<[^>]+>";

        Pattern pScript = Pattern.compile(regExSript, Pattern.CASE_INSENSITIVE);
        Matcher mScript = pScript.matcher(from);
        //过滤script标签
        from = mScript.replaceAll("");

        Pattern pStyle = Pattern.compile(regExStyle, Pattern.CASE_INSENSITIVE);
        Matcher mStyle = pStyle.matcher(from);
        //过滤style标签
        from = mStyle.replaceAll("");

        Pattern pHtml = Pattern.compile(regExHtml, Pattern.CASE_INSENSITIVE);
        Matcher mHtml = pHtml.matcher(from);
        //过滤html标签
        from = mHtml.replaceAll("");

        return from.trim();
    }

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return 结果
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断所有的字符串是不是或空
     *
     * @param strs 字符串列表
     * @return 是不是有一个为空
     */
    public static boolean isAnyBlank(String... strs) {
        for (String str : strs) {
            if (isBlank(str)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断所有的字符串是不是都为空
     *
     * @param strs 字符串列表
     * @return 是不是都为空
     */
    public static boolean isAllBlank(String... strs) {
        for (String str : strs) {
            if (!isBlank(str)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isAllEndsWith(String from, String... withs) {
        for (String with : withs) {
            if (!from.endsWith(with)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isAnyEndsWith(String from, String... withs) {
        for (String with : withs) {
            if (from.endsWith(with)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 将字符转换成Ascii字符
     *
     * @param from 原始字符串
     * @return Ascii字符串
     */
    public static String toAscii(String from) {
        StringBuilder sb = new StringBuilder();
        byte[] bt = from.getBytes();
        for (int i = 0; i < bt.length; i++) {
            //1.是汉字去高位1
            //2.是英文字符补0作记录
            if (bt[i] < 0) {
                sb.append((char) (bt[i] & 0x7f));
            } else {
                sb.append((char) 0);
                sb.append((char) bt[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 从Ascii字符串解码成原始字符串
     *
     * @param from 原始字符串
     * @return 转码后的字符串
     */
    public static String fromAscii(String from) {
        byte[] btAll = from.getBytes();
        int i, l = 0, length = btAll.length, j = 0;
        for (i = 0; i < length; i++) {
            if (btAll[i] == 0) {
                l++;
            }
        }

        byte[] btTrue = new byte[length - l];
        for (i = 0; i < length; i++) {
            //1.是英文字符
            //2.是中文字符，高位补1
            if (btAll[i] == 0) {
                i++;
                btTrue[j] = btAll[i];
            } else {
                btTrue[j] = (byte) (btAll[i] | 0x80);
            }
            j++;
        }
        String tt = new String(btTrue);
        return tt;
    }

    /**
     * 间隔插入字符串
     *
     * @param from     原始字符串
     * @param insert   要插入的字符串
     * @param interval 间隔
     * @return 处理后的字符串
     */
    public static String interval(String from, String insert, int interval) {
        StringBuilder ret = new StringBuilder();
        if (isBlank(from)) {
            return ret.toString();
        }

        int len = from.length();
        for (int index = 0; index < len; index += interval) {
            int end = index + interval;
            if (index >= len) {
                index = len - 1;
            }
            if (end >= len) {
                end = len - 1;
            }
            if (end == len - 1) {
                ret.append(from.subSequence(index, end));
            } else {
                ret.append(from.subSequence(index, end));
                ret.append(insert);
            }
        }

        return ret.toString();
    }

    /**
     * 间隔插入字符串
     *
     * @param from     原始字符串
     * @param interval 间隔
     * @return 处理后的字符串
     */
    public static String interval(String from, int interval) {
        return interval(from, "<br/>", interval);
    }

    /**
     * 间隔插入字符串
     *
     * @param from   原始字符串
     * @param insert 要插入的字符串
     * @return 处理后的字符串
     */
    public static String interval(String from, String insert) {
        return interval(from, insert, 30);
    }

    /**
     * 间隔插入字符串
     *
     * @param from 原始字符串
     * @return 处理后的字符串
     */
    public static String interval(String from) {
        return interval(from, "<br/>", 30);
    }

    public static boolean check(String check, String patternString) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(check);
        return matcher.find();
    }

    public static boolean check(String check, String... patterns) {
        for (String patternString : patterns) {
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(check);
            if (matcher.find()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查字符串中是否包含另一个字符串
     *
     * @param target 要检查的目标字符串
     * @param test   要检查的字符串
     * @return 是否包含
     */
    public static boolean contains(String target, String test) {
        return contains(target, test, ",", ",");
    }

    /**
     * 检查字符串中是否包含另一个字符串
     *
     * @param target         要检查的目标字符串
     * @param test           要检查的字符串
     * @param targetInterval 目标字符串间隔字符
     * @param testInterval   要检查的字符串的间隔字符
     * @return 是否包含
     */
    public static boolean contains(String target, String test, String targetInterval, String testInterval) {
        if (isBlank(target)) {
            return false;
        }
        if (isBlank(test)) {
            return true;
        }

        String[] targetArray = target.split(targetInterval);
        if (null == targetArray || 0 == targetArray.length) {
            return false;
        }
        String[] testArray = test.split(testInterval);
        if (null == testArray || 0 == testArray.length) {
            return true;
        }

        for (String targetStr : targetArray) {
            for (String testStr : testArray) {
                if (!isBlank(targetStr) && !isBlank(testStr) && targetStr.equals(testStr)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 检查目标字符串里是不是包含所有给出的字符串
     *
     * @param content 目标字符串
     * @param checks  包含的所有字符串数组
     * @return 是否包含
     */
    public static boolean containsAll(String content, String... checks) {
        boolean contains = false;
        if (isBlank(content)) {
            return contains;
        }

        if (null == checks || 0 == checks.length) {
            contains = true;
            return contains;
        }

        for (String check : checks) {
            if (!content.contains(check)) {
                contains = false;
                break;
            }
        }

        return contains;
    }

    /**
     * 检查目标字符串里是不是包含任意给出的字符串
     *
     * @param content 目标字符串
     * @param checks  包含的任意字符串数组
     * @return 是否包含
     */
    public static boolean containsAny(String content, String... checks) {
        boolean contains = false;
        if (isBlank(content)) {
            return contains;
        }

        if (null == checks || 0 == checks.length) {
            contains = true;
            return contains;
        }

        for (String check : checks) {
            if (content.contains(check)) {
                contains = true;
                break;
            }
        }

        return contains;
    }

    /**
     * 从参数中取得字符串直到参数不为空
     *
     * @param params 参数
     * @return 最终字符串
     */
    public static String getString(String... params) {
        String ret = "";

        if (null == params || 0 == params.length) {
            return ret;
        }

        for (String param : params) {
            ret = param;
            if (!isBlank(ret)) {
                break;
            }
        }

        return ret;
    }

    /**
     * 检查字符串列表里是否包含指定的字符串组
     *
     * @param checks 要检查的字符串列表
     * @param tests  要验证的字符串组
     * @return 是否包含
     */
    public static boolean contains(List<String> checks, String... tests) {
        boolean contains = true;
        if (null == checks || checks.isEmpty()) {
            return contains;
        }

        for (String test : tests) {
            contains |= checks.contains(test);
        }

        return contains;
    }

    /**
     * 不区分大小写检查字符串列表
     *
     * @param checks 被检查的字符串列表
     * @param test   要检查的字符串
     * @return 是否在字符串列表中
     */
    public static boolean containsNCS(List<String> checks, String test) {
        boolean contains = false;
        if (null == checks || checks.isEmpty() || isBlank(test)) {
            return contains;
        }

        for (String check : checks) {
            if (check.toLowerCase().equals(test.toLowerCase())) {
                contains = true;
                break;
            }
        }

        return contains;
    }

    /**
     * 异常的堆栈字符串
     *
     * @param e 异常
     * @return 堆栈字符串
     */
    public static String stackTrace(Throwable e) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * 连接字符串
     *
     * @param strings 字符串列表
     * @param contact 分隔符
     * @return 最终字符串
     */
    public static String contact(Collection<String> strings, String contact) {
        String ret = "";

        if (null == strings || strings.isEmpty()) {
            return ret;
        }

        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(string).append(contact);
        }
        ret = sb.substring(0, sb.length() - contact.length());

        return ret;
    }

    /**
     * double类型的数，如果是整型的，要把后面的0去掉。
     *
     * @param num
     * @return
     */
    public static String double2Trans(double num) {
        if (Math.round(num) - num == 0) {
            return String.valueOf((long) num);
        }
        return String.valueOf(num);
    }

    public static String formatStr(String str, int length) {
        int oldLength = 0;
        if (StringUtils.isNotBlank(str)) {
            oldLength = str.length();
        }
        if (oldLength >= length) {
            return str;
        }
        StringBuffer sb = new StringBuffer(length);
        for (int i = 0; i < length - oldLength; i++) {
            sb.append("0");
        }
        sb.append(str);
        return sb.toString();
    }

    /**
     * 校验手机号
     *
     * @param phone
     * @return
     * @author xianghy
     * @date 2019/3/13
     */
    public static boolean checkedPhone(String phone) {

        String regex = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
        if (!isBlank(phone)) {
            return check(phone, regex);
        }
        return false;
    }

    /**
     * 封装toString方法
     *
     * @param value
     * @return
     */
//    public static String toString(T value) {
//        return value == null ? "" : value.toString();
//    }

    /**
     * 封装toInteger方法
     *
     * @param value
     * @return
     */
    public static Integer toInteger(Object value, Integer defaultValue) {
        try {
            return value == null ? defaultValue : Integer.parseInt(value.toString());
        } catch (NumberFormatException e) {
            return defaultValue;
        }

    }

    /**
     * 封装toDouble方法
     *
     * @param value
     * @return
     */
    public static double toDouble(Object value, double defaultValue) {
        try {
            return value == null ? defaultValue : Double.parseDouble(value.toString());
        } catch (NumberFormatException e) {
            return defaultValue;
        }

    }

    public static String numtochinese(String input) {
        String s1 = "零壹贰叁肆伍陆柒捌玖";
        String s4 = "分角整元拾佰仟万拾佰仟亿拾佰仟";
        String temp = "";
        String result = "";
        if (input == null) {
            return "输入字串不是数字串只能包括以下字符（'0'～'9'，'.')，输入字串最大只能精确到仟亿，小数点只能两位！";
        }
        temp = input.trim();
        if (temp.indexOf("-") == 0) {
            temp = temp.substring(1);
            result = result.concat("负");
        }
        float f;
        try {
            f = Float.parseFloat(temp);
        } catch (Exception e) {
            return "输入字串不是数字串只能包括以下字符（'0'～'9'，'.')，输入字串最大只能精确到仟亿，小数点只能两位！";
        }
        int len = 0;
        if (temp.indexOf(".") == -1) {
            len = temp.length();
        } else {
            len = temp.indexOf(".");
        }
        if (len > s4.length() - 3) {
            return ("输入字串最大只能精确到仟亿，小数点只能两位！");
        }
        int n1, n2 = 0;
        String num = "";
        String unit = "";
        for (int i = 0; i < temp.length(); i++) {
            if (i > len + 2) {
                break;
            }
            if (i == len) {
                continue;
            }
            n1 = Integer.parseInt(String.valueOf(temp.charAt(i)));
            num = s1.substring(n1, n1 + 1);
            n1 = len - i + 2;
            unit = s4.substring(n1, n1 + 1);
//            if(num.equals("零")){
//                result = result.concat(num);
//            }else{
            result = result.concat(num).concat(unit);
//            }

        }
        if ((len == temp.length()) || (len == temp.length() - 1)) {
            result = result.concat("整");
        }
        if (len == temp.length() - 2) {
            result = result.concat("零分");
        }

        result = result.replaceAll("零仟零佰零拾|零仟零佰|零佰零拾|零仟|零佰|零拾", "零");
        result = result.replaceAll("零+", "零").replace("零亿", "亿");
        result = result.matches("^.*亿零万[^零]仟.*$") ? result.replace("零万", "零") : result.replace("零万", "万");
        result = result.replace("亿万", "亿");
        //Processing decimals
        result = result.replace("零角", "零").replace("零分", "");
        result = result.replaceAll("(^[零元]*)(.+$)", "$2");
        result = result.replaceAll("(^.*)([零]+元)(.+$)", "$1元零$3");

        //Processing integer
        result = result.replaceAll("元零角零分|元零角$|元$|^零$|元零$|元零零$|零元$", "元整");
        result = result.replaceAll("^元整$", "零元整");

        result = result.replace("零角零分", "整");
        result = result.replace("零零零", "零");
        result = result.replace("零零", "零");
        result = result.replace("零角", "");
        result = result.replace("零分", "");
        return result;
    }

    public static String subString(String value, int length) {
        int valueLength = 0;
        StringBuilder sb = new StringBuilder();
        if (value != null) {
            String[] split = value.split("");
            for (int i = 0; i < split.length; i++) {
                try {
                    int l = split[i].getBytes("GBK").length;
                    valueLength += l;
                    // 此处中文长度不够
                    if (valueLength == length) {
                        sb.append(split[i]);
                        return sb.toString();
                    } else if (valueLength -1 == length){
                        sb.append(" ");
                        return sb.toString();
                    }
                    sb.append(split[i]);
                } catch (Exception e) {

                }
            }

        }
        if (valueLength < length) {
            int l = length - valueLength ;
            for (int i = 0; i < l; i++) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }


    public static String[] split(Object str) {
        return split(str, ",");
    }
    public static String[] split(Object str, String regex) {
        if (str == null) {
            return new String[] {};
        } else {
            return str.toString().split(regex);
        }
    }

    public static void main(String[] args) {
        "XYZFxxxxxx".contains("XYZF");
    }
}
