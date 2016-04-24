package com.znb.java.learn.basic.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 流可以组合在一起使用
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 上午11:36
 */
public class StreamTest {

    // 将流整合起来以便实现更高级的输入和输出操作
    public void combineStream() throws IOException{
        InputStream input = new BufferedInputStream(new FileInputStream(new File("")));
    }
}
