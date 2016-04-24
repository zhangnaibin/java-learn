package com.znb.java.learn.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 下午3:51
 */
public class FinalTest {
    public static class FinalFieldExample {
        final int x;
        int y;
        public void setY(int y) {
            this.y = y;
        }

        public int getY() {
            return y;
        }
        static FinalFieldExample f;
        public FinalFieldExample() {
            x = 3;
            y = 4;
        }

        static void write() {
            f = new FinalFieldExample();
        }

        // 一个正在执行reader方法的线程保证看到f.x的值为3，因为它是final字段。它不保证看到f.y的值为4，因为f.y不是final字段
        static void reader() {
            if (f != null) {
                int i = f.x;
                int j = f.y;
            }
        }
    }



    public static void main(String[] args) {
        final int a = 2;
//        a = 4; // 不能改变

        // 对于容器类型和引用，不能重新再new了，但里面的内容是可以变化
        final List<String> stringList = new ArrayList<String>();
        stringList.add("zzz");
        stringList.add("xxx");
        System.out.println(stringList.toString());

        final FinalFieldExample example = new FinalFieldExample();
        example.setY(10);
        System.out.println(example.getY());
    }
}
