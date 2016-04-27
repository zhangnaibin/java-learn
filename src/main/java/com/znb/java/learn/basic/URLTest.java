package com.znb.java.learn.basic;

import java.net.URL;

/**
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-27 下午2:50
 */
public class URLTest {
    public static void main(String[] args) {
        String urlString = "https://www.google.com/?ion=1&espv=2#newwindow=1&q=redis%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84";
        try {
            URL url = new URL(urlString);

            System.out.println("host : " + url.getHost());
            System.out.println("path : " + url.getPath());
            System.out.println("query : " + url.getQuery());
            System.out.println("authority : " + url.getAuthority());
            System.out.println("port : " + url.getPort());
            System.out.println("default port : " + url.getDefaultPort());
            System.out.println("protocol : " + url.getProtocol());
            System.out.println("file : " + url.getFile());
        } catch (Exception e) {

        }
    }
}
