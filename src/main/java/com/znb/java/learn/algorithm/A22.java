package com.znb.java.learn.algorithm;

/**
 * 输入一个整数，求该整数的二进制表达中有多少个1。例如输入10，由于其二进制表示为1010，有两个1，因此输出2。
 *
 * see http://zhedahht.blog.163.com/blog/static/2541117420073118945734/
 *
 * 注意：不可以使用数字右移后与1相与的方法，负数会导致死循环
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-15 下午5:57
 */
public class A22 {

    public int getOneNum(int n) {
        int count = 0;
        int flag = 1;
        // 这里有问题，左移后不会变成0的。Java没有无符号整数
        while (flag != 0) {
            if ((n & flag) == 1) {
                count ++;
            }
            flag = flag << 1;
        }
        return count;
    }

    public static void main(String[] args) {
        int i = 1;
        System.out.println(i << 31);
    }
}
