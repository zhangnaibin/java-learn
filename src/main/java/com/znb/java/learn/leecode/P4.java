package com.znb.java.learn.leecode;

/**
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-23 下午3:47
 */
public class P4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        int[] sortMerge = new int[length];
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                sortMerge[k ++] = nums1[i ++];
            } else {
                sortMerge[k ++] = nums2[j ++];
            }
        }
        while (i < nums1.length) {
            sortMerge[k ++] = nums1[i ++];
        }
        while (j < nums2.length) {
            sortMerge[k ++] = nums2[j ++];
        }
        if (length % 2 == 0) {
            double sum = sortMerge[length /2] + sortMerge[length /2 -1];
            return sum / 2;
        } else {
            return sortMerge[length /2];
        }
    }

    public static void main(String[] args) {
        int[] num1 = {};
        int[] num2 = {2, 3};
        System.out.println(new P4().findMedianSortedArrays(num1, num2));
    }
}
