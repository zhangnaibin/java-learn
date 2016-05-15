package com.znb.java.learn.algorithm;

/**
 * 给定链表的头指针和一个结点指针，在O(1)时间删除该结点。
 * see http://zhedahht.blog.163.com/blog/static/254111742007112255248202/
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-13 下午8:08
 */
public class A33 {
    public static class Node {
        int key;
        Node next;
    }

    public void deleteNode(Node head, Node toDelete) {
        if (null == head || null == toDelete) {
            return ;
        }
        // 删除节点就是头节点
        if (toDelete == head) {
            head = null;
        }

        Node temp = head;
        // 删除节点是最后节点
        if (toDelete.next == null) {
            while (temp.next != toDelete) {
                temp = temp.next;
            }
            temp.next = null;
            return ;
        } else {
            // 将删除节点后面节点数据复制到自己，删除后面节点
            temp = toDelete.next;
            toDelete.key = temp.key;
            toDelete.next = temp.next;
            return ;
        }
    }

    // 时间复杂度
    // 假设链表总共有n个结点，我们的算法在n-1总情况下时间复杂度是O(1)，
    // 只有当给定的结点处于链表末尾的时候，时间复杂度为O(n)。
    // 那么平均时间复杂度[(n-1)*O(1)+O(n)]/n，仍然为O(1)
}
