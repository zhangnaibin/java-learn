package com.znb.java.learn.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * 输入两个字符串，从第一字符串中删除第二个字符串中所有的字符。
 * 例如，输入”They are students.”和”aeiou”，则删除之后的第一个字符串变成”Thy r stdnts.”
 *
 * see http://zhedahht.blog.163.com/blog/static/25411174200801931426484/
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-18 下午4:50
 */
public class A36 {
    // Java 没有内存操作，只能再申请一块空间使用
    public char[] deleteChars(char[] source , char[] delete) {
        if (null == source || null == delete) {
            return null;
        }
        Set<Character> deleteSet = new HashSet<Character>(256);
        for (int i = 0; i < delete.length; i ++) {
            deleteSet.add(delete[i]);
        }
        char[] result = new char[source.length];
        int j = 0;
        for (int i = 0; i < source.length; i ++) {
            if (!deleteSet.contains(source[i])) {
                result[i++] = source[i];
            }
        }
        return result;
    }
}
