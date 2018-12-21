package com.znb.java.learn.leecode;

import java.util.*;

public class P103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> curQueue = new LinkedList<>();
        Queue<TreeNode> nextQueue = new LinkedList<>();
        curQueue.add(root);
        List<Integer> layerList = new ArrayList<>();
        int rowNum = 1;
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
                if (rowNum % 2 == 0) {
                    Stack<Integer> stack = new Stack<>();
                    layerList.forEach(stack::push);
                    List<Integer> tempList = new ArrayList<>();
                    while (!stack.isEmpty()) {
                        tempList.add(stack.pop());
                    }
                    result.add(tempList);
                } else {
                    result.add(new ArrayList<>(layerList));
                }
                layerList.clear();
                rowNum ++;
            }
        }

        return result;
    }
}
