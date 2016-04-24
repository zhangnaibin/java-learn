package com.znb.java.learn.concurrent.Atomic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 一个基于反射的工具类，可以对指定类的指定的volatile引用字段进行原子更新(不能是private字段)
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 上午10:58
 */
public class AtomicReferenceFieldUpdateerTest {
    static class Dog {
        volatile String name = "dog1";
        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        AtomicReferenceFieldUpdater updater = AtomicReferenceFieldUpdater.newUpdater(Dog.class, String.class, "name");
        Dog dog1 = new Dog();
        updater.compareAndSet(dog1, dog1.name, "test");
        System.out.println(dog1.name);

        // 获取原来的值，并设置为新值
        System.out.println(updater.getAndSet(dog1, "lele"));
        System.out.println(dog1.name);
    }
}
