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
        ListNode temp = null;
        while (l1 != null && l2 != null) {
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
            temp = l1;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int val;
            if (mark) {
                val = l1.val + 1;
                mark = false;
            } else {
                val = l1.val;
            }
            if (val >= 10) {
                val = val % 10;
                mark = true;
            }
            l1.val = val;
            temp = l1;
            l1 = l1.next;
        }

        if (l2 != null) {
            temp.next = l2;
        }
        while (l2 != null) {
            int val;
            if (mark) {
                val = l2.val + 1;
                mark = false;
            } else {
                val = l2.val;
            }
            if (val >= 10) {
                val = val % 10;
                mark = true;
            }
            l2.val = val;
            temp = l2;
            l2 = l2.next;
        }

        if (l1 == null && l2 == null && mark) {
            ListNode node = new ListNode(1);
            node.next = null;
            temp.next = node;
        }

        return result;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
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
        ListNode temp = null;
        while (l1.next != null && l2.next != null) {
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
            temp = l1;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1.next != null) {
            int val;
            if (mark) {
                val = l1.val + 1;
                mark = false;
            } else {
                val = l1.val;
            }
            if (val >= 10) {
                val = val % 10;
                mark = true;
            }
            l1.val = val;
            temp = l1;
            l1 = l1.next;
        }

        if (l2.next != null) {
            temp.next = l2;
        }
        while (l2 != null) {
            int val;
            if (mark) {
                val = l2.val + 1;
                mark = false;
            } else {
                val = l2.val;
            }
            if (val >= 10) {
                val = val % 10;
                mark = true;
            }
            l2.val = val;
            temp = l2;
            l2 = l2.next;
        }

        if (l1 == null && l2 == null && mark) {
            ListNode node = new ListNode(1);
            node.next = null;
            temp.next = node;
        }

        return result;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(9);
        ListNode node3 = new ListNode(9);
        node2.next = node3;
        ListNode result = new P2().addTwoNumbers(node1, node2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
