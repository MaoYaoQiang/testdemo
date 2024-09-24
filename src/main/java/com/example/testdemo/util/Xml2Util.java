package com.example.testdemo.util;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class Xml2Util {

    public  String xmlTrade(String requestUrl, String requestBody) throws Exception {
        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        try {
            URL url = new URL(requestUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(60000);
            connection.setReadTimeout(60000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "text/xml");
            if("http://192.16.0.91:81/BusinessService/Exam.svc".equals(requestUrl)){
                connection.setRequestProperty("SOAPAction","http://www.tomtaw.com.cn/IMCIS/BusinessService/IExam/ScheduleCancelXml");
            }
            os = connection.getOutputStream();
            os.write(requestBody.getBytes());
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            } else {
                throw new Exception("交易请求失败, error code: " + connection.getResponseCode() + ", error message: " + connection.getResponseMessage());
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();
        }
        return result;
    }


    public  String buildParams(String usertoken,String model) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:bus=\"http://www.tomtaw.com.cn/IMCIS/BusinessService\">\n" +
                "<soapenv:Header/>\n" +
                "<soapenv:Body>\n" +
                "<bus:ScheduleCancelXml>\n" +
                "<bus:userUID>b0b67908-64db-4d72-828e-d7f9abafb98b</bus:userUID>\n" +
                "<bus:userToken></bus:userToken>\n" +
                "<bus:model><![CDATA[" +
                model+
                "]]></bus:model>\n" +
                "</bus:ScheduleCancelXml>\n" +
                "</soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }

    public  String buildBloodParams(String headXml,String bodyxml) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
                "  <soap:Body>\n" +
                "    <CallInterface xmlns=\"http://tempuri.org/\">\n" +
                "      <headXml><![CDATA["+headXml+"]]></headXml>\n" +
                "      <bodyXml><![CDATA["+bodyxml+"]]></bodyXml>\n" +
                "    </CallInterface>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
    }
}
