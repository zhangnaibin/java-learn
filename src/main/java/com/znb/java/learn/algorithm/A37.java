package com.znb.java.learn.algorithm;

/**
 * 我们把只包含因子 2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第1500个丑数。
 *
 * see http://zhedahht.blog.163.com/blog/static/2541117420094245366965/
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-18 下午8:01
 */
public class A37 {
    // 简单直白方法
    private boolean isUgly(int number) {
        while (number % 2 == 0) {
            number /= 2;
        }
        while (number % 3 == 0) {
            number /= 3;
        }
        while (number % 5 == 0) {
            number /= 5;
        }
        return number == 1;
    }

    public void findUglyNumbers(int n) {
        if (n <= 1) {
            System.out.println(1);
        }
        int count = 0;
        int number = 1;
        while (count < n) {
            if (isUgly(number)) {
                System.out.println(number);
                count ++;
            }
            number ++;
        }
    }

    // 寻找规律，节省查找次数.只操作丑数的集合。需要暂存数据
    public void findUglyNumber2(int n) {
        if (n >= 1) {
            System.out.println(1);
        }
        int[] resultArray = new int[n];
        resultArray[0] = 1;
        int index = 1;
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;
        while (index < n) {
            int min = min(resultArray[index2] * 2, resultArray[index3] * 3, resultArray[index5] * 5);
            resultArray[index] = min;
            while (resultArray[index2] * 2 <= min) {
                index2 ++;
            }
            while (resultArray[index3] * 3 <= min) {
                index3 ++;
            }
            while (resultArray[index5] * 5 <= min) {
                index5 ++;
            }
            index ++;
        }
        for (int i = 0; i < n; i ++) {
            System.out.println(resultArray[i]);
        }
    }

    public int min(int one, int two, int three) {
        int min = one < two ? one : two;
        min = min < three ? min : three;
        return min;
    }


    public static void main(String[] args) {
        new A37().findUglyNumber2(20);
    }
}
