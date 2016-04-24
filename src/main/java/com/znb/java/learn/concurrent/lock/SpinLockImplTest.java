package com.znb.java.learn.concurrent.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 自旋锁的实现
 * 自旋锁中 另有三种常见的锁形式:TicketLock ，CLHlock 和MCSlock
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 上午10:33
 */
public class SpinLockImplTest {

    // Ticket锁主要解决的是访问顺序的问题，主要的问题是在多核cpu上
    public static class TicketLock {
        private AtomicInteger serviceNum = new AtomicInteger();
        private AtomicInteger tickNum = new AtomicInteger();
        private static final ThreadLocal<Integer> LOCAL = new ThreadLocal<Integer>();

        // 当天tickerNum与serviceNum相等才加锁，一次完整操作后两者都+1
        public void lock () {
            int myTicket = tickNum.getAndIncrement();
            LOCAL.set(myTicket);
            while (myTicket != serviceNum.get()) {

            }
        }

        // 每次unlock， serviceNum 加 1
        public void unlock() {
            int myTicket = LOCAL.get();
            serviceNum.compareAndSet(myTicket, myTicket + 1);
        }
    }

    // 不停查询前驱变量，使用隐式队列，没有真实后继节点
    // MCS 的队列是显式的队列，有真实的后继结点属性
    public static class CLHLock {
        public static class CLHNode {
            private volatile boolean isLocked = true;
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
                while (preNode.isLocked) {

                }
                preNode = null;
                LOCAL.set(node);
            }
        }

        public void unlock() {
            CLHNode node = LOCAL.get();
            if (!UPDATER.compareAndSet(this, node, null)) {
                node.isLocked = false;
            }
            node = null;
        }

    }
}
