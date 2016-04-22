package com.znb.java.learn.concurrent;

/**
 * 给每一个线程存储了一份数据，以空间换时间
 * 通过数组存储，使用线性探测处理hash冲突
 * 数据Entry继承自WeakReference
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-22 下午4:53
 */
public class ThreadLocalTest {
    ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

    public static void main(String[] args) {

    }
}
