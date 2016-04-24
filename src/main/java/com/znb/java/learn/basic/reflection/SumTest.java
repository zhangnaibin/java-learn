package com.znb.java.learn.basic.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 反射主要方法使用说明
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 下午12:31
 */
public class SumTest {
    public static class test {
        private int i;
    }
    public static void main(String[] args) {
        // 获取class的对象
        Class test = test.class; // 编译期间知道类名
        try {
            test = Class.forName("com.znb.java.learn.basic.reflection.sumTest.test"); // 运行期间可以获取类名
        } catch (Exception e) {

        }

        // 获取类名
        String className = test.getName(); // 获取类的全限定名
        String simpleName = test.getSimpleName(); // 不包含包名
        System.out.println("full name " + className);
        System.out.println("simle name " + simpleName);

        // 修饰符,即public,private,static等等的关键字---------------没有测试通过
        int modifiers = test.getModifiers();
        System.out.println(modifiers);
        System.out.println(Modifier.isPrivate(modifiers));
//        Modifier.isAbstract(int modifiers);
//        Modifier.isFinal(int modifiers);
//        Modifier.isInterface(int modifiers);
//        Modifier.isNative(int modifiers);
//        Modifier.isPrivate(int modifiers);
//        Modifier.isProtected(int modifiers);
//        Modifier.isPublic(int modifiers);
//        Modifier.isStatic(int modifiers);
//        Modifier.isStrict(int modifiers);
//        Modifier.isSynchronized(int modifiers);
//        Modifier.isTransient(int modifiers);
//        Modifier.isVolatile(int modifiers);

        // 包信息
        Package packageInfo = test.getPackage();

        // 父类
        Class father = test.getSuperclass();

        // 实现的接口,仅仅返回当前类实现的接口
        Class[] interfaces = test.getInterfaces();

        // 构造器
        Constructor[] constructors = test.getConstructors();

        // 方法
        Method[] methods = test.getMethods();

        // 变量
        Field[] fileds = test.getFields();

        // 注解
        Annotation[] annotations = test.getAnnotations();

    }
}
