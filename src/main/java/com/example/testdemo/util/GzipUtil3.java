package com.example.testdemo.util;
import org.apache.tomcat.util.codec.binary.StringUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil3 {
    public static final String GZIP_ENCODE_UTF_8 = "UTF-8";
    public static final String GZIP_ENCODE_ISO_8859_1 = "ISO-8859-1";

    /** * 字符串压缩为GZIP字节数组 * * @param str * @return */
    public static byte[] compress(String str) {
        return compress(str, GZIP_ENCODE_UTF_8);
    }

    /** * 字符串压缩为GZIP字节数组 * * @param str * @param encoding * @return */
    public static byte[] compress(String str, String encoding) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encoding));
            gzip.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return out.toByteArray();
    }

    /** * GZIP解压缩 * * @param bytes * @return */
    public static byte[] uncompress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return out.toByteArray();
    }

    /** * * @param bytes * @return */
    public static String uncompressToString(byte[] bytes) {
        return uncompressToString(bytes, GZIP_ENCODE_UTF_8);
    }

    /** * * @param bytes * @param encoding * @return */
    public static String uncompressToString(byte[] bytes, String encoding) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            return out.toString(encoding);
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        //String str = "%5B%7B%22lastUpdateTime%22%3A%222011-10-28+9%3A39%3A41%22%2C%22smsList%22%3A%5B%7B%22liveState%22%3A%221";
        String str = "{\"name\":\"张三\",\"sex\":\"1\",\"address\":\"山西原平\"}";
        System.out.println("原长度：" + str.length());
        System.out.println("压缩后字符串：" + GzipUtil3.compress(str).toString());
        System.out.println("解压缩后字符串：" + StringUtils.newStringUtf8(GzipUtil3.uncompress(GzipUtil3.compress(str))));
        System.out.println("解压缩后字符串：" + GzipUtil3.uncompressToString(GzipUtil3.compress(str)));

        String encodeStr = Base64.getEncoder().encodeToString(GzipUtil3.compress(str));
        System.out.println("压缩文件转码base64"+encodeStr);
        try {
            byte[] ret = new sun.misc.BASE64Decoder().decodeBuffer(encodeStr);
            System.out.println("转码后"+ret);
            System.out.println("转码后解压缩：" + GzipUtil3.uncompressToString(ret));
            String s="2022-12-23 11:11:11";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sdf.format(sdf1.parse(s));
            System.out.println(format);
        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
