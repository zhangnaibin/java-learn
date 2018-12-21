package com.znb.java.learn.leecode;

public class P23 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        return new P21().mergeTwoLists(l1, l2);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return mergeKLists(lists, 0 , lists.length - 1);
    }

    private ListNode mergeKLists(ListNode[] lists, int begin, int end) {
        if (begin == end) {
            return lists[begin];
        }
        int middle = (begin + end) / 2;
        ListNode left = mergeKLists(lists, begin, middle);
        ListNode right = mergeKLists(lists, middle + 1, end);
        return mergeTwoLists(left, right);
    }

    private void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        P23 x = new P23();
        ListNode node1L1 = new ListNode(1);
        ListNode node4L1 = new ListNode(4);
        ListNode node5L1 = new ListNode(5);
        node1L1.next = node4L1;
        node4L1.next = node5L1;
        node5L1.next = null;
        x.print(node1L1);

        ListNode node1L2 = new ListNode(1);
        ListNode node3L2 = new ListNode(3);
        ListNode node4L2 = new ListNode(4);
        node1L2.next = node3L2;
        node3L2.next = node4L2;
        node4L2.next = null;
        x.print(node1L2);

        ListNode node2L3 = new ListNode(2);
        ListNode node6L3 = new ListNode(6);
        node2L3.next = node6L3;
        node6L3.next = null;
        x.print(node2L3);

        ListNode[] lists = new ListNode[3];
        lists[0] = node1L1;
        lists[1] = node1L2;
        lists[2] = node2L3;

        ListNode head = x.mergeKLists(new ListNode[0]);
        x.print(head);
    }
}
