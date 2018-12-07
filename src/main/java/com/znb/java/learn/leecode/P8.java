package com.znb.java.learn.leecode;

public class P8 {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int result = 0;
        boolean start = false;
        boolean blowZero = false;
        for (int i = 0; i < chars.length; i ++) {
            char c = chars[i];
            if (c ==' ' && !start) {
                continue;
            }

            if (c == '-' && !start) {
                blowZero = true;
                start = true;
                continue;
            }

            if (c == '+' && !start) {
                blowZero = false;
                start = true;
                continue;
            }

            if (!start && !isNum(c)) {
                return 0;
            }

            if (isNum(c)) {
                int temp;
                if (!blowZero) {
                    temp = result * 10 + (c - '0');
                } else {
                    temp = result * 10 - (c - '0');
                }
                // 判断溢出
                if (temp / 10 != result) {
                    if (blowZero) {
                        return Integer.MIN_VALUE;
                    } else {
                        return Integer.MAX_VALUE;
                    }
                }
                result = temp;
                start = true;
            }

            if (start && !isNum(c)) {
                break;
            }
        }
        return result;
    }

    private boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    public static void main(String[] args) {
        P8 x = new P8();
        System.out.println(x.myAtoi("42"));
        System.out.println(x.myAtoi("    -42"));
        System.out.println(x.myAtoi("4193 with words"));
        System.out.println(x.myAtoi("words and 987"));
        System.out.println(x.myAtoi("-91283472332"));
        System.out.println(x.myAtoi("+1"));
        System.out.println(x.myAtoi("2147483648"));
    }
}
