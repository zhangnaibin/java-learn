package com.znb.java.learn.leecode;

public class P108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) {
            return null;
        }

        return sortToBST(nums, 0 , nums.length - 1);
    }

    public TreeNode sortToBST(int[] nums, int low, int high) {
        if (high < low) {
            high = low;
        }
        if (low >= high) {
            return new TreeNode(nums[low]);
        } else {
            int mid = (low + high) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            TreeNode left = sortToBST(nums, low, mid - 1);
            TreeNode right = sortToBST(nums,mid + 1, high);
            root.right = right;
            root.left = left;
            return left;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{-10, -3, 0, 5, 9};
        P108 x = new P108();
        TreeNode node = x.sortedArrayToBST(array);
        System.out.println(node);
    }
}
