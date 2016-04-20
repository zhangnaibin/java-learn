package com.znb.java.learn.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

/**
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-20 上午9:53
 */
public class CyclicBarrierTest {
    public static class ThreadTest implements Runnable {
        CyclicBarrier barrier;

        public ThreadTest(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run(){
            try {
                Thread.sleep((long)(Math.random() * 1000));
                System.out.println(" barrier 1 " + Thread.currentThread().getName() + " arrive, \t wait number: " + barrier.getNumberWaiting());

                barrier.await();

                Thread.sleep((long)(Math.random() * 1000));
                System.out.println(" barrier 2 " + Thread.currentThread().getName() + " arrive, \t wait number: " + barrier.getNumberWaiting());

                barrier.await();

                Thread.sleep((long)(Math.random() * 1000));
                System.out.println(" barrier 3 " + Thread.currentThread().getName() + " arrive, \t wait number: " + barrier.getNumberWaiting());
                barrier.await();
            } catch (Exception e) {

            }
        }
    }

    public static void main(String[] args) {
        List<Thread> list = new ArrayList<Thread>();
        CyclicBarrier barrier = new CyclicBarrier(5);
        CyclicBarrier barrier1 = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("barrier runnable : ready? go ");
            }
        });
        System.out.println("barrier --------------");
        for (int i = 0; i < 5; i ++) {
            Thread t = new Thread(new ThreadTest(barrier));
            t.start();
            list.add(t);
        }

        for (Thread t : list) {
            try {
                t.join();
            } catch (Exception e) {

            }
        }

        System.out.println("barrier end --------------");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }

        System.out.println("barrier with runnable() --------------");
        list.clear();
        for (int i = 0; i < 5; i ++) {
            Thread t = new Thread(new ThreadTest(barrier1));
            t.start();
            list.add(t);
        }

        for (Thread t : list) {
            try {
                t.join();
            } catch (Exception e) {

            }
        }
        System.out.println("barrier with runnable() end --------------");
    }
}
