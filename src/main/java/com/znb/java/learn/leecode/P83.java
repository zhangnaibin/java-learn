package com.znb.java.learn.leecode;

public class P83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val != pre.val) {
                cur = cur.next;
                pre = pre.next;
            } else {
                pre.next = cur.next;
                cur = cur.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        P83 x = new P83();
        ListNode head = x.deleteDuplicates(node1);
        ListNode.print(head);
    }
}
