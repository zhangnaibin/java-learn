package com.znb.java.learn.concurrent;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-22 下午4:37
 */
public class ReadWriteLockTest {
    private Object data = null;
    ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void get() {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " ready to read data");
            Thread.sleep((long)(Math.random() * 1000));
            System.out.println(Thread.currentThread().getName() + " get data " + (data == null ? "znb" : data));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void put(Object data) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " ready to write data ");
            Thread.sleep((long)(Math.random() * 1000));
            this.data = data;
            System.out.println(Thread.currentThread().getName() + " write data " + data.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockTest readWrite = new ReadWriteLockTest();

        for (int i = 0; i < 5; i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readWrite.get();
                }
            }).start();
        }

        for (int i = 0; i < 5; i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readWrite.put(new Random().nextInt(10));
                }
            }).start();
        }

        for (int i = 0; i < 5; i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readWrite.get();
                }
            }).start();
        }
    }
}
