package com.znb.java.learn.basic.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * reader可以和inputStream整合
 * io的顺序是
 * fileName -> File -> Strean -> Reader/Write 可以进行转换
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 上午11:39
 */
public class ReaderWriterTest {


    public void test() throws IOException{
        Reader reader = new InputStreamReader(new FileInputStream(""));
    }

    // 将Reader包装到BufferedReader实现缓冲
    public void test2() throws IOException {
        Reader reader = new BufferedReader(new FileReader(""));
    }
}
