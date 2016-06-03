package com.znb.java.learn.datastruct;

/**
 * see http://blog.csdn.net/v_july_v/article/details/6530142
 * BTree, Balanced Tree, 平衡树,多路平衡树
 *
 * 用阶定义: 一个m阶的B树特征如下：
 *      1、树种每个节点最多包含m个孩子(m>=2)
 *      2、除根节点和叶子节点外，其他的每个节点至少有[ceil(m/2)]个孩子，ceil(x)是一个取上限函数
 *      3、若根节点不是叶子节点，则至少有2个孩子(特殊情况: 没有孩子的根节点，只有一个节点)
 *      4、所有叶子节点都出现在同一层，叶子节点不包含任何关键字信息
 *      5、每个非终端节点包含有n个关键字信息，这些关键字按递增顺序排序
 *
 * 用度定义：
 * @author zhangnaibin@xiaomi.com
 * @time 2016-06-02 上午10:36
 */
public class BTreeTest {
    class BTNode<T> {
        int keyNum; // 实际关键字个数
        BTNode parent; // 指向父节点
        BTNode[] prt; // 子树指针向量
        T[] key; // 关键字向量
    }
}
