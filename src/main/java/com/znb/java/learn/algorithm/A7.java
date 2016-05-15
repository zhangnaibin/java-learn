package com.znb.java.learn.algorithm;

/**
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
 * 句子中单词以空格符隔开。为简单起见，标点符号和普通字母一样处理。
 * 例如输入“I am a student.”，则输出“student. a am I”。
 *
 * 我们先颠倒句子中的所有字符。这时，不但翻转了句子中单词的顺序，
 * 而且单词内字符也被翻转了。我们再颠倒每个单词内的字符。由于单词内的字符被翻转两次，
 * 因此顺序仍然和输入时的顺序保持一致
 *
 * see http://zhedahht.blog.163.com/blog/static/254111742007289205219/
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-15 下午2:59
 */
public class A7 {

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

    public static void revertSentence(char[] data) {
        if (null == data || data.length <= 0) {
            return;
        }
        revert(data, 0, data.length - 1);
        int begin = 0, end = 0;
        while (end < data.length) {
            if (data[begin] == ' ') {
                begin ++;
                end ++;
                continue;
            } else {
                if (data[end] == ' ') {
                    revert(data, begin, end - 1);
                    begin = end;
                } else {
                    end ++;
                }
            }
        }
    }

    public static void main(String[] args) {
        String en = "I am a student.";
        char[] aa = en.toCharArray();
        System.out.println(String.valueOf(aa));
        revertSentence(aa);
        System.out.println(String.valueOf(aa));

    }
}
