package com.znb.java.learn.datastruct;

import java.util.ArrayList;
import java.util.List;

/**
 * B+树
 * @author zhangnaibin@xiaomi.com
 * @time 2016-06-02 上午10:04
 */
public class BPlustTreeTest {
    // 节点父类
    class INode {
        public boolean isLeaf = false;
        public INode parent = null;
        public List<Float> keys = new ArrayList<Float>();
        public List<INode> childNodes = new ArrayList<INode>();
    }

    // 普通子节点
    class TreeNode extends INode {

    }

    // 叶子节点
    class TreeLeaf extends INode {
        public List<Object> values = new ArrayList<Object>();
        public TreeLeaf rightBrother = null;
    }
}
