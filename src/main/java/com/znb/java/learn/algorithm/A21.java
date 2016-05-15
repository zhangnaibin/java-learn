package com.znb.java.learn.algorithm;

/**
 * 定义字符串的左旋转操作：把字符串前面的若干个字符移动到字符串的尾部。如把字符串abcdef左旋转2位得到字符串cdefab。
 * 请实现字符串左旋转的函数。要求时间对长度为n的字符串操作的复杂度为O(n)，辅助内存为O(1)
 *
 * see http://zhedahht.blog.163.com/blog/static/2541117420073993725873/
 *
 * 跟A7操作差不多，分割后翻转。分为两个单词
 *
 * 翻转两次后就是本身了，这个很重要的
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-15 下午5:56
 */
public class A21 {

    public static void revert(char[] data, int begin, int end) {
        if (data == null || begin  < 0 || end >= data.length) {
            return ;
        }
        while (begin < end) {
            char temp = data[end];
            data[end] = data[begin];
            data[begin] = temp;
            begin ++;
            end --;
        }
    }

    public void leftRotateString(char[] data, int m) {
        if (data == null || data.length == 0 || m > data.length || m <= 0) {
            return;
        }

        revert(data, 0, m - 1);
        revert(data, m, data.length - 1);
        revert(data, 0 , data.length - 1);
    }
}
