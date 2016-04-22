package com.znb.java.learn.datastruct;

/**
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-21 下午9:20
 */
public class SortAlgorithm {

    // 一个有序表和一个无序表。每次从无序表中取出第一个元素，将它插入到有序表中的适当位置，使之成为新的有序表
    public void insertSort(int[] a) {
        for (int i = 1; i < a.length; i ++) {
            // 在a[i]前的a[0...j-1]有序区间找一个位置
            int j;
            for (j = i - 1; j >= 0; j --) {
                if (a[j] < a[i]) {
                    break;
                }
            }
            // 找到了合适的位置，需要移动数据
            if (j != i - 1) {
                int temp = a[i];
                // 将比a[i]大的值后移
                for (int k = i - 1; k > j; k --) {
                    a[k + 1] = a[k];
                }
                // a[j]比a[i]小，应该放在他后面
                a[j + 1] = temp;
            }
        }

        for (int i = 0; i < a.length; i ++) {
            System.out.println(a[i]);
        }
    }

    // 有标记位的冒泡排序,标记最后一次
    public void bubbleSort(int[] a, int n) {
        int flag, times = 0;
        for (int i = n -1; i > 0; i --) {
            times ++;
            flag = 0;
            for (int j = 0; j < i; j ++) { // 每次排序后，最后的数据已经ok了，不用再排序了
                // 将a[0]...a[i]中最大数据放在最后
                if (a[j] > a[j + 1]) {
                    int temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                    flag = 1; // 有交换发生，标记
                }
            }
            if (flag == 0) {
                break; // 一次的排序中没有发生交换，说明前面都有序了，直接结束
            }
        }

        for (int i = 0; i < n; i ++) {
            System.out.println(a[i]);
        }
        System.out.println("op times :" + times);
    }

    // 快排,low为左边界，high为右边界
    public void quickSortCell(int[] a, int low, int high) {
        if (low < high) {
            int lowIndex = low, highIndex = high;
            int mark = a[lowIndex];
            while (lowIndex < highIndex) {
                while (lowIndex < highIndex && a[highIndex] > mark) {
                    highIndex --;
                }
                if (lowIndex < highIndex) {
                    a[lowIndex ++] = a[highIndex];
                }
                while (lowIndex < highIndex && a[lowIndex] < mark) {
                    lowIndex ++;
                }
                if (lowIndex < highIndex) {
                    a[highIndex --] = a[lowIndex];
                }
            }
            a[lowIndex] = mark;
            quickSortCell(a, low, lowIndex - 1);
            quickSortCell(a, lowIndex + 1, high);
        }
    }

    // 快速排序
    public void quickSort(int[] a) {
        quickSortCell(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i ++) {
            System.out.println(a[i]);
        }
    }

    // 选择排序
    public void selectSort(int[] a) {
        int mixIdx;
        for (int i = 0; i < a.length; i ++) {
            mixIdx = i;
            // 找出a[i+1,...n]的最小元素，坐标给minIdx
            for (int j = i + 1; j < a.length; j ++) {
                if (a[j] < a[mixIdx]) {
                    mixIdx = j;
                }
            }
            // 交换数据，保证a[0...i]区数据有序
            if (mixIdx != i) {
                int temp = a[i];
                a[i] = a[mixIdx];
                a[mixIdx] = temp;
            }
        }

        for (int i = 0; i < a.length; i ++) {
            System.out.println(a[i]);
        }
    }

    // 希尔排序
    public void shellSort(int[] a) {
        // 步长每次减半
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            // 有gap个分组，每个分组都进行直接插入排序
            for (int i = 0; i < gap; i ++) {
                groupSort(a, a.length, i, gap);
            }
        }
        for (int i = 0; i < a.length; i ++) {
            System.out.println(a[i]);
        }
    }

    // 对shell排序中的单个组排序,就是一次插入排序的执行
    public void groupSort(int a[], int leng, int start, int gap) {
        for (int j = start + gap; j < leng; j += gap) {
            // a[j]前面为有序的数组
            if (a[j] < a[j - gap]) {
                int temp = a[j];
                int k = j - gap;
                while (k >0 && a[k] > temp) {
                    a[k + gap] = a[k];
                    k -= gap;
                }
                a[k + gap] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {3, 1, 99, 55, 77, 110, 5, 10086, 65};
//        new SortAlgorithm().bubbleSort(a, a.length);
//        new SortAlgorithm().quickSort(a);
//        new SortAlgorithm().insertSort(a);
//        new SortAlgorithm().selectSort(a);
//        new SortAlgorithm().shellSort(a);
    }
}
