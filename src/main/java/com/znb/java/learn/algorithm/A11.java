package com.znb.java.learn.algorithm;

/**
 * 翻转二叉树
 *
 * 输入一颗二元查找树，将该树转换为它的镜像，即在转换后的二元查找树中，
 * 左子树的结点都大于右子树的结点。用递归和循环两种方法完成树的镜像转换
 *
 * see http://zhedahht.blog.163.com/blog/static/2541117420072159363370/
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-15 下午4:37
 */
public class A11 {
    public static class Node {
        int key;
        Node left;
        Node right;
    }

    public void reserveTree(Node node) {
        if (node == null) {
            return;
        }
        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
        if (null != node.left) {
            reserveTree(node.left);
        }
        if (null != node.right) {
            reserveTree(node.right);
        }
    }
}
