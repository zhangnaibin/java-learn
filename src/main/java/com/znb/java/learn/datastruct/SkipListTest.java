package com.znb.java.learn.datastruct;


import java.util.Random;

/**
 * 跳表,使用概率均衡技术而不是强制性均衡，对于插入和删除节点比传统上的平衡树算法更为简洁高效
 * 不支持相同的key值处理
 *
 * @author zhangnaibin@xiaomi.com
 * @time 2016-06-01 上午10:53
 */
public class SkipListTest {
    /**
     * skip list定义：
     * 一个跳表，应该具有以下特征：
     * 1、一个跳表应该由几个层(level)组成
     * 2、跳表的第零层包含所有的元素
     * 3、每一层都是一个有序的链表
     * 4、如果元素x出现在第i层，则所有比i小的层都包含x
     */

    /**
     * skip list构造步骤：
     * 1、给定一个有序的链表
     * 2、选择链表中的最大和最小的元素，然后从其他元素中按照一定算法(随机)选出一些元素，
     * 将这些元素组成有序的链表。这个心得链表称为一层，原链表称为下一层。
     * 3、为刚选出的每个元素添加一个指针域，这个指针指向下一层中值同自己相等的元素。Top
     * 指针指向改层首元素
     * 4、重复2、3步，知道不在选择出除最大最小元素以外的元素
     */

    class SkipListNode {
        int key;
        int value;
        SkipListNode[] forward;

        public SkipListNode(int key, int value) {
            this.key = key;
            this.value = value;
            forward = new SkipListNode[MAX_LEVEL];
        }
    }

    int level; // 跳表的当前最大level(包含), 当前有0 ~ level 总共level + 1 层
    SkipListNode header; // 跳表首节点
    private final int MAX_LEVEL; // level数量上限, 实际level范围为0 ~~ MAX_LEVEL - 1
    private static final Random RANDOM = new Random();
    private SkipListNode NULL = new SkipListNode(Integer.MAX_VALUE, Integer.MAX_VALUE);

    public SkipListTest(int level) {
        MAX_LEVEL = level;

        // 头节点包含了允许的所有level，这个是最大允许值。实际中可以使用不到
        header = new SkipListNode(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (int i = 0; i < MAX_LEVEL; i ++) {
            header.forward[i] = NULL;
        }
    }

    // 判断一个节点是否存在跳表中
    public boolean exist(int key, int value) {
        SkipListNode temp = header;
        for (int i = level; i >= 0; i --) {
            while (temp.forward[i].key < key) {
                temp = temp.forward[i];
            }
            // 在较高level找到了，直接返回true
            if (temp.forward[i].key == key) {
                return true;
            }
        }
        if (temp.forward[0].key == key) {
            return true;
        }
        return false;
    }

    // 多层的单链表插入,保存插入节点前的节点
    public boolean insert(int key, int value) {
        int level = getLevel();
        // 当此时的level高于当前值时，对当前level加1处理，整体level增加1
        if (level > this.level) {
            level = this.level + 1;
        }

        SkipListNode[] pre = new SkipListNode[level];
        SkipListNode temp = header;
        for (int i = level; i >= 0; i --) {
            while (temp.forward[i].key < key) {
                temp = temp.forward[i];
            }
            pre[i] = temp;
        }
        // 移到0 level的后面节点主要进行判断
        temp = temp.forward[0];
        if (temp.key == key) {
            temp.value = value;
            return false;
        } else {
            // 当level增加时更新原level，保存前节点(head)
            if (level > this.level) {
                this.level = level;
                pre[level] = header;
            }

            SkipListNode node = new SkipListNode(key, value);
            // 值更新level及以下的层次节点，高的部分不处理
            for (int i = level; i >= 0; i --) {
                temp = pre[i];
                node.forward[i] = temp.forward[i];
                temp.forward[i] = node;
            }
            return true;
        }
    }

    // 删除值
    public Integer delete(int key) {
        SkipListNode[] pre = new SkipListNode[MAX_LEVEL];
        SkipListNode temp = header;
        for (int i = level; i >= 0; i --) {
            while (temp.forward[i].key < key) {
                temp = temp.forward[i];
            }
            pre[i] = temp;
        }
        // 移到0level的后面节点主要进行判断
        temp = temp.forward[0];
        if (temp.key != key) {
            return null;
        } else {
            int value = temp.value;
            // 从下向上清理，如果当前level没有这个节点，停止清理
            for (int i = 0; i <= level; i ++) {
                if (pre[i].forward[i] != temp) {
                    break;
                }
                pre[i].forward[i] = temp.forward[i];
            }
            // 删除节点后，可能造成level下降，需要调整level
            while (level > 0 && header.forward[level] == NULL) {
                level --;
            }
            return value;
        }
    }

    private int getLevel() {
        return RANDOM.nextInt(MAX_LEVEL);
    }

    public static void main(String[] args) {

    }
}
