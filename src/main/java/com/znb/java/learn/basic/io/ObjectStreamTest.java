package com.znb.java.learn.basic.io;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-08 上午10:54
 */
public class ObjectStreamTest {
    public void outTest() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(""));
            out.writeObject(new Object());
            out.defaultWriteObject();

            // 写数据
            ObjectOutputStream.PutField putField = out.putFields();
            putField.put("key", "value");
            out.writeFields();

            // 写数据
            out.writeObject(new Object());
        } catch (Exception e) {

        }
    }
}
