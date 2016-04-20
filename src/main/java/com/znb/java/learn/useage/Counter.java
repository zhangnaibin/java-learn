package com.znb.java.learn.useage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 利用原子操作和并发包实现的计数器
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-16 下午4:30
 */
public class Counter<T> {
    private Map<T, AtomicInteger> counter = new ConcurrentHashMap<T, AtomicInteger>();

    public void incrementAndGet(T key) {
        AtomicInteger result;
        if ((result = counter.get(key)) == null) {
            synchronized (counter) {
                if ((result = counter.get(key)) == null) {
                    result = new AtomicInteger(1);
                    counter.put(key, result);
                    return ;
                }
            }
        }
        result.incrementAndGet();

    }

    public int get(T key) {
        AtomicInteger result = counter.get(key);
        if (null != result) {
            return result.get();
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        final Counter<String> counter = new Counter<String>();
        List<Thread> list = new ArrayList<Thread>(100);
        for (int i = 0; i < 100; i ++) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 10000; i ++) {
                        try {
                            Thread.sleep(1);
                        } catch (Exception e) {

                        }
                        counter.incrementAndGet("znb");
                    }
                }
            });
            list.add(t);
        }

        for (Thread thread : list) {
            thread.start();
        }

        for (Thread thread : list) {
            try {
                thread.join();
            }catch (Exception e) {

            }
        }

        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
        System.out.println(counter.get("znb"));
    }
}
