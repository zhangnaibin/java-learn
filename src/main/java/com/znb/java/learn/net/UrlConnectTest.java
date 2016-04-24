package com.znb.java.learn.net;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * URL和URLConnection
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 下午4:08
 */
public class UrlConnectTest {
    public static void main(String[] args) throws Exception{
        // 利用URL连接服务器
        URL url = new URL("");
        URLConnection urlConnection = url.openConnection();
        InputStream input = urlConnection.getInputStream();
        int data;
        while((data = input.read()) != -1){
            System.out.print((char) data);
        }
        input.close();
    }
}
