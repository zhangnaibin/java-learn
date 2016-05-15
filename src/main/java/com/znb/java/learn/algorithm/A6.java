package com.znb.java.learn.algorithm;

/**
 * 输入一个整数数组，判断该数组是不是某二元查找树的后序遍历的结果。如果是返回true，否则返回false
 * 后序遍历：左右根的顺序遍历
 * 在后续遍历得到的序列中，最后一个元素为树的根结点。从头开始扫描这个序列，
 * 比根结点小的元素都应该位于序列的左半部分；从第一个大于跟结点开始到跟结点前面的一个元素为止，
 * 所有元素都应该大于跟结点，因为这部分元素对应的是树的右子树。根据这样的划分，把序列划分为左右两部分，
 * 我们递归地确认序列的左、右两部分是不是都是二元查找树
 *
 * Java不支持引用，直接将数组分为两部分
 *
 * see http://zhedahht.blog.163.com/blog/static/25411174200725319627/
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-15 下午2:28
 */
public class A6 {

    public boolean verifySquenceOfBST(int squence[], int length) {
        if (null == squence || length <= 0) {
            return false;
        }
        int root = squence[length - 1];
        // 将数组分为左子树部分和右子树部分， i 为右子树部门第一个
        int i = 0;
        for (;i < length; i ++) {
            if (squence[i] > root) {
                break;
            }
        }
        // 验证右子树部分是否都大于根
        int j = i;
        for (; j < length; j ++) {
            if (squence[j] < root) {
                return false;
            }
        }

        boolean left = true;
        if (i > 0) {
            left = verifySquenceOfBST(squence, i);
        }

        boolean right = true;
        if (i < length - 1) {
            // Java 不支持引用
//            right = verifySquenceOfBST(squence + i, length - i - 1);
        }

        return left && right;
    }
}
