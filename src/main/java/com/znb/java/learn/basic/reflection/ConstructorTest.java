package com.znb.java.learn.basic.reflection;

import java.lang.reflect.Constructor;

/**
 * 构造器
 * 利用Java的反射机制你可以检查一个类的构造方法，并且可以在运行期创建一个对象。这些功能都是通过java.lang.reflect.Constructor这个类实现的
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 下午12:46
 */
public class ConstructorTest {
    public static class conTest {
        private String name;
        public conTest() {

        }

        public conTest(String a) {
            name = a;
        }

        public String getName() {
            return name;
        }

    }

    public static void main(String[] args) throws Exception{
        Class contest = conTest.class;
        // 获取所有构造器
        Constructor[] constructors = contest.getConstructors();
        for (Constructor c : constructors) {
            System.out.println(c.getName());
        }
        System.out.println("------------");
        // 获取指定参数构造器
        Constructor consInt = contest.getConstructor(String.class);
        System.out.println(consInt.getName());

        System.out.println("-------------");
        // 构造方法参数
        Class[] parameterTypes = consInt.getParameterTypes();
        for (Class ptype : parameterTypes) {
            System.out.println(ptype.getName());
        }

        System.out.println("---------");
        // 利用constructor对象实例化一个类
        Constructor cs = conTest.class.getConstructor(String.class);
        conTest test = (conTest)cs.newInstance("ssssss");
        System.out.println(test.getName());
    }
}
