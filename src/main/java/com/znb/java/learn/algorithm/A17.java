package com.znb.java.learn.algorithm;

/**
 * 输入一个表示整数的字符串，把该字符串转换成整数并输出。例如输入字符串"345"，则输出整数345
 *
 * see http://zhedahht.blog.163.com/blog/static/25411174200731139971/
 *
 * 需要明确异常情况的处理方法
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-15 下午5:22
 */
public class A17 {

    public int strToInt(char[] data) throws Exception{
        if (data == null || data.length == 0) {
            throw new Exception("ileggal input");
        }
        boolean minus = false;
        int i = 0;
        if (data[0] == '-') {
            minus = true;
            i ++;
        }

        int sum = 0;
        // 忽略非数字字符
        for (; i < data.length; i ++) {
            if (data[i] >= '0' && data[i] <= '9') {
                sum += sum * 10 + (data[i] - '0');
                if (sum > Integer.MAX_VALUE) {
                    throw new Exception("out of bound!");
                }
            }
        }
        if (minus) {
            return -sum;
        } else {
            return sum;
        }
    }
}
