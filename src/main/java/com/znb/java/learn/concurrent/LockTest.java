package com.znb.java.learn.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 代码中多次获取不会造成死锁
 * 可重入锁：递归锁，同一个线程外层函数获取锁后，内层递归仍然有获取锁代码，但不受影响
 * sync和ReentrantLock是可重入锁，可重入锁可以避免死锁
 * 自旋锁不可重入，一个线程两次调用时，第二次调用会自旋，造成死锁。unlock第一次会释放锁，但不应该
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-22 下午5:23
 */
public class LockTest {
    ReentrantLock reentrantLock = new ReentrantLock();

    public synchronized void get() {
        System.out.println(Thread.currentThread().getId());
        set();
    }

    public synchronized void set() {
        System.out.println(Thread.currentThread().getId());
    }

    public void get2() {
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getId());
        set2();
        reentrantLock.unlock();
    }

    public void set2() {
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getId());
        reentrantLock.unlock();
    }

    // 自旋锁---不可重入
    private AtomicReference<Thread> owner = new AtomicReference<Thread>();
    // 第二次调用这个时会不断自旋，死锁
    public void lock() {
        Thread current = Thread.currentThread();
        while (!owner.compareAndSet(null, current)) {

        }
    }
    // unlock时，第一次就释放锁，应该第二次释放
    public void unlock() {
        Thread current = Thread.currentThread();
        owner.compareAndSet(current, null);
    }

    // 可重入自旋锁，需要一个计数器
    private int count = 0;
    public void lock1() {
        Thread current = Thread.currentThread();
        if (current == owner.get()) {
            count ++;
            return ;
        }
        while (!owner.compareAndSet(null, current)) {

        }
    }

    public void unlock2() {
        Thread current = Thread.currentThread();
        if (current == owner.get()) {
            if (count != 0) {
                count --;
            } else {
                owner.compareAndSet(current, null);
            }
        }
    }

    public static void main(String[] args) {
        final LockTest lockTest = new LockTest();
        // 同一个线程id被输出两次，内部的方法获不受影响,sync可重入
        for (int i = 0; i < 5; i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lockTest.get();
                }
            }).start();
        }

        // 同一个线程id被输出两次，内部的方法获不受影响。ReentrantLock可重入
        for (int i = 0; i < 5; i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lockTest.get2();
                }
            }).start();
        }

    }
}
