package com.znb.java.learn.algorithm;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 输入一颗二元树，从上往下按层打印树的每个结点，同一层中按照从左往右的顺序打印
 *
 * see http://zhedahht.blog.163.com/blog/static/2541117420072199173643/
 *
 * 二叉树层次遍历,借助队列实现
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-15 下午4:42
 */
public class A12 {
    public static class Node{
        int key;
        Node left;
        Node right;
    }

    public void printByLevel(Node node) {
        if (null == node) {
            return;
        }
        Queue<Node> queue = new ConcurrentLinkedQueue<Node>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            System.out.println(temp.key);
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
    }
}
