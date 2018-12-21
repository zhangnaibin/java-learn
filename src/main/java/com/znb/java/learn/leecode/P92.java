package com.znb.java.learn.leecode;

public class P92 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        ListNode newHead = null;
        if (m > 1) {
            newHead = head;
        }

        ListNode preListTail = null;
        ListNode cur = head;
        m --;
        n --;
        // 寻找反转节点，保留前面链表尾
        while (m > 0) {
            preListTail = cur;
            cur = cur.next;
            m --;
            n --;
        }

        // 反转链表, 最后一个节点的反转要注意，反转前保存后
        ListNode reversListCurPre = null;
        ListNode reversListTail = cur;
        while (n > 0) {
            ListNode next = cur.next;
            cur.next = reversListCurPre;
            reversListCurPre = cur;
            cur = next;
            n --;
        }
        ListNode reversListHead = cur;
        ListNode afterListHead = cur.next;
        cur.next = reversListCurPre;

        if (preListTail != null) {
            preListTail.next = reversListHead;
        } else {
            newHead = reversListHead;
        }
        reversListTail.next = afterListHead;

        if (newHead == null) {
            newHead = reversListHead;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;
        ListNode.print(node1);

        P92 x = new P92();
        ListNode node = x.reverseBetween(node1, 2, 3);
        ListNode.print(node);
    }
}
