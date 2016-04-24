package com.znb.java.learn.basic.reflection;

import java.lang.reflect.Array;

/**
 * Java反射机制通过java.lang.reflect.Array这个类来处理数组
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 下午3:32
 */
public class ArrayTest {
    public static void main(String[] args) {
        // 创建一个int类型的数组。Array.newInstance()方法的第一个参数表示了我们要创建一个什么类型的数组。第二个参数表示了这个数组的空间是多大
        int[] intArray = (int[]) Array.newInstance(int.class, 3);

        // 设置值
        Array.set(intArray, 0, 123);
        Array.set(intArray, 1, 456);
        Array.set(intArray, 2, 789);

        // 读取值
        System.out.println(Array.get(intArray, 0));

        System.out.println("------------");

        // 获取数组成员类型
        String[] strings = new String[5];
        Class stringArrayClass = strings.getClass();
        Class stringArrayComponetType = stringArrayClass.getComponentType();
        System.out.println(stringArrayComponetType);
    }
}
