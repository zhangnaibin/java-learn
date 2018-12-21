package com.znb.java.learn.leecode;

public class P11 {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int low = 0;
        int high = height.length - 1;
        while (low < high) {
            int area = Math.min(height[low], height[high]) * (high - low);
            maxArea = Math.max(area, maxArea);
            if (height[low] < height[high]) {
                low ++;
            } else {
                high --;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new P11().maxArea(a));
    }
}
