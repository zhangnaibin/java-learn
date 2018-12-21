package com.znb.java.learn.leecode;

public class P160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int lengthA = 0;
        ListNode curA = headA;
        while (curA != null) {
            lengthA ++;
            curA = curA.next;
        }

        int lengthB = 0;
        ListNode curB = headB;
        while (curB != null) {
            lengthB ++;
            curB = curB.next;
        }

        int gap;
        ListNode preMove;
        ListNode afterMove;
        if (lengthA > lengthB) {
            gap = lengthA - lengthB;
            preMove = headA;
            afterMove = headB;
        } else {
            gap = lengthB - lengthA;
            preMove = headB;
            afterMove = headA;
        }

        while (gap > 0) {
            preMove = preMove.next;
            gap --;
        }

        while (preMove != null && afterMove != null) {
            if (preMove == afterMove) {
                return preMove;
            } else {
                preMove = preMove.next;
                afterMove = afterMove.next;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;
        ListNode.print(node1);

        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        ListNode node8 = new ListNode(8);
        node6.next = node7;
        node7.next = node8;
        node8.next = node4;
        ListNode.print(node6);

        P160 x = new P160();
        ListNode node = x.getIntersectionNode(node1, node8);
        ListNode.print(node);
    }
}
