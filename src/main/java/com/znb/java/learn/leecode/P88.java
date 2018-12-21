package com.znb.java.learn.leecode;

public class P88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1;
        int index2 = n - 1;
        int index = m + n - 1;
        while (index1 >= 0 && index2 >= 0) {
            if (nums1[index1] > nums2[index2]) {
                nums1[index --] = nums1[index1 --];
            } else {
                nums1[index --] = nums2[index2 --];
            }
        }

        while (index1 >= 0) {
            nums1[index --] = nums1[index1 --];
        }

        while (index2 >= 0) {
            nums1[index --] = nums2[index2 --];
        }
    }

    public static void main(String[] args) {
        int[] n1 = new int[6];
        n1[0] = 1;
        n1[1] = 2;
        n1[2] = 3;

        int[] n2 = new int[]{2, 5, 6};
        new P88().merge(n1, 3, n2, 3);
        for (int i = 0; i < n1.length; i ++) {
            System.out.print(n1[i] + " -> ");
        }
    }
}
