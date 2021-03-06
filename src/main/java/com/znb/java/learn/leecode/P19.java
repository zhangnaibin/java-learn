package com.znb.java.learn.leecode;

public class P19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (n > 0) {
            fast = fast.next;
            n --;
        }

        // 删除的是头结点
        if (fast == null) {
            return head.next;
        }

        ListNode slowPre = slow;
        while (fast != null) {
            fast = fast.next;
            slowPre = slow;
            slow = slow.next;
        }

        slowPre.next = slow.next;
        return head;

    }

    private void print(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
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
        P19 x = new P19();
        ListNode head = x.removeNthFromEnd(node1, 5);
        x.print(head);
    }

}



