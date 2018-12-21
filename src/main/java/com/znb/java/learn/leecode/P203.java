package com.znb.java.learn.leecode;

public class P203 {
    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        // head!= null 且值不等于val，
        ListNode newHead = head;
        ListNode pre = head;
        ListNode temp = head.next;
        while (temp != null) {
            if (temp.val == val) {
                pre.next = temp.next;
            } else {
                pre = temp;
            }
            temp = temp.next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(5);
        ListNode node7 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = null;
        ListNode.print(node1);

        P203 x = new P203();
        ListNode head = x.removeElements(new ListNode(6), 6);
        ListNode.print(head);
    }
}
