package com.znb.java.learn.leecode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> curQueue = new LinkedList<>();
        Queue<TreeNode> nextQueue = new LinkedList<>();
        curQueue.add(root);
        List<Integer> layerList = new ArrayList<>();
        while (!curQueue.isEmpty()) {
            TreeNode node = curQueue.poll();
            layerList.add(node.val);
            if (node.left != null) {
                nextQueue.add(node.left);
            }

            if (node.right != null) {
                nextQueue.add(node.right);
            }

            if (curQueue.isEmpty()) {
                Queue<TreeNode> temp = nextQueue;
                nextQueue = curQueue;
                curQueue = temp;
                result.add(new ArrayList<>(layerList));
                layerList.clear();
            }
        }

        return result;
    }
}