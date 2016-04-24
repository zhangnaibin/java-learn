package com.znb.java.learn.basic;

/**
 * 静态变量初始化
 * 类初始化时，不会对静态内部类初始化
 * 类初始化是，先初始化静态变量
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-22 上午11:18
 */
public class StaticVarTest {
    static class test {
        public static String staticField = "static var";
        public String filed = "field";
        static {
            System.out.println(staticField);
            System.out.println("static init");
        }

        {
            System.out.println(filed);
            System.out.println("init");
        }

        public test() {
            System.out.println("construct");
        }

        public static void main(String[] args) {
            new test();
        }
    }

    public StaticVarTest() {
        System.out.println("static var test construct");
    }

//    public static void main(String[] args) {
//        new StaticVarTest();
//    }
}
