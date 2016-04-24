package com.znb.java.learn.net;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 下午4:04
 */
public class SocketTest {
    public static void main(String[] args) throws Exception{

        Socket socket = new Socket("localhost", 80);

        // 发送数据
        OutputStream out = socket.getOutputStream();
        out.write("data".getBytes());
        out.flush();
        out.close();
        socket.close();

        // 读取数据
        // Socket的输入流中读取数据并不能读取文件那样，一直调用read()方法直到返回-1为止，
        // 因为对Socket而言，只有当服务端关闭连接时，Socket的输入流才会返回-1，而是事实上服务器并不会不停地关闭连
        InputStream in = socket.getInputStream();
        int data = in.read();
        //
        in.close();
        socket.close();
    }
}
