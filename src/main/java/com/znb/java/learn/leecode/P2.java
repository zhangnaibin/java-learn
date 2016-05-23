package com.znb.java.learn.leecode;

/**
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-22 下午4:38
 */
public class P2 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode result = l1;
        boolean mark = false;
        ListNode pre = null;
        ListNode temp = l1;
        while (l1 != null && l2 != null) {
            pre = l1;
            int val;
            if (mark) {
                val = l1.val + l2.val + 1;
                mark = false;
            } else {
                val = l1.val + l2.val;
            }
            if (val >= 10) {
                val = val % 10;
                mark = true;
            }
            l1.val = val;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {

        }

        return result;
    }
}
