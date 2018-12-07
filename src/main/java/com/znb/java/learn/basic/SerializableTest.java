package com.znb.java.learn.basic;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

/**
 * 序列化测试
 * 序列化时不保存静态变量，它是属于类的
 * 同一个对象的重复序列化只增加引用，改变内容时反序列化后值不会改变
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-08 上午10:34
 */
public class SerializableTest {

    // 通过自己实现readObject和writeObject来实现序列化，可以处理特殊需求
    public static class EncTest implements Serializable{
        private static final long serialVersionUID = 1L;
        private String password = "pass";

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        // 这里返回的对象会替换掉反序列化时生成的对象，适用于单例的情况
        private Object readResolve() {
            return new Object();
        }

        private void writeObject(ObjectOutputStream out) {
            try {
                ObjectOutputStream.PutField putField = out.putFields();
                System.out.println("原密码:" + password);
                password = "encryption";
                putField.put("password", password);
                System.out.println("加密后密码:" + password);
                out.writeFields();

            } catch (Exception e) {

            }
        }

        private void readObject(ObjectInputStream in) {
            try {
                ObjectInputStream.GetField readFields = in.readFields();
                Object oject = readFields.get("password", "");
                System.out.println("要解密的字符串:" + oject.toString());
                password = "pass";
            } catch (Exception e) {

            }
        }

        public static void main(String[] args) {
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/Users/znb/temp/result.obj"));
                out.writeObject(new EncTest());
                out.close();

                ObjectInputStream in = new ObjectInputStream(new FileInputStream("/Users/znb/temp/result.obj"));
                EncTest enc = (EncTest)in.readObject();
                System.out.println("解密后字符串:" + enc.getPassword());
                in.close();
            } catch (Exception e) {

            }
        }
    }

    // Externalizable是Serilizable的子类，强制必须实现自己的序列化
    // 需要提供一个public的无参构造函数
    public static class EncTest2 implements Externalizable {

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {

        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

        }
    }


}
