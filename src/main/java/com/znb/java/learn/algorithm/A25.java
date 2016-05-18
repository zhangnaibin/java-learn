package com.znb.java.learn.algorithm;

/**
 * 输入一个整数n，求从1到n这n个整数的十进制表示中1出现的次数
 *
 * see http://zhedahht.blog.163.com/blog/static/25411174200732494452636/
 *
 *
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-17 下午8:43
 */
public class A25 {
    // 常规做法，计算次数比较多。复杂度O(n)
    private int numberOf1(int num) {
        int number = 0;
        while (num % 10 == 1) {
            number ++;
            num = num / 10;
        }
        return number;
    }

    public int findNumberOf1(int n) {
        int num = 0;
        for (int i =1; i <= n; i ++) {
            num += numberOf1(n);
        }

        return num;
    }

    // 总结规律

}
