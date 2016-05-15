package com.znb.java.learn.algorithm;

/**
 * 输入一棵二元查找树，将该二元查找树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只调整指针的指向
 *
 * see http://zhedahht.blog.163.com/blog/static/254111742007127104759245/
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-14 上午7:28
 */
public class A1 {
    public static class Node {
        int key;
        Node left;
        Node right;
    }

    // 对跟节点，isLeft控制想要那个头的指针
    // 把一个节点下的子节点组织成双向链表，该节点为左节点是返回tail，右节点时返回head
    public Node buildList(Node node, boolean isLeft) {
        if (null == node) {
            return null;
        }
        Node left = null;
        if (node.left != null) {
            left = buildList(node.left, true);
        }
        node.left = left;
        if (left != null) {
            left.right = node;
        }

        Node right = null;
        if (node.right != null) {
            right = buildList(node.right, false);
        }

        node.right = right;
        if (right != null) {
            right.left = node;
        }

        Node temp = node;
        if (isLeft) {
            while (temp.right != null) {
                temp = temp.right;
            }
        } else {
            while (temp.left != null) {
                temp = temp.left;
            }
        }
        return temp;
    }

    // 中序遍历
    public Node convert(Node node) {
        if (null == node) {
            return null;
        }
        return null;
    }
}
