package com.znb.java.learn.nio;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * 管道是2个线程之间的单向数据连接。Pipe有一个source通道和一个sink通道。数据会被写到sink通道，从source通道读取
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-20 下午10:15
 */
public class PipeTest {
    public void test() {
        try {
            // pipe主要就是这两个方法，其他都是Object的了
            Pipe pipe = Pipe.open();

            // 要向管道写数据，需要访问sink通道
            Pipe.SinkChannel sinkChannel = pipe.sink();
            ByteBuffer buffer = ByteBuffer.allocate(20);
            buffer.clear();
            buffer.put("data to pipe".getBytes());
            buffer.flip();
            while (buffer.hasRemaining()) {
                sinkChannel.write(buffer);
            }

            // 从读取管道的数据，需要访问source通道
            Pipe.SourceChannel sourceChannel = pipe.source();
            ByteBuffer buffer1 = ByteBuffer.allocate(20);
            while (-1 != sourceChannel.read(buffer1)) {
                // do something
            }
        } catch (Exception e) {

        }
    }
}
