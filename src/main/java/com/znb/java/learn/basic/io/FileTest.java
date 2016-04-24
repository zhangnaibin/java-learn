package com.znb.java.learn.basic.io;

import java.io.File;

/**
 * 通过File可以做到
 *  检测文件是否存在
    读取文件长度
    重命名或移动文件
    删除文件
    检测某个路径是文件还是目录
    读取目录中的文件列表
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 上午11:49
 */
public class FileTest {
    public void function() {
        File file = new File("");
        file.exists();
        file.length();
        file.renameTo(new File(""));
        file.delete();
        file.isDirectory();

        // 读取目录中的文件列表
        String[] fileNames = file.list();
        File[] files = file.listFiles();
    }
}
