package com.znb.java.learn.concurrent.lock;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现一个公平锁
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-08 下午3:53
 */
public class FairLockTest {

    // 用来表示阻塞对象，避免信号丢失和假唤醒问题
    public static class QueueObject {
        private boolean isNotified = false;

        public synchronized void doWait() throws InterruptedException {
            while (!isNotified) {
                this.wait();
            }
            this.isNotified = false;
        }

        public synchronized void doNotify() {
            this.isNotified = true;
            this.notify();
        }

        public boolean equals(Object o) {
            return this == o;
        }
    }

    private boolean isLocked = false;
    private Thread lockingThread = null;
    private List<QueueObject> waitingThreads = new ArrayList<QueueObject>();

    public void lock() throws InterruptedException {
        QueueObject queueObject = new QueueObject();
        boolean isLockedThisThread = true;
        synchronized (this) {
            waitingThreads.add(queueObject);
        }

        while (isLockedThisThread) {
            synchronized (this) {
                isLockedThisThread = isLocked || waitingThreads.get(0) != queueObject;
                if (!isLockedThisThread) {
                    isLocked = true;
                    waitingThreads.remove(queueObject);
                    lockingThread = Thread.currentThread();
                }
            }
            try {
                queueObject.doWait();
            } catch (InterruptedException e) {
                synchronized (this) {
                    waitingThreads.remove(queueObject);
                    throw  e;
                }
            }
        }
    }

    public synchronized void unlock() {
        if (this.lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException("calling thread has not locked this lock!");
        }
        isLocked = false;
        lockingThread = null;
        if (waitingThreads.size() > 0) {
            waitingThreads.get(0).doNotify();
        }
    }
}
