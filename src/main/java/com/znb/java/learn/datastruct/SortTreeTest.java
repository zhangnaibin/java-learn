package com.znb.java.learn.datastruct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 排序树，实现快速求一个key的排名和获取topN
 *
 * @author zhangnaibin@xiaomi.com
 * @time 2016-06-01 上午11:37
 */
public class SortTreeTest {

    // data的属性必须是不变化的
    public static class Data implements Comparable{
        final int userId;
        int score = 0;
        int ranking = 0;
        long createTime = 0;

        public Data(int userId) {
            this.userId = userId;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Data) {
                return ((Data) o).userId == userId;
            }
            return false;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = result * 31 + userId;
            return result;
        }

        @Override
        public String toString() {
            return new StringBuilder().append("userId:").append(userId).toString();
        }

        @Override
        public int compareTo(Object o) {
            Data data = (Data)o;
            return (int)(this.createTime - data.createTime);
        }
    }

    public static class Node {
        int lowerKey = 0;
        int upperKey = 0;
        int number = 0;

        ArrayList<Data> dataList = new ArrayList<Data>();
        Node left = null;
        Node right = null;
        Node prev = null;
        Node next = null;
    }

    Node root = null;
    Node head = null;
    Node tail = null;

    public void setup(int lowerKey, int upperKey) {
        root = setupNode(lowerKey, upperKey);
    }

    public void insert(int score, Data data) {
        insertIntoNode(root, score, data);
    }

    public void remove(int score, Data data) {
        removeFromNode(root, score, data);
    }

    // data的变化会不会影响删除操作
    public void change(int oldKey, int newKey, Data oldData, Data data) {
        remove(oldKey, oldData);
        insert(newKey, data);
    }

    public int getRanking(int score, Data data) {
        return getRankOfNode(root, score, data) + 1;
    }

    private List<Data> getTopN(int n) {
        List<Data> dataList = new ArrayList<Data>(n + 1);
        int count = 0;
        Node cursor = tail;
        while (cursor != null) {
            for (Data data : cursor.dataList) {
                data.ranking = ++ count;
                data.score = cursor.lowerKey;
                dataList.add(data);
                if (count >= n) {
                    return dataList;
                }
            }
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
        node.number = 0;
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
    private void insertIntoNode(Node node, int score, Data data) {
        if (node == null) {
            return ;
        }

        if (!isInsideNode(node, score)) {
            return ;
        }

        node.number ++;
        if (isLeafNode(node)) {
            node.dataList.add(data);
            Collections.sort(node.dataList);
            return ;
        }
        final int middleKey = getMiddleKey(node.lowerKey, node.upperKey);
        if (score <= middleKey) {
            insertIntoNode(node.left, score, data);
        } else {
            insertIntoNode(node.right, score, data);
        }
    }

    private void removeFromNode(Node node, int score, Data data) {
        if (node == null) {
            return ;
        }

        if (!isInsideNode(node, score)) {
            return ;
        }
        node.number --;
        if (isLeafNode(node)) {
            node.dataList.remove(data);
            Collections.sort(node.dataList);
            return ;
        }
        final int middleKey = getMiddleKey(node.lowerKey, node.upperKey);
        if (score <= middleKey) {
            removeFromNode(node.left, score, data);
        } else {
            removeFromNode(node.right, score, data);
        }
    }

    private int getRankOfNode(Node node, int score, Data data) {
        int ranking = 0;
        if (node == null) {
            return ranking;
        }

        if (score < node.lowerKey) {
            ranking += node.number;
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
            ranking += node.right != null ? node.right.number : 0;
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
}
