package com.znb.java.learn.basic.reflection;

import java.lang.reflect.Method;

/**
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 下午12:22
 */
public class SimpleTest {
    public static class My {
        public int getInt() {
            return 0;
        }

        public double getDouble() {
            return 0.0;
        }
    }

    public static void main(String[] args) {
        Method[] methods = My.class.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }
}
