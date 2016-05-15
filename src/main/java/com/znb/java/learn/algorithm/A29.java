package com.znb.java.learn.algorithm;

import java.util.Arrays;

/**
 * 输入一个整数数组，调整数组中数字的顺序，使得所有奇数位于数组的前半部分，
 * 所有偶数位于数组的后半部分。要求时间复杂度为O(n)。
 * see http://zhedahht.blog.163.com/blog/static/25411174200741295930898/
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-13 下午8:36
 */
public class A29 {

    public void reorder(int data[]) {
        if (null == data || data.length == 0) {
            return ;
        }
        int head = 0, tail = data.length - 1;
        while (head < tail) {
            while (isJishu(data[head])) {
                head ++;
            }
            while (!isJishu(data[tail])) {
                tail --;
            }
            // 没有这个限制，当head>tail时还会进行一次移动
            if (head < tail) {
                int temp = data[head];
                data[head] = data[tail];
                data[tail] = temp;
            }

        }
        System.out.println(Arrays.toString(data));
    }

    private boolean isJishu(int num) {
        return (num & 1) == 1;
    }

    public static void main(String[] args) {
        int[] data = {2, 4, 5, 6, 8, 1, 9, 7, 3, 11};
        new A29().reorder(data);
    }
}
