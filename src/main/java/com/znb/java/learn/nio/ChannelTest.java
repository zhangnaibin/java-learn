package com.znb.java.learn.nio;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-20 下午5:19
 */
public class ChannelTest {

    /**
     * FileChannel，需要通过使用一个InputStream、OutputStream或RandomAccessFile来获取一个FileChannel实例,从文件读取数据
     */
    public void fileChannel() {
        FileOutputStream output = null;
        try {
             output = new FileOutputStream("xxx.txt");
//            RandomAccessFile randomAccessFile = new RandomAccessFile("", "");
            FileChannel channel = output.getChannel();

            // read data
            ByteBuffer readBuf = ByteBuffer.allocate(20);
            while (channel.read(readBuf) != -1) {
                readBuf.flip();
                // process readBuf
            }

            // write data
            ByteBuffer writeBuf = ByteBuffer.allocate(20);
            writeBuf.clear();
            writeBuf.put("data".getBytes());
            // 将buf从写模式，切换到读模式（写入channel对buf来说是读）
            writeBuf.flip();
            // 无法保证write()方法一次能向FileChannel写入多少字节，因此需要重复调用write()方法，直到Buffer中已经没有尚未写入通道的字节
            while (writeBuf.hasRemaining()) {
                channel.write(writeBuf);
            }

            // 返回该实例所关联文件的大小
            channel.size();
            // 将通道里尚未写入磁盘的数据强制写到磁盘上
            channel.force(true);
            // 截取一个文件。截取文件时，文件将中指定长度后面的部分将被删除
            channel.truncate(1024);
            // 设置FileChannel的当前位置
            long pos = channel.position();
            channel.position(pos + 1);

            URL url = new URL("http://www.baidu.com");
            InputStream in = url.openStream();
            ReadableByteChannel readChannel = Channels.newChannel(in);
            channel.transferFrom(readChannel, 0, Integer.MAX_VALUE);
        } catch (Exception e) {

        } finally {
            if (null != output) {
                try {
                    output.close();
                } catch (Exception e) {

                }
            }
        }

    }

    /**
     * 连接到TCP网络套接字的通道，读写tcp网络数据
     */
    public void SocketChannel() {
        SocketChannel socketChannel = null;
        try {
            socketChannel  = SocketChannel.open();
            // read data
            ByteBuffer readBuf = ByteBuffer.allocate(20);
            while (socketChannel.read(readBuf) != -1) {
                readBuf.flip();
                // process readBuf
            }

            // write data
            ByteBuffer writeBuf = ByteBuffer.allocate(20);
            writeBuf.clear();
            writeBuf.put("".getBytes());
            writeBuf.flip();
            while (writeBuf.hasRemaining()) {
                socketChannel.write(writeBuf);
            }

            // 设置非阻塞模式,设置之后，就可以在异步模式下调用connect(), read() 和write()了
            socketChannel.configureBlocking(false);

            // connect, true if connect established
            socketChannel.connect(new InetSocketAddress("", 80));
            if (socketChannel.isConnectionPending()) {
                socketChannel.finishConnect();
            }

        } catch (Exception e) {

        } finally {
            if (null != socketChannel) {
                try {
                    socketChannel.close();
                } catch (Exception e) {

                }
            }
        }
    }

    /**
     * 监听tcp连接，每一个连接创建一个SocketChannel
     */
    public void ServerSocketChannelTest() {
        ServerSocketChannel serverSocketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress("", 80));
            // 监听链接，一般不这么使用，使用selector绑定
//            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();
//            }

            // 非阻塞模式
            serverSocketChannel.configureBlocking(false);
            while (true) {
                SocketChannel socketChannel1 = serverSocketChannel.accept();
                if (null != socketChannel1) {
                    // do something
                }
            }
        } catch (Exception e) {

        } finally {
            if (null != serverSocketChannel) {
                try {
                    serverSocketChannel.close();
                } catch (Exception e) {

                }
            }
        }
    }

    /**
     * DatagramChannel是一个能收发UDP包的通道。因为UDP是无连接的网络协议，所以不能像其它通道那样读取和写入。它发送和接收的是数据包
     */
    public void DatagramChannelTest() {
        DatagramChannel datagramChannel = null;
        try {
            datagramChannel = DatagramChannel.open();
            datagramChannel.socket().bind(new InetSocketAddress(9999));
            datagramChannel.configureBlocking(false);
            // receive data, 特有的模式
            ByteBuffer receiverBuf = ByteBuffer.allocate(20);
            receiverBuf.clear();
            // 阻塞模式，返回source地址；非阻塞模式，数据不ok，返回null
            datagramChannel.receive(receiverBuf);

            // 使用read()和write()方法，就像在用传统的通道一样。只是在数据传送方面没有任何保证
        } catch (Exception e) {

        } finally {
            if (null != datagramChannel) {
                try {
                    datagramChannel.close();
                } catch (Exception e) {

                }
            }
        }
    }

    /**
     * 分散、聚集操作channel的数据，适用于需要将传输数据分开处理的场合
     */
    public void scatterTest() throws Exception{
        // 适用于消息固定的情况，只有第一个填满了才会填充下一个
        SocketChannel channel = SocketChannel.open();
        ByteBuffer header = ByteBuffer.allocate(20);
        ByteBuffer body = ByteBuffer.allocate(1024);
        ByteBuffer[] bufferArray = {header, body};
        channel.read(bufferArray);

        // 写入channel
        channel.write(bufferArray);
    }

    /**
     * 通道间的数据传输
     * 如果两个通道有一个是FileChannel.只能FileChannel调用传输方法
     */
    public void channelTransport() throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile("", "");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("", "");
        FileChannel toChannel = toFile.getChannel();

        long positon = 0;
        long count = fromChannel.size();
        // 将数据从源通道传输到FileChannel中（译者注：这个方法在JDK文档中的解释为将字节从给定的可读取字节通道传输到此通道的文件中）
        toChannel.transferFrom(fromChannel, positon, count);

        // 将数据从FileChannel传输到其他的channel中
        fromChannel.transferTo(positon, count, toChannel);

        // SocketChannel只会传输此刻准备好的数据（可能不足count字节）。因此，SocketChannel可能不会将请求的所有数据(count个字节)全部传输到FileChannel中
        SocketChannel socketChannel = SocketChannel.open();
        fromChannel.transferTo(positon, count, socketChannel);
        toChannel.transferFrom(socketChannel, positon, count);
    }

}
