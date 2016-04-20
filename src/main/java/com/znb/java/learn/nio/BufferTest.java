package com.znb.java.learn.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 使用Buffer读写数据一般遵循以下四个步骤：
 1、写入数据到Buffer
 2、调用flip()方法
 3、从Buffer中读取数据
 4、调用clear()方法或者compact()方法
 clear()清空所有数据， compact()清除已读数据
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-20 下午10:05
 */
public class BufferTest {

    public void ByteBufferTest() {
        try {
            FileInputStream inputStream = new FileInputStream("test.txt");
            FileChannel inChannel = inputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(20);

            // write data
            inChannel.read(byteBuffer);
            byteBuffer.put("test".getBytes());

            // 将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值
            byteBuffer.flip();

            // read data
            inChannel.write(byteBuffer);
            byte aByte = byteBuffer.get();

            // 将position设回0，所以你可以重读Buffer中的所有数据。limit保持不变，仍然表示能从Buffer中读取多少个元素
            byteBuffer.rewind();

            // 调用Buffer.mark()方法，可以标记Buffer中的一个特定position。之后可以通过调用Buffer.reset()方法恢复到这个position
            byteBuffer.mark();
            byteBuffer.reset();

            // 比较剩余的元素，相同就true
            byteBuffer.equals(ByteBuffer.allocate(10));
            // 比较两者的公共剩余部分
            byteBuffer.compareTo(ByteBuffer.allocate(10));

            // 获取剩余量，从 position到limit之间的元素
            byteBuffer.remaining();
        } catch (Exception e) {

        }

    }

}
