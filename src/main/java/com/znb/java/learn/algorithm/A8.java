package com.znb.java.learn.algorithm;

/**
 * 求1+2+…+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字以及条件判断语句（A?B:C）
 *
 * 使用static变量和构造函数，在构造函数中进行加法操作
 *
 * see http://zhedahht.blog.163.com/blog/static/2541117420072915131422/
 *
 * Java数组初始化时，对象初始化值为null
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-15 下午3:43
 */
public class A8 {
    public static class Temp{
        private static int num = 0;
        private static int sum = 0;
        public Temp() {
            num ++;
            sum += num;
        }

        public static void reset() {
            num = 0;
            sum = 0;
        }

        public static int getSum() {
            return sum;
        }
    }

    public int sum(int n) {
        Temp.reset();
        Temp[] temps = new Temp[n];
        System.out.println(Temp.getSum());
        return Temp.getSum();
    }

    public static void main(String[] args) {
        System.out.println(new A8().sum(100));
//        new Temp();
//        System.out.println(Temp.getSum());
//        new Temp();
//        System.out.println(Temp.getSum());
//        new Temp();
//        System.out.println(Temp.getSum());
        Temp[] aa = new Temp[10];
        for (int i = 0; i < 10; i ++) {
            System.out.println(aa[i]);
        }
    }
}
