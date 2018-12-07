package com.znb.java.learn.leecode;

public class P6 {
    public static String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows == 1) {
            return s;
        }
        char[][] array = new char[numRows][s.length()];
        int row = 0;
        int col = 0;
        array[0][0] = s.charAt(0);
        int index = 1;
        while (index < s.length()) {
            // 先处理列
            while (col % (numRows - 1) == 0 && row < numRows - 1 && index < s.length()) {
                array[ ++ row][col] = s.charAt(index ++);
            }

            if (row > 0 && index < s.length()) {
                array[-- row][ ++ col] = s.charAt(index ++);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i ++) {
            int j = 0;
            while (j < s.length()) {
                if (array[i][j] != '\0') {
                    sb.append(array[i][j]);
                }
                j ++;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("", 1));

    }
}
