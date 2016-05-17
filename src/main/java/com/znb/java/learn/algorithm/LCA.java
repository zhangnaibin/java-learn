package com.znb.java.learn.algorithm;

/**
 * 求有根树的任意两个节点的最近公共祖先。
 *
 * 解答这个问题之前，咱们得先搞清楚到底什么是最近公共祖先。最近公共祖先简称LCA（Lowest Common Ancestor），
 * 所谓LCA，是当给定一个有根树T时，对于任意两个结点u、v，找到一个离根最远的结点x，使得x同时是u和v的祖先，
 * x 便是u、v的最近公共祖先。（参见：http://en.wikipedia.org/wiki/Lowest_common_ancestor ）原问题涵盖一般性的有根树，
 * 本文为了简化，多使用二叉树来讨论。
 *
 * see https://github.com/julycoding/The-Art-Of-Programming-By-July/blob/master/ebook/zh/03.03.md
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-17 上午10:26
 */
public class LCA {
    public static class Node {
        int key;
        Node left;
        Node right;
    }
    // 解法一，暴力对待。当这棵树是二叉查找树时
    public Node getLCA1(Node root, Node u, Node v) {
        if (root == null || u == null || v == null) {
            return null;
        }
        int left, right;
        if (u.key < v.key) {
            left = u.key;
            right = v.key;
        } else {
            left = v.key;
            right = u.key;
        }

        Node temp = root;
        while (temp != null) {
            // 大于最大值，左子树寻找
            if (temp.key > right) {
                temp = temp.left;
            } else {
                // 小于最小值，右子树寻找
                if (temp.key < left) {
                    temp = temp.right;
                } else {
                    // 位于两者中间，返回
                    return temp;
                }
            }
        }
        // 节点不是树上节点，返回null
        return null;
    }

    // 解法二，如果节点有指向父节点的指针，问题变为两个链表求第一个交点

    // 解法三，递归解决
    // 在当前节点的左右子树寻找u、v节点。找到时当前节点就是LCA节点，否则递归。
    public Node getLCA2(Node root, Node u, Node v) {
        if (root == null) {
            return null;
        }
        if (root == u || root == v) {
            return root;
        }
        Node left = getLCA2(root.left, u, v);
        Node right = getLCA2(root.right, u, v);
        if (left != null && right != null) {
            return  root;
        } else {
            if (left != null) {
                return left;
            } else {
                if (right != null) {
                    return right;
                } else {
                    return null;
                }
            }
        }
    }

    // 解法四，批量查询的离线算法，Tarjan算法。
    // 解法五，转换为RMQ算法
}
