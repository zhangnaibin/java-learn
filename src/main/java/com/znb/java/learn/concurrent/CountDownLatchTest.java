package com.znb.java.learn.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-20 上午10:14
 */
public class CountDownLatchTest {

    static class innerThread implements Runnable{
        CountDownLatch countDownLatch;
        public innerThread(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName()  + " arrive ");
                countDownLatch.countDown();
            } catch (Exception e) {

            }
        }
    }

    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i ++) {
            Thread t = new Thread(new innerThread(countDownLatch));
            t.start();
        }

        System.out.println("wait begin");

        countDownLatch.await();
        
        System.out.println("wait end");
    }
}
