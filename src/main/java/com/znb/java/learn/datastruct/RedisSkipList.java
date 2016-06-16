package com.znb.java.learn.datastruct;

import java.util.Random;

/**
 * redis 跳表实现
 * @author zhangnaibin@xiaomi.com
 * @time 2016-06-12 下午4:53
 */
public class RedisSkipList {

    // 注意: a.equals(b) 不意味着 a.compare(b) == 0, 通过userId判断两者相等，通过其他信息进行两者的比较
    class RankExtra implements Comparable{
        private long userId;
        private long createTime;
        private long timeCost;

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public long getTimeCost() {
            return timeCost;
        }

        public void setTimeCost(long timeCost) {
            this.timeCost = timeCost;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        @Override
        public String toString() {
            return "userId:" + userId + "\tcreateTime:" + createTime + "\ttimeCost:" + timeCost;
        }

        // 只要userId相等，两个额外信息就是相等的
        @Override
        public boolean equals(Object o) {
            if (o instanceof RankExtra) {
                return ((RankExtra)o).userId == this.userId;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            int result = 17;
            int c = (int)(userId ^ (userId >>> 32));
            return result * 31 + c;
        }

        // TODO 比较的信息
        @Override
        public int compareTo(Object o) {
            RankExtra extra = (RankExtra)o;
            return (int)(timeCost - extra.timeCost);
        }
    }

    // 后退指针数组
    class Level {
        SkipListNode forward;
        int span;
    }

    // 跳表存储的节点
    class SkipListNode {
        RankExtra extra;
        int score;
        SkipListNode backward;
        Level[] level;
        public SkipListNode(int level, int score, RankExtra extra) {
            this.score = score;
            this.extra = extra;
        }
    }

    private final int MAX_SIZE;
    SkipListNode header, tail;
    int length;
    int level;
    private static final Random RANDOM = new Random();

    public RedisSkipList(int level) {
        MAX_SIZE = level;
        length = 0;
        this.level = 1;
        header = new SkipListNode(MAX_SIZE, 0, null);
        for (int i = 0; i < MAX_SIZE; i ++) {
            header.level[i].forward = null;
            header.level[i].span = 0;
        }
        header.backward = null;
        tail = null;
    }

    // 插入节点
    public SkipListNode insert(int score, RankExtra extra) {
        if (extra == null || extra.getUserId() == 0) {
            return null;
        }

        SkipListNode[] update = new SkipListNode[MAX_SIZE];
        SkipListNode temp = header;
        int[] rank = new int[MAX_SIZE];
        int level;
        for (int i = this.level - 1; i >= 0; i--) {
            rank[i] = i == this.level - 1 ? 0 : rank[i + 1];
            while (temp.level[i].forward != null &&
                    (temp.level[i].forward.score < score ||
                            (temp.level[i].forward.score == score && temp.level[i].forward.extra.compareTo(extra) < 0))) {
                rank[i] += temp.level[i].span;
                temp = temp.level[i].forward;
            }
            update[i] = temp;
        }
        level = new Random().nextInt(MAX_SIZE);
        if (level > this.level) {
            for (int i = this.level; i < level; i ++) {
                rank[i] = 0;
                update[i] = header;
                update[i].level[i].span = length;
            }
            this.level = level;
        }

        temp = new SkipListNode(level, score, extra);
        for (int i = 0; i < level; i ++) {
            temp.level[i].forward = update[i].level[i].forward;
            update[i].level[i].forward = temp;

            temp.level[i].span = update[i].level[i].span - (rank[0] - rank[i]);
            update[i].level[i].span = (rank[0] - rank[i]) + 1;
        }

        for (int i = level; i < this.level; i ++) {
            update[i].level[i].span ++;
        }

        temp.backward = (update[0] == header) ? null : update[0];
        if (temp.level[0].forward != null) {
            temp.level[0].forward.backward = temp;
        } else {
            tail = temp;
        }
        length ++;

        return temp;
    }

    // 获取排名，从小的开始。
    public long getRank(int score, RankExtra extra) {
        SkipListNode temp = header;
        long rank = 0;
        for (int i = this.level - 1; i >= 0; i --) {
            while (temp.level[i].forward != null &&
                    (temp.level[i].forward.score < score ||
                            (temp.level[i].forward.score == score && temp.level[i].forward.extra.compareTo(extra) <= 0))) { // 这里可以使用==0，因为这里不需要保存它的前一个节点
                rank += temp.level[i].span;
                temp =  temp.level[i].forward;
            }
        }
        if (temp.score == score && temp.extra != null && temp.extra.compareTo(extra) == 0) {
            return rank;
        }
        return 0;
    }

    // 删除指定score和extra的节点
    public SkipListNode delete(int score, RankExtra extra) {
        SkipListNode[] update = new SkipListNode[MAX_SIZE];
        SkipListNode temp = header;

        for (int i = level - 1; i >= 0; i --) {
            while (temp.level[i].forward != null &&
                    (temp.level[i].forward.score < score ||
                    temp.level[i].forward.score == score && temp.level[i].forward.extra.compareTo(extra) < 0)) {
                temp = temp.level[i].forward;
            }
            update[i] = temp;
        }
        temp = temp.level[0].forward;
        if (temp != null && temp.score == score && temp.extra.compareTo(extra) == 0) {
            return temp;
        } else {
            return null;
        }
    }

    // 将节点从list删除，调整level和length。
    private void delteNode(SkipListNode node, SkipListNode[] update) {
        for (int i = 0; i < level; i ++) {
            if (update[i].level[i].forward == node) {
                update[i].level[i].span += node.level[i].span - 1;
                update[i].level[i].forward = node.level[i].forward;
            } else {
                update[i].level[i].span --;
            }
        }

        if (node.level[0].forward != null) {
            node.level[0].forward.backward = node.backward;
        } else {
            tail = node.backward;
        }

        while (level > 1 && header.level[level - 1].forward == null) {
            level --;
        }
        length --;
    }
}
