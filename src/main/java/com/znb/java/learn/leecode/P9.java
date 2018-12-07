package com.znb.java.learn.leecode;

public class P9 {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int[] array = new int[10];
        int index = -1;
        while (x != 0) {
            int left = x % 10;
            array[ ++ index] = left;
            x /= 10;
        }

        int low = 0, high = index;
        while (low < high) {
            if (array[low++] != array[high--]) {
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        P9 x = new P9();
        System.out.println(x.isPalindrome(121));
        System.out.println(x.isPalindrome(-121));
        System.out.println(x.isPalindrome(10));
    }
}
