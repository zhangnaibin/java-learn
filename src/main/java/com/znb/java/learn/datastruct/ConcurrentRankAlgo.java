package com.znb.java.learn.datastruct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhangnaibin@xiaomi.com
 * @time 2016-06-01 下午8:49
 */
public class ConcurrentRankAlgo {

    class RankExtra implements Comparable {
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
                return ((RankExtra) o).userId == this.userId;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            int result = 17;
            int c = (int) (userId ^ (userId >>> 32));
            return result * 31 + c;
        }

        // TODO
        @Override
        public int compareTo(Object o) {
            RankExtra extra = (RankExtra) o;
            return -1;
        }
    }

    class UserRank {
        private int score;
        private int rank;
        private RankExtra extra;

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public RankExtra getExtra() {
            return extra;
        }

        public void setExtra(RankExtra extra) {
            this.extra = extra;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(score).append("\t").append(rank).append("\t").append(extra.toString());
            return sb.toString();
        }

    }

    class Node {
        int lowerKey = 0;
        int upperKey = 0;
        AtomicInteger number = new AtomicInteger(0);

        ArrayList<RankExtra> dataList = new ArrayList<RankExtra>();
        Node left = null;
        Node right = null;
        Node prev = null;
        Node next = null;
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    }

    Node root = null;
    Node head = null;
    Node tail = null;


    public ConcurrentRankAlgo(int lowerKey, int upperKey) {
        root = setupNode(lowerKey, upperKey);
    }

    public void insert(int score, RankExtra data) {
        insertIntoNode(root, score, data);
    }

    public void remove(int score, RankExtra data) {
        removeFromNode(root, score, data);
    }

    // data的变化会不会影响删除操作
    public void change(int oldScore, int newScore, RankExtra data) {
        remove(oldScore, data);
        insert(newScore, data);
    }

    public int getRanking(int score, RankExtra data) {
        return getRankOfNode(root, score, data) + 1;
    }

    public List<UserRank> getRankList(int start, int end) {
        return getTopList(start, end);
    }

    private List<UserRank> getTopN(int n) {
        List<UserRank> dataList = new ArrayList<UserRank>(n + 1);
        int count = 0;
        Node cursor = tail;
        while (cursor != null) {
            cursor.readWriteLock.readLock().lock();
            for (RankExtra data : cursor.dataList) {
                UserRank rank = new UserRank();
                rank.setRank(++ count);
                rank.setScore(cursor.lowerKey);
                rank.setExtra(data);
                dataList.add(rank);
                if (count >= n) {
                    cursor.readWriteLock.readLock().unlock();
                    return dataList;
                }
            }
            cursor.readWriteLock.readLock().unlock();
            cursor = cursor.prev;
        }
        return dataList;
    }

    private List<UserRank> getTopList(int begin, int end) {
        if (begin > end) {
            return new ArrayList<UserRank>();
        }

        List<UserRank> dataList = new ArrayList<UserRank>(end - begin + 1);
        int count = 0;
        Node cursor = tail;
        while (cursor != null) {
            cursor.readWriteLock.readLock().lock();
            if (count + cursor.number.get() < begin) {
                count += cursor.number.get();
            } else {
                for (RankExtra data : cursor.dataList) {
                    if (count >= begin) {
                        UserRank rank = new UserRank();
                        rank.setRank(++ count);
                        rank.setScore(cursor.lowerKey);
                        rank.setExtra(data);
                        dataList.add(rank);
                        if (count >= end) {
                            cursor.readWriteLock.readLock().unlock();
                            return dataList;
                        }
                    }
                }
            }
            cursor.readWriteLock.readLock().unlock();
            cursor = cursor.prev;
        }
        return dataList;
    }

    private Node setupNode(int lowerKey, int upperKey) {
        if (lowerKey > upperKey) {
            return null;
        }

        Node node = new Node();
        node.lowerKey = lowerKey;
        node.upperKey = upperKey;
        node.dataList.clear();

        if (isLeafNode(node)) {
            if (head == null) {
                head = node;
            }

            if (tail != null) {
                tail.next = node;
                node.prev = tail;
            }
            tail = node;
            return node;
        }
        if (upperKey > lowerKey) {
            final int middleKey = getMiddleKey(lowerKey, upperKey);
            node.left = setupNode(lowerKey, middleKey);
            node.right = setupNode(middleKey + 1, upperKey);
        }
        return node;
    }

    // 插入一个节点
    private void insertIntoNode(Node node, int score, RankExtra data) {
        if (node == null) {
            return;
        }

        if (!isInsideNode(node, score)) {
            return;
        }

        node.number.incrementAndGet();
        if (isLeafNode(node)) {
            node.readWriteLock.writeLock().lock();
            node.dataList.add(data);
            Collections.sort(node.dataList);
            node.readWriteLock.writeLock().unlock();
            return;
        }
        final int middleKey = getMiddleKey(node.lowerKey, node.upperKey);
        if (score <= middleKey) {
            insertIntoNode(node.left, score, data);
        } else {
            insertIntoNode(node.right, score, data);
        }
    }

    private void removeFromNode(Node node, int score, RankExtra data) {
        if (node == null) {
            return;
        }

        if (!isInsideNode(node, score)) {
            return;
        }
        node.number.decrementAndGet();
        if (isLeafNode(node)) {
            node.readWriteLock.writeLock().lock();
            node.dataList.remove(data);
            Collections.sort(node.dataList);
            node.readWriteLock.writeLock().unlock();
            return;
        }
        final int middleKey = getMiddleKey(node.lowerKey, node.upperKey);
        if (score <= middleKey) {
            removeFromNode(node.left, score, data);
        } else {
            removeFromNode(node.right, score, data);
        }
    }

    private int getRankOfNode(Node node, int score, RankExtra data) {
        int ranking = 0;
        if (node == null) {
            return ranking;
        }

        if (score < node.lowerKey) {
            ranking += node.number.get();
            return ranking;
        }

        if (score > node.upperKey) {
            ranking += 0;
            return ranking;
        }

        if (isLeafNode(node)) {
            ranking += Math.max(node.dataList.indexOf(data), 0);
            return ranking;
        }

        final int middleKey = getMiddleKey(node.lowerKey, node.upperKey);
        if (score <= middleKey) {
            ranking += node.right != null ? node.right.number.get() : 0;
            ranking += getRankOfNode(node.left, score, data);
        } else {
            ranking += getRankOfNode(node.right, score, data);
        }
        return ranking;
    }

    private boolean isLeafNode(Node node) {
        return node.lowerKey == node.upperKey;
    }

    private boolean isInsideNode(Node node, int score) {
        return score >= node.lowerKey && score <= node.upperKey;
    }

    private int getMiddleKey(int lowerKey, int upperKey) {
        final int middleKey = lowerKey + ((upperKey - lowerKey) >> 1);
        return middleKey;
    }

    // 前序遍历输出树，测试使用
    public void print(Node node) {

    }

    // 顺序输出所有叶子信息
    public void printLeaf() {
        Node temp = tail;
        while (temp != null) {
            System.out.println(temp.lowerKey + "\t" + temp.upperKey + "\t" + temp.number + "\t" + Arrays.toString(temp.dataList.toArray()));
            temp = temp.prev;
        }
    }

    public void printTopN(int n) {
        List<UserRank> rankList = getTopN(n);
    }

    public static void main(String[] args) {
    }
}
