package com.znb.java.learn.algorithm;

/**
 * 输入一个单向链表，输出该链表中倒数第k个结点。链表的倒数第0个结点为链表的尾指针
 *
 * see http://zhedahht.blog.163.com/blog/static/2541117420072114478828/
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-15 下午3:58
 */
public class A9 {
    public static class Node {
        int key;
        Node next;
    }

    // 需要注意链表长度和k的关系
    public Node findKthToTail(Node head, int k) {
        if (null == head) {
            return null;
        }
        Node ahead = head, behind = null;
        while (k >= 0) {
            if (ahead.next != null) {
                ahead = ahead.next;
            } else {
                // 链表长度小于k
                return null;
            }
        }
        while (ahead.next != null) {
            ahead = ahead.next;
            behind = behind.next;
        }
        return behind;
    }
}
