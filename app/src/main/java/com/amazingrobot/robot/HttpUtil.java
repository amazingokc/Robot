package com.amazingrobot.robot;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpUtil {
    public static int TXT = 100000;  //文本格式
    public static int TRAIN = 305000; //列车
    public static int NEWS = 302000; //新闻
    public static String API_HOST = "http://www.tuling123.com/openapi/api";
    public static String API_KEY = "7248434e46be11612ea15a97088a1ab7";
    static ByteArrayOutputStream baos;
    static InputStream is;

    public static String getData(final String info) {
        String result = "";
        try {
            URL url = new URL(getParam(info));
            // url打开连接对对象
            HttpURLConnection urlconnection = (HttpURLConnection) url
                    .openConnection();
            // 允许写入数据
            urlconnection.setDoOutput(true);
            // 设置请求方式
            urlconnection.setRequestMethod("GET");
            // 设置时间
            urlconnection.setReadTimeout(5 * 1000);
            urlconnection.setConnectTimeout(5 * 1000);
            // 得到输入流
            OutputStream os = urlconnection.getOutputStream();
            // 写入服务器端 能以java的基本类型往里写
            DataOutputStream outputStream = new DataOutputStream(os);
            outputStream.flush();
            outputStream.close();
            is = urlconnection.getInputStream();
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            result = new String(baos.toByteArray());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (baos != null)
                try {
                    baos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return result;
    }


    private static String getParam(String info) {
        String url = "";
        try {
            url = API_HOST + "?key=" + API_KEY + "&info="
                    + URLEncoder.encode(info, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return url;

    }

}