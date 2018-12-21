package com.znb.java.learn.datastruct.tree;

import com.znb.java.learn.leecode.ListNode;

/**
 * 二叉搜索树
 */
public class BinarySearchTree {
    static class BSTNode {
        private int data;
        public BSTNode left;
        public BSTNode right;

        public BSTNode(int data) {
            this.data = data;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return "BSTNode{" +
                    "data=" + data +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    // TODO
    public BSTNode createBST(int[] nums) {
        BSTNode root = null;
        for (int i = 0; i < nums.length; i++) {
            root = insertNode(root, nums[i]);
        }
        return root;
    }

    // TODO
    public BSTNode insertNode(BSTNode root, int data) {
        BSTNode node = new BSTNode(data);
        if (root == null) {
            root = node;
        } else {
            BSTNode iter = root;
            while (iter.data != data) {
                // 插入左子树
                if (iter.data > data) {
                    if (iter.left == null) {
                        iter.left = node;
                        break;
                    } else {
                        iter = iter.left;
                    }
                } else {
                    // 插入右子树
                    if (iter.right == null) {
                        iter.right = node;
                        break;
                    } else {
                        iter = iter.right;
                    }
                }
            }
        }
        return root;
    }

    // TODO
    public BSTNode searchMin(BSTNode root) {
        if (root == null) {
            return null;
        }

        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    // TODO
    public BSTNode searchMax(BSTNode root) {
        if (root == null) {
            return null;
        }

        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    // TODO 递归版
    public BSTNode search1(BSTNode root, int data) {
        if (root == null) {
            return null;
        }

        if (root.data == data) {
            return root;
        }

        if (root.data < data) {
            return search1(root.right, data);
        } else {
            return search1(root.left, data);
        }
    }

    // TODO 迭代版
    public BSTNode search2(BSTNode root, int data) {
        while (root != null && root.data != data) {
            if (data < root.data) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return root;
    }

    // TODO
    public void preOrderTraverse(BSTNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " -> ");
        preOrderTraverse(root.left);
        preOrderTraverse(root.right);
    }

    public void midOrderTraverse(BSTNode root) {
        if (root == null) {
            return;
        }
        midOrderTraverse(root.left);
        System.out.print(root.data + " -> ");
        midOrderTraverse(root.right);
    }

    public void postOrderTraverse(BSTNode root) {
        if (root == null) {
            return;
        }
        postOrderTraverse(root.left);
        postOrderTraverse(root.right);
        System.out.print(root.data + " -> ");
    }

    // 完全删除一个节点，必须有它的父节点
    // 返回树的root
//    public BSTNode deleteNode(BSTNode root, int data) {
//        if (root == null) {
//            return null;
//        }
//
//        BSTNode parent = null;
//        boolean parentLeft = true;
//        BSTNode node = root;
//        while (node != null && node.data != data) {
//            if (node.data > data) {
//                parent = node;
//                node = node.left;
//            } else {
//                parent = node;
//                parentLeft = false;
//                node = node.right;
//            }
//        }
//
//        // 没有找到删除节点
//        if (node == null) {
//            return;
//        }
//
//        if ()
//
//        if (root.data == data) {
//            if (root.left == null && root.right == null) {
//                root = null;
//                return;
//            }
//
//            if (root.left != null) {
//                root = root.left;
//            }
//
//            if (root.right != null) {
//                root = root.right;
//            }
//
//            BSTNode iter = root.right;
//            while (iter.left != null) {
//                iter = iter.left;
//            }
//            root.data = iter.data;
//            iter = iter.right;
//        } else {
//            if (root.data > data) {
//                deleteNode(root.left, data);
//            } else {
//                deleteNode(root.right, data);
//            }
//        }
//    }

    public static void main(String[] args) {
        int[] array = new int[]{10, 6, 4, 8, 7, 15, 13, 14, 18, 17, 19};
        BinarySearchTree t = new BinarySearchTree();
        BSTNode root = t.createBST(array);
//        t.preOrderTraverse(root);
//        System.out.println();
//        t.midOrderTraverse(root);
//        System.out.println(t.search1(root, 8).toString());
//        System.out.println(t.search2(root, 15).toString());
//        System.out.println(t.searchMax(root));
//        System.out.println(t.searchMin(root));
//        t.deleteNode(root, 4);
        System.out.println(root);

    }
}
