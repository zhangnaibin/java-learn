package com.znb.java.learn.algorithm;

/**
 * O(logn)求Fibonacci数列
 *
 * see http://zhedahht.blog.163.com/blog/static/25411174200722991933440/
 *
 * 递归解法，负责度nlogn,有很多重复的操作
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-15 下午5:13
 */
public class A16 {
    // O(nlogn) 解法
    public int fibonacci1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci1(n - 1) + fibonacci1(n - 2);
    }

    // O(n)解法，保存每一步的中间值
    public int fibonacci2(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int numOne = 0;
        int numTwo = 1;
        int result = 0;
        for (int i = 2; i < n; i ++) {
            result = numOne + numTwo;
            numOne = numTwo;
            numTwo = result;
        }
        return result;
    }

    // O(logn) 解法，需要使用数学公式
}
