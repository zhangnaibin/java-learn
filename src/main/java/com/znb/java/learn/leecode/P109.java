package com.znb.java.learn.leecode;

/**
 * TODO
 * 有序链表转换二叉搜索树
 */
public class P109 {

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return new TreeNode(head.val);
        }

        ListNode afterHead = splitList(head);
        if (afterHead == null) {
            return null;
        }

        TreeNode root = new TreeNode(afterHead.val);
        TreeNode left = sortedListToBST(head);
        TreeNode right = sortedListToBST(afterHead.next);
        root.left = left;
        root.right = right;
        return root;
    }

    // 将链表分为两部分left和right两部分，保证length(right) >= length(left)
    // 因为会从right选第一个节点作为root
    public ListNode splitList(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode fast = head.next;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode newHead = slow.next;
        slow.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(-10);
        ListNode node2 = new ListNode(-3);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(9);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;
        ListNode.print(node1);

        P109 x = new P109();
        TreeNode node = x.sortedListToBST(node1);
        System.out.println(node);
    }
}
