package com.znb.java.learn.leecode;

public class P21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }

        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode iter;
        ListNode iterL1 = l1;
        ListNode iterL2 = l2;
        if (l1.val < l2.val) {
            iter = l1;
            iterL1 = l1.next;
        } else {
            iter = l2;
            iterL2 = l2.next;
        }
        ListNode head = iter;

        while (iterL1 != null && iterL2 != null) {
            if (iterL1.val < iterL2.val) {
                iter.next = iterL1;
                iterL1 = iterL1.next;
            } else {
                iter.next = iterL2;
                iterL2 = iterL2.next;
            }

            iter = iter.next;
        }

        if (iterL1 != null) {
            iter.next = iterL1;
        }

        if (iterL2 != null) {
            iter.next = iterL2;
        }

        return head;
    }

    private void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        P21 x = new P21();
        ListNode node1L1 = new ListNode(1);
        ListNode node2L1 = new ListNode(2);
        ListNode node4L1 = new ListNode(4);
        node1L1.next = node2L1;
        node2L1.next = node4L1;
        node4L1.next = null;
        x.print(node1L1);

        ListNode node1L2 = new ListNode(1);
        ListNode node3L2 = new ListNode(3);
        ListNode node4L2 = new ListNode(4);
        node1L2.next = node3L2;
        node3L2.next = node4L2;
        node4L2.next = null;
        x.print(node1L2);

        ListNode head = x.mergeTwoLists(node1L1, node1L2);
        x.print(head);
    }
}
