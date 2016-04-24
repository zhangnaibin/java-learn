package com.znb.java.learn.basic.reflection;

import java.lang.reflect.Field;

/**
 * 只能获取public的变量
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 下午12:57
 */
public class FieldTest {
    public static class Test {
        private int id;
        protected Object obj;
        public String name;
    }

    public static void main(String[] args) {
        try {
            Class test = Test.class;
            Field[] fields = test.getFields();
            for (Field f : fields) {
                System.out.println(f.getName());
            }
            System.out.println("-------------");
            Field filed = test.getField("name");
            System.out.println(filed.getName());
            System.out.println("-------------");

            // 获取变量类型
            Object value = filed.getType();
            System.out.println(value.toString());

            // 获取或设置变量值

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
