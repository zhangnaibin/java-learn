package com.znb.java.learn.algorithm;

/**
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * see http://zhedahht.blog.163.com/blog/static/2541117420071128950682/
 *
 * 简化：只有一个数字出现一次（一次异或的结果就是这个值）
 * 变种：有3个数字只出现了一次
 *
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-13 下午7:26
 */
public class A34 {

    // ************************** 两个数组出现一次 ****************************
    // 找出数字中第一个为1的bit位
    private int findFirstBitIs1(int num) {
        int indexBit = 0;
        while (((num & 1) == 0) && (indexBit < 32)) {
            num = num >> 1;
            ++ indexBit;
        }
        return indexBit;
    }

    private boolean isBit1(int num, int indexBit) {
        num = num >> indexBit;
        return (num & 1) == 1;
    }

    public void findNumAppearOnce(int data[]) {
        if (null == data) {
            return ;
        }

        int length = data.length;
        if (length < 2) {
            return ;
        }

        int resultExclusiveOR = 0;
        for (int i = 0; i < length; i ++) {
            resultExclusiveOR ^= data[i];
        }

        int indexOf1 = findFirstBitIs1(resultExclusiveOR);
        int num1 = 0, num2 = 0;
        for (int i = 0; i < length; i ++) {
            // 因为其他数字都出现了两次，所以他们会根据indexOf1的值进行分组，并且都会在同一组
            if (isBit1(data[i], indexOf1)) {
                num1 ^= data[i];
            } else {
                num2 ^= data[i];
            }
        }

        System.out.println(num1);
        System.out.println(num2);
    }

    // ************************** 两个数组出现一次 ****************************

    public static void main(String[] args) {
        int[] data = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 9};
        new A34().findNumAppearOnce(data);
    }

}
