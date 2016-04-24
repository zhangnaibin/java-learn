package com.znb.java.learn.basic.io;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 管道使用
 * read()方法和write()方法调用时会导致流阻塞，使用管道时需要将他们分配给不同的线程
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 上午11:26
 */
public class PipeTest {
    public static void main(String[] args) throws Exception {
        // 线程内部访问的变量必须为final
        final PipedOutputStream output = new PipedOutputStream();
        // 通过构造函数关联两个管道
        final PipedInputStream input = new PipedInputStream(output);
        // 通过connect方法关联
        input.connect(output);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    output.write("hello world, pipe!".getBytes());
                } catch (Exception e) {

                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int data = input.read();
                    while (data != -1) {
                        System.out.println((char)data);
                        data = input.read();
                    }
                } catch (Exception e) {

                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
