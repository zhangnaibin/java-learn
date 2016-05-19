package com.znb.java.learn.algorithm;

/**
 * 输入一个正数n，输出所有和为n连续正数序列
 *
 * see http://zhedahht.blog.163.com/blog/static/25411174200732711051101/
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-17 下午8:51
 */
public class A26 {
    private void print(int min, int max) {
        for (int i = min; i <= max; i ++) {
            System.out.print(i);
        }
        System.out.println();
    }

    public void findContinueSequence(int n) {
        if (n < 3) {
            return ;
        }
        int min = 1, max = 2;
        int sum = min + max;
        while (max <= (n + 1) / 2 ) {
            if (sum > n) {
                sum -= min;
                min ++;
            }
            if (sum == n) {
                print(min, max);
                sum -= min;
                min ++;
            }
            if (sum < n) {
                max ++;
                sum += max;
            }
        }
    }

    public static void main(String[] args) {
        new A26().findContinueSequence(15);
    }
}
