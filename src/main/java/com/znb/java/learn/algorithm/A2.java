package com.znb.java.learn.algorithm;

import java.util.Stack;

/**
 * 定义栈的数据结构，要求添加一个min函数，能够得到栈的最小元素。
 * 要求函数min、push以及pop的时间复杂度都是O(1)
 *
 * see http://zhedahht.blog.163.com/blog/static/25411174200712895228171/
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-14 上午9:21
 */
public class A2 {
    public static class SpStack {
        private Stack<Integer> data = new Stack<Integer>();
        private Stack<Integer> min = new Stack<Integer>();

        public void push(int t) {
            if (min.peek() >= t) {
                min.push(t);
            }
            data.push(t);
        }

        public int pop() {
            int top = data.pop();
            if (min.peek() == top) {
                min.pop();
            }
            return top;
        }

        public int min() {
            return min.peek();
        }
    }
}
