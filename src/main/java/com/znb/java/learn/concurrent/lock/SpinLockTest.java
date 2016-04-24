package com.znb.java.learn.concurrent.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * 通过cas操作实现，不改变线程状态,非公平锁
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 上午10:28
 */
public class SpinLockTest {
    private AtomicReference<Thread> sign = new AtomicReference<Thread>();
    public void lock() {
        Thread current = Thread.currentThread();
        while (!sign.compareAndSet(null, current)) {

        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        sign.compareAndSet(current, null);
    }
}
