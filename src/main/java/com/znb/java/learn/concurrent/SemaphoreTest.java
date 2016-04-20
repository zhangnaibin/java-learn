package com.znb.java.learn.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量测试，控制资源被同时访问的次数
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-20 上午10:25
 */
public class SemaphoreTest {
    public static void main(String[] args) {

        final Semaphore semaphore = new Semaphore(5);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i ++) {
            final int no = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(no + " acquier");
                        System.out.println("avaliable " + semaphore.availablePermits());
                        Thread.sleep(3000);
                        semaphore.release();
                        System.out.println("after release, avaliable:" + semaphore.availablePermits());
                    } catch (Exception e) {

                    }


                }
            };
            exec.execute(runnable);
        }
        exec.shutdown();
    }
}
