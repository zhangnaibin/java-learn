package com.znb.java.learn.leecode;

public class P26 {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int pre = nums[0];

        boolean removeData = false;
        for (int i = 1; i < nums.length; i ++) {
            if (pre != nums[i]) {
                pre = nums[i];
            } else {
                nums[i] = Integer.MIN_VALUE;
                removeData = true;
            }
        }

        if (!removeData) {
            return nums.length;
        }

        // 寻找第一个移动数据的位置, 数组长度>2, 肯定从第二个开始放置
        int putIndex = 1;
        while (putIndex < nums.length) {
            if (nums[putIndex] != Integer.MIN_VALUE) {
                putIndex ++;
            } else {
                break;
            }
        }

        int moveIndex = putIndex + 1;
        while (moveIndex < nums.length) {
            if (nums[moveIndex] != Integer.MIN_VALUE) {
                nums[putIndex ++] = nums[moveIndex ++];
            } else {
                moveIndex ++;
            }
        }
        return putIndex;
    }

    private void print(int[] nums, int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " -> ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        P26 x = new P26();
//        int[] a = new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
//        int[] a = new int[] {1, 1, 2};
        int[] a = new int[] {};
//        int[] a = new int[] {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int lengthA = x.removeDuplicates(a);
        x.print(a, lengthA);
    }
}
