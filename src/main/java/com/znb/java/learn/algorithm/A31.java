package com.znb.java.learn.algorithm;

import java.util.Stack;

/**
 * 输入一个链表的头结点，从尾到头反过来输出每个结点的值
 * see http://zhedahht.blog.163.com/blog/static/2541117420079237185699/
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-13 下午8:20
 */
public class A31 {
    public static class Node {
        int key;
        Node next;
    }

    public void printListReversely(Node head) {
        if (head != null) {
            if (head.next != null) {
                printListReversely(head.next);
            }
            System.out.println(head.key);
        }
    }

    public void printListReversely2(Node head) {
        Stack<Integer> stack = new Stack<Integer>();
        while (head != null) {
            stack.push(head.key);
            head = head.next;
        }

        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }

}
