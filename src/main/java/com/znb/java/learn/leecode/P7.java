package com.znb.java.learn.leecode;

public class P7 {
    public int reverse(int x) {
        if (x > Math.pow(2, 31) - 1 || x < (-1) * Math.pow(2, 32)) {
            return 0;
        }

        boolean blowZero = false;
        int low = 0;
        if (x < 0) {
            blowZero = true;
            low = 1;
        }
        String strX = x + "";
        char[] strArr = strX.toCharArray();
        int high = strArr.length - 1;
        while (low < high) {
            char temp = strArr[low];
            strArr[low] = strArr[high];
            strArr[high] = temp;
            low++;
            high--;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            sb.append(strArr[i]);
        }
        long data = Long.parseLong(sb.toString());

        if (data > Math.pow(2, 31) - 1 || data < (-1) * Math.pow(2, 32)) {
            return 0;
        } else {
            return (int) data;
        }

    }

    public int reverse2(int x) {
        if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) {
            return 0;
        }

        int result = 0;
        while (x != 0) {
            int left = x % 10;
            x = x / 10;
            int temp = result * 10 + left;
            if (temp / 10 != result ) {
                return 0;
            }
            result = temp;
        }
        return result;
    }
    public static void main(String[] args) {
//        System.out.println(new P7().reverse2(-123));
        System.out.println(-123 % 10);
        Math.addExact(1, 2);
    }
}
