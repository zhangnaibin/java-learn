package com.znb.java.learn.leecode;

public class P148 {
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        ListNode highHead = getHighListHead(head);
        ListNode sortHighHead = sortList(highHead);
        ListNode sortLowHead = sortList(head);
        return mergeSortedList(sortLowHead, sortHighHead);
    }

    public ListNode getHighListHead(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode leftHead = slow.next;
        slow.next = null;
        return leftHead;

    }

    public ListNode mergeSortedList(ListNode l1, ListNode l2) {
//        return new P21().mergeTwoLists(l1, l2);
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

    private void test1() {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;

        ListNode.print(node1);
        ListNode head = sortList(node1);
        ListNode.print(head);
    }

    private void test2() {
        ListNode node1 = new ListNode(-1);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(0);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;
        ListNode.print(node1);
        ListNode head = sortList(node1);
        ListNode.print(head);
    }

    public static void main(String[] args) {
//        new P148().test1();
        new P148().test2();
    }
}
