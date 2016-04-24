package com.znb.java.learn.basic.reflection;

import java.lang.reflect.Method;

/**
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 下午1:49
 */
public class MethodTest {
    public static class Test {
        public int getInt() {
            return -1;
        }

        public String getString() {
            return "znb";
        }

        public String setString(String str) {
            System.out.println(str);
            return str;
        }
    }

    public static void main(String[] args) {
        try {
            Class test = Test.class;
            // 获取所有方法,包含从父类继承的
            Method[] methods = test.getMethods();
            for (Method method : methods) {
                System.out.println(method.getName());
            }
            System.out.println("-----------");

            // 获取所有方法,包含从父类继承的
            Method[] methods2 = test.getDeclaredMethods();
            for (Method method : methods2) {
                System.out.println(method.getName());
            }
            System.out.println("-----------");

            // 获取指定名称方法
            Method oneMethon = test.getDeclaredMethod("getString");
            System.out.println(oneMethon.getName());
            System.out.println("-----------");

            // 获取指定名称和参数方法
            Method twoMethn = test.getDeclaredMethod("setString", new Class[]{String.class});// 没有参数可以传入null
            System.out.println(twoMethn.getName());
            System.out.println("-----------");

            // 方法的参数和返回值
            Class[] paramTypes = oneMethon.getParameterTypes();
            Class returnType = oneMethon.getReturnType();
            System.out.println(returnType.getName());
            System.out.println("-----------");

            // 通过method对象调用方法
            // 如果是一个静态方法调用的话则可以用null代替指定对象作为invoke()的参数，在上面这个例子中，
            // setString，你就要传入有效的Object实例而不是null。
            Object returnValue = twoMethn.invoke(test, "test");
//            System.out.println(returnValue.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
