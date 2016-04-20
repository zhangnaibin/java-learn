package com.znb.java.learn.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-16 下午9:56
 */
public class CondationLockTest {
    static Buffer buf = new Buffer(5);

    static class PutT implements Runnable{
        String name;
        int num;
        public PutT(String name, int num) {
            this.name = name;
            this.num = num;
        }

        @Override
        public void run() {
            System.out.println("thread " + name + "\t put data \t" + num);
            buf.put(num);
        }
    }

    static class TakeT implements Runnable{
        String name;
        public TakeT(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println("thread " + name + "\t take data \t" + buf.take());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i ++) {
            new Thread(new TakeT("take" + i)).start();
            new Thread(new PutT("put" + i, i)).start();
        }
    }

    static class Buffer {
        int[] buf;
        private Lock lock = new ReentrantLock();
        private Condition full = lock.newCondition();
        private Condition empty = lock.newCondition();
        int putptr, takptr, count;

        public Buffer(int size) {
            buf = new int[size];
        }

        public void put(int val) {
            lock.lock();
            try {
                if (count == buf.length) {
                    empty.await();
                }
                buf[putptr] = val;
                putptr = (putptr + 1) % buf.length;
                count ++;
                full.signal();

            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }

        public int take() {
            lock.lock();
            try {
                if (count == 0) {
                    full.await();
                }
                int result = buf[takptr];
                takptr = (takptr + 1) % buf.length;
                count --;
                empty.signal();
                return result;
            } catch (Exception e) {
                System.out.println(e);
            }
            finally {
                lock.unlock();
            }
            return Integer.MAX_VALUE;
        }
    }
}
