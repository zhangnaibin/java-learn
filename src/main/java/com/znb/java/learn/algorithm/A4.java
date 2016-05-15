package com.znb.java.learn.algorithm;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 输入一个整数和一棵二元树。从树的根结点开始往下访问
 * 一直到叶结点所经过的所有结点形成一条路径。打印出和与输入整数相等的所有路径
 *
 * see http://zhedahht.blog.163.com/blog/static/254111742007228357325/
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-14 下午6:14
 */
public class A4 {
    public static class Node {
        int key;
        Node left;
        Node right;
    }

    // 需要一直记录当前值得和
    public void printPath(Node node, int num, AtomicInteger curSum, Stack<Integer> stack) {
        if (node == null) {
            return ;
        }

        curSum.getAndAdd(node.key);
        stack.push(node.key);

        if (node.left == null && node.right == null) {
            if (curSum.get() + node.key == num) {
                stack.push(node.key);
                System.out.println(stack.toString());
            }
        }

        if (node.left != null) {
            printPath(node.left, num, curSum, stack);
        }
        if (node.right != null) {
            printPath(node.right, num, curSum, stack);
        }

        // 访问完一个节点，返回父节点时，从路径中删除这个节点
        curSum.getAndSet(curSum.get() - node.key);
        stack.pop();
    }

    public void printPath2(Node node, int num, AtomicInteger curNum, Stack<Integer> stack) {
        if (node == null) {
            return ;
        }

        if (node.key + curNum.get() == num) {
            if (node.left == null && node.right == null) {
                stack.push(node.key);
                System.out.println(stack.toString());
                stack.pop();
                return ;
            } else {
                return ;
            }
        } else if (node.key + curNum.get() > num) {
            return ;
        } else {
            stack.push(node.key);
            curNum.getAndAdd(node.key);
            if (node.left != null) {
                printPath2(node.left, num, curNum, stack);
            }
            if (node.right != null) {
                printPath2(node.right, num, curNum, stack);
            }
            stack.pop();
            curNum.getAndSet(curNum.get() - node.key);
            return ;
        }
    }

    // Integer 的值是final的，不会发生改变的
    public static void integerTest(Integer i) {
        i = new Integer(23);
    }

    public static void nodeTest(Node node) {
        node.key = 16;
    }
    public static void main(String[] args) {
        Integer i = new Integer(1);
        integerTest(i);
        System.out.println(i);
        Node node = new Node();
        node.key = 1;
        nodeTest(node);
        System.out.println(node.key);
    }
}
