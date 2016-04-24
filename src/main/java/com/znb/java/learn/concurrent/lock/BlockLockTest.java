package com.znb.java.learn.concurrent.lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;

/**
 * 阻塞锁
 * JAVA中，能够进入\退出、阻塞状态或包含阻塞锁的方法有 ，synchronized 关键字（其中的重量锁
 * ），ReentrantLock，Object.wait()\notify(),LockSupport.park()/unpart()
 * 阻塞锁，阻塞线程不会占用cpu时间，竞争激烈时效率高于自旋锁
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 上午10:24
 */
public class BlockLockTest {

    // 将CLH锁的自旋部分改为了LockSupport的阻塞
    public static class CLHLock {

        public static class CLHNode {
            private volatile Thread isLocked;
        }

        // 原文这里有问题，这个字段不能是private字段
        volatile CLHNode tail;
        private static final ThreadLocal<CLHNode> LOCAL = new ThreadLocal<CLHNode>();
        private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(CLHLock.class, CLHNode.class, "tail");

        public void lock() {
            CLHNode node = new CLHNode();
            LOCAL.set(node);
            CLHNode preNode = UPDATER.getAndSet(this, node);
            if (preNode != null) {
                preNode.isLocked = Thread.currentThread();
                LockSupport.park();
                preNode = null;
                LOCAL.set(node);
            }
        }

        public void unlock() {
            CLHNode node = LOCAL.get();
            if (!UPDATER.compareAndSet(this, node, null)) {
                LockSupport.unpark(node.isLocked);
            }
            node = null;
        }

    }
}
