package com.znb.java.learn.algorithm;

/**
 * 输入一棵二元树的根结点，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 *
 * see http://zhedahht.blog.163.com/blog/static/25411174200732975328975/
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-18 下午4:41
 */
public class A27 {
    public static class Node {
        int key;
        Node left;
        Node right;
    }
    public int getTreeDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int left = getTreeDepth(root.left);
        int right = getTreeDepth(root.right);
        if (left > right) {
            return left + 1;
        } else {
            return right + 1;
        }
    }
}
