package com.znb.java.learn.algorithm;

/**
 * 在一个字符串中找到第一个只出现一次的字符。如输入abaccdeff，则输出b。
 *
 * see http://zhedahht.blog.163.com/blog/static/25411174200722191722430/
 *
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-15 下午4:50
 */
public class A13 {

    public char firstNotRepeatChar(char[] data) {
        if (null == data || data.length == 0) {
            return 0;
        }
        int[] counter = new int[256];
        for (int i = 0; i < counter.length; i ++) {
            counter[i] = 0;
        }

        // 计数,计算每个字符出现次数
        for (int i = 0; i < counter.length; i++ ) {
            counter[data[i]] ++;
        }

        // 遍历，寻找第一次出现的字符
        for (int i = 0; i < counter.length; i ++) {
            if (counter[data[i]] == 1) {
                return data[i];
            }
        }
        return 0;
    }
}
