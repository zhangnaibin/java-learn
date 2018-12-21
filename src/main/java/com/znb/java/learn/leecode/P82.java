package com.znb.java.learn.leecode;

public class P82 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 先处理最前面相同
        ListNode pre = new ListNode(0);
        ListNode newHead = pre;
        ListNode cur = head;
        ListNode preCur = null;
        // 当结点与前后值都不同时，才会在最终的链表中出现
        while (cur.next != null) {
            if (preCur != null) {
                if (cur.val != preCur.val  && cur.val != cur.next.val) {
                    pre.next = cur;
                    pre = pre.next;
                }
            } else {
                if (cur.val != cur.next.val) {
                    pre.next = cur;
                    pre = pre.next;
                }
            }
            preCur = cur;
            cur = cur.next;
        }

        if (preCur.val == cur.val) {
            pre.next = null;
        } else {
            pre.next = cur;
        }

        return newHead.next;
    }

    private void test1() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(4);
        ListNode node7 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = null;
        ListNode.print(node1);

        ListNode head = deleteDuplicates(node1);
        ListNode.print(head);
    }

    private void test2() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;
        ListNode.print(node1);

        ListNode head = deleteDuplicates(node1);
        ListNode.print(head);
    }

    private void test3() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;
        ListNode.print(node1);

        ListNode head = deleteDuplicates(node1);
        ListNode.print(head);
    }
    public static void main(String[] args) {
//        new P82().test2();
        new P82().test3();
    }
}
