package com.znb.java.learn.algorithm;

import java.util.Stack;

/**
 * 用两个栈实现队列
 *
 * see http://zhedahht.blog.163.com/blog/static/2541117420073293950662/
 *
 * 一个负责进，一个负责出；出的为空时把进栈的数据压如出的栈
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-15 下午5:32
 */
public class A18<T> {
    private Stack<T> stackIn = new Stack<T>();
    private Stack<T> stackOut = new Stack<T>();

    public void push(T t) {
        stackIn.push(t);
    }

    public T pop() throws Exception{
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
        if (!stackOut.isEmpty()) {
            return stackOut.pop();
        } else {
            throw new Exception("empty!");
        }
    }
}
