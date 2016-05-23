package com.znb.java.learn.leecode;

import java.util.Arrays;

/**
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-22 下午4:01
 */
public class P1 {

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i ++) {
            for (int j = i + 1; j < nums.length; j ++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {3,1,99,4,33,6,7};
        System.out.println(Arrays.toString(new P1().twoSum(array, 11)));
    }
}
