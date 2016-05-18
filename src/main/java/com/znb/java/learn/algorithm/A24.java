package com.znb.java.learn.algorithm;

import java.util.Stack;

/**
 * 输入两个整数序列。其中一个序列表示栈的push顺序，判断另一个序列有没有可能是对应的pop顺序。
 * 为了简单起见，我们假设push序列的任意两个整数都是不相等的。
 *
 * see http://zhedahht.blog.163.com/blog/static/25411174200732102055385/
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-15 下午6:27
 */
public class A24 {

    public boolean isPossiblePopOrder(int[] push, int[] pop, int length) {
        if (push == null || pop == null || length <= 0) {
            return false;
        }
        boolean isPossible = false;
        int popIndex = 0, pushIndex = 0;
        Stack<Integer> stack = new Stack<Integer>();
        // 正常是数据全部出来完成退出，还有是顺序不对退出
        while (popIndex < length) {
            // 栈顶元素不是pop数据时，一直push数据
            while (stack.isEmpty() || stack.peek() != pop[popIndex]) {
                // 数据都push进去了，停止push数据
                if (pushIndex == length) {
                    break;
                }
                stack.push(push[pushIndex++]);
            }
            // push停止后，栈顶值不是pop值，直接退出
            if (stack.peek() != pop[popIndex]) {
                break;
            }
            stack.pop();
            popIndex ++;
        }
        // pop列表中所有值都验证过了，ok
        if (stack.isEmpty() && popIndex == length) {
            isPossible = true;
        }
        return isPossible;
    }

}
