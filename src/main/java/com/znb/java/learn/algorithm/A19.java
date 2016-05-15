package com.znb.java.learn.algorithm;

/**
 * 输入一个链表的头结点，反转该链表，并返回反转后链表的头结点
 *
 * see http://zhedahht.blog.163.com/blog/static/2541117420073471124487/
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-15 下午5:41
 */
public class A19 {
    public static class Node {
        int key;
        Node next;
    }

    public Node reverseList(Node head) {
        if (null == head) {
            return null;
        }
        Node newHead = null;
        Node pre = null;
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            if (next == null) {
                newHead = cur;
            }
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return newHead;
    }
}
