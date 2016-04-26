package com.znb.java.learn.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * exchanger 允许两个并发任务相互交换数据
 * 两者在一个安全时刻互换数据
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-25 下午1:08
 */
public class ExchangerTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final Exchanger<String> exchanger = new Exchanger<String>();

        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String data1 = " food ";
                    System.out.println(Thread.currentThread().getName()  + " put data " + data1);
                    Thread.sleep((long)(Math.random() * 1000));
                    String date2 = exchanger.exchange(data1);
                    System.out.println(Thread.currentThread().getName()  + " get data " + date2);
                } catch (Exception e) {

                }
            }
        });

        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String data1 = " money ";
                    System.out.println(Thread.currentThread().getName()  + " put data " + data1);
                    Thread.sleep((long)(Math.random() * 1000));
                    String date2 = exchanger.exchange(data1);
                    System.out.println(Thread.currentThread().getName()  + " get data " + date2);
                } catch (Exception e) {

                }
            }
        });

        service.shutdown();
    }
}
