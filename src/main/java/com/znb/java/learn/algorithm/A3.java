package com.znb.java.learn.algorithm;

/**
 * 输入一个整形数组，数组里有正数也有负数。数组中连续的一个或多个整数组成一个子数组，
 * 每个子数组都有一个和。求所有子数组的和的最大值。要求时间复杂度为O(n)
 *
 * see http://zhedahht.blog.163.com/blog/static/254111742007219147591/
 *
 * 高级变种，需要使用动态规划
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-14 上午10:41
 */
public class A3 {

    public int getGreatestSumOfSubArray(int[] data) throws Exception{
        if (null == data || data.length == 0) {
            throw new Exception("invalid data array");
        }
        int curSum = 0, greatestSum = 0;
        for (int i = 0; i < data.length; i ++) {
            curSum += data[i];
            if (curSum < 0) {
                curSum = 0;
            }
            if (curSum > greatestSum) {
                greatestSum = curSum;
            }
        }

        // 当输入数组中所有整数都是负数时，子数组和的最大值就是数组中的最大元素
        if (greatestSum == 0) {
            greatestSum = data[0];
            for (int i = 1;i < data.length; i ++) {
                if (greatestSum < data[i]) {
                    greatestSum = data[i];
                }
            }
        }
        return greatestSum;
    }
}
