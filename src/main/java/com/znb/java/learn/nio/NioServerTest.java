package com.znb.java.learn.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;

/**
 * 简单例子，只有退出时才返回数据
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-20 下午5:24
 */
public class NioServerTest {

    private static class IOWorker implements Runnable {
        @Override
        public void run() {
            try {
                // selector是channel的管理者
                Selector selector = Selector.open();
                // 设置一个channel
                ServerSocketChannel channel = ServerSocketChannel.open();
                // 通道设置为非阻塞
                channel.configureBlocking(false);
                // socket 属于通道，由channel创建
                ServerSocket socket = channel.socket();
                socket.bind(new InetSocketAddress("localhost", 10086));
                // channel 到selector上注册事件，事件发生后select()方法返回
                channel.register(selector, channel.validOps());
                while (true) {
                    selector.select();
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (!key.isValid()) {
                            continue;
                        }
                        if (key.isAcceptable()) {
                            System.out.println("init server socket connect!!!");
                            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                            SocketChannel sc = ssc.accept();
                            sc.configureBlocking(false);
                            sc.register(selector, sc.validOps());
                        }
                        if (key.isWritable()) {
                            System.out.println("server ready to write to client");
                            SocketChannel client = (SocketChannel) key.channel();
                            Charset charset = Charset.forName("UTF-8");
                            CharsetEncoder encoder = charset.newEncoder();
                            CharBuffer charBuffer = CharBuffer.allocate(32);
                            charBuffer.put("Hello World");
                            charBuffer.flip();
                            ByteBuffer content = encoder.encode(charBuffer);
                            client.write(content);
                            // 执行后，key的channel取消在selector上的注册

                        }
                        if (key.isReadable()) {
                            System.out.println("server ready to read from client");
                            SocketChannel sc = (SocketChannel)key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(32);
                            sc.read(buffer);
                            byte[] data = buffer.array();
                            String msg = new String(data).trim();
                            System.out.println("server accept msg : " + msg);
                            sc.register(selector, sc.validOps());
                            key.cancel();
                        }
                    }
                }
            } catch (Exception e) {

            }
        }
    }

    private static class NIOClient implements Runnable {
        @Override
        public void run() {
            try {
                Selector selector = Selector.open();
                SocketChannel channel = SocketChannel.open();
                channel.configureBlocking(false);
                channel.connect(new InetSocketAddress("localhost", 10086));
                channel.register(selector, SelectionKey.OP_CONNECT);
                while (true) {
                    selector.select();
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isConnectable()) {
                            System.out.println("client to connect server!");
                            SocketChannel sc = (SocketChannel) key.channel();
                            if (sc.isConnectionPending()) {
                                sc.finishConnect();
                            }
                            sc.configureBlocking(false);
                            sc.register(selector, sc.validOps());
                        }
                        if (key.isReadable()) {
                            SocketChannel sc = (SocketChannel)key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(32);
                            sc.read(buffer);
                            byte[] data = buffer.array();
                            String msg = new String(data).trim();
                            System.out.println("client accept msg : " + msg);
                            sc.register(selector, sc.validOps());
                        }
                        if (key.isWritable()) {
                            SocketChannel sc = (SocketChannel) key.channel();
                            sc.write(ByteBuffer.wrap(new String("message from client").getBytes()));
                            sc.register(selector, sc.validOps());
                        }
                    }
                }
            } catch (Exception e) {

            }

        }
    }
    public static void main(String[] args) {
        new Thread(new IOWorker()).start();
        new Thread(new NIOClient()).start();
    }
}
