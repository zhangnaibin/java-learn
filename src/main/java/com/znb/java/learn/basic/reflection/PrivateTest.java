package com.znb.java.learn.basic.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 利用反射访问私有变量和私有方法
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 下午2:14
 */
public class PrivateTest {
    public static class Test {
        private String name;

        public Test(String name) {
            this.name = name;
        }

        public void setName (String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        private String getPrivate() {
            return name;
        }
    }

    public static void main(String[] args) throws Exception{

        Test test = new Test("private value");

        // 访问私有变量
        Field privateField = Test.class.getDeclaredField("name");
        // 只有设置后才可以访问，调用setAccessible()方法会关闭指定类Field实例的反射访问检查
        privateField.setAccessible(true);
        // 获取是某个对象的私有变量
        String value = (String) privateField.get(test);
        System.out.println(value);
        System.out.println("----------");

        // 访问私有方法
        Method privateMethod = Test.class.getDeclaredMethod("getPrivate", null);
        privateMethod.setAccessible(true);
        String returnVale = (String)privateMethod.invoke(test, null);
        System.out.println(returnVale);
    }
}
