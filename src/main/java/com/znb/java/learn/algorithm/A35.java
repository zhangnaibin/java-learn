package com.znb.java.learn.algorithm;

/**
 *
 * 题目：两个单向链表，找出它们的第一个公共结点。

    链表的结点定义为：

    struct ListNode
    {
        int         m_nKey;
        ListNode*   m_pNext;
    };
 *
 * see http://zhedahht.blog.163.com/blog/static/254111742008053169567/
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-13 下午7:12
 */
public class A35 {
    public static class Node {
        int key;
        Node next;
    }

    private int getLength(Node head) {
        if (null == head) {
            return 0;
        }
        int length = 1;
        while (head.next != null) {
            length ++;
            head = head.next;
        }
        return length;
    }

    public Node getFirstCommonNode(Node pHead1, Node pHead2) {
        int length1 = getLength(pHead1);
        int length2 = getLength(pHead2);
        int lengthDiff = length1 - length2;
        Node longNode = pHead1;
        Node shortNode = pHead2;
        if (lengthDiff < 0) {
            longNode = pHead2;
            shortNode = pHead1;
            lengthDiff = - lengthDiff;
        }

        for (int i = 0; i < lengthDiff; i ++) {
            longNode = longNode.next;
        }

        while (longNode != null && shortNode != null && longNode != shortNode) {
            longNode = longNode.next;
            shortNode = shortNode.next;
        }
        Node firstNode = null;
        if (longNode == shortNode) {
            firstNode = longNode;
        }
        return firstNode;
    }
}
