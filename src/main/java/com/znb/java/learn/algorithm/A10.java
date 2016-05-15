package com.znb.java.learn.algorithm;

/**
 * 输入一个已经按升序排序过的数组和一个数字，在数组中查找两个数，使得它们的和正好是输入的那个数字。
 * 要求时间复杂度是O(n)。如果有多对数字的和等于输入的数字，输出任意一对即可
 *
 * see http://zhedahht.blog.163.com/blog/static/2541117420072143251809/
 *
 * 改进版本，键A57
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-15 下午4:05
 */
public class A10 {

    public boolean findTwoNumberWithSum(int data[], int sum) {
        if (data == null || data.length < 1) {
            return false;
        }
        int head = 0, tail = data.length - 1;
        while (head < tail) {
            if (data[head] + data[tail] == sum) {
                System.out.println(data[head] + "\t" + data[tail]);
                return true;
            } else {
                if (data[head] + data[tail] < sum) {
                    head ++;
                } else {
                    tail --;
                }
            }
        }
        return false;
    }
}
