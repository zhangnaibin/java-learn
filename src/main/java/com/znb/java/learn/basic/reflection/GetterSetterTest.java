package com.znb.java.learn.basic.reflection;

import java.lang.reflect.Method;

/**
 * 获取setter和getter方法
 *
 * 遍历方法名，根据规则寻找方法
 * 获取指定类的getters和setters，你不能直接寻找getters和setters，
 * 你需要检查一个类所有的方法来判断哪个方法是getters和setters
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 下午2:05
 */
public class GetterSetterTest {

    // Setter方法的名字以set开头，有一个方法参数
    public static boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) {
            return false;
        }
        if (method.getParameterTypes().length != 1) {
            return false;
        }
        return true;
    }

    // Getter方法的名字以get开头，没有方法参数，返回一个值
    public static boolean isGetter(Method method) {
        if (!method.getName().startsWith("get")) {
            return false;
        }
        if (method.getParameterTypes().length != 0) {
            return false;
        }
        if (void.class.equals(method.getReturnType())) {
            return false;
        }
        return true;
    }

    public static void printGetterAndSetter(Class clazz) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (isGetter(method)) {
                System.out.println("GET " + method);
            }
            if (isSetter(method)) {
                System.out.println("SET " + method);
            }
        }
    }

    public static void main(String[] args) {
        printGetterAndSetter(test.class);
    }

    public static class test {
        int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
