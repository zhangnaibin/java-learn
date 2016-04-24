package com.znb.java.learn.basic.reflection;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 注解使用测试
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 下午2:41
 */
public class AnnotationTest {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface ClassAnnotation {
        public String name();
        public String value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface MethodAnnotation {
        public String name();
        public String value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface ParameterAnnotation {
        public String name();
        public String value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface FieldAnnotation {
        public String name();
        public String value();
    }

    @ClassAnnotation(name="class name", value = "class value")
    public static class TheClass {
        @MethodAnnotation(name = "method name", value = "method value")
        public void doSomeString(@ParameterAnnotation(name = "parameter name", value = " parameter value")String name) {

        }

        public void doSomeString1(String name) {

        }

        @FieldAnnotation(name = "filed name", value = "field value")
        public String filed = "";

    }

    public static void main(String[] args) throws Exception{
        // 获取类注解
        Class clazz = TheClass.class;
        Annotation[] classAnnotations = clazz.getAnnotations();
        for (Annotation annotation : classAnnotations) {
            if (annotation instanceof ClassAnnotation) {
                ClassAnnotation myAnnotation = (ClassAnnotation) annotation;
                System.out.println(myAnnotation.name());
                System.out.println(myAnnotation.value());
            }
        }

        Annotation classAnnotation = clazz.getAnnotation(ClassAnnotation.class);
        if (classAnnotation instanceof ClassAnnotation) {
            ClassAnnotation myAnnotation = (ClassAnnotation) classAnnotation;
            System.out.println(myAnnotation.name());
            System.out.println(myAnnotation.value());
        }
        System.out.println("---------------");

        // 方法注解
        Method method = clazz.getDeclaredMethod("doSomeString", String.class);
        Annotation[] methodAnnotations = method.getDeclaredAnnotations();
        for (Annotation annotation : methodAnnotations) {
            if (annotation instanceof MethodAnnotation) {
                MethodAnnotation myAnnotation = (MethodAnnotation) annotation;
                System.out.println(myAnnotation.name());
                System.out.println(myAnnotation.value());
            }
        }

        Annotation methodAnnotation = method.getAnnotation(MethodAnnotation.class);
        if (methodAnnotation instanceof MethodAnnotation) {
            MethodAnnotation myAnnotation = (MethodAnnotation) methodAnnotation;
            System.out.println(myAnnotation.name());
            System.out.println(myAnnotation.value());
        }
        System.out.println("--------");

        // 参数注解,通过methd对象访问参数注解
        // 参数是一个数组，获取的注解是一个数组，所以为二维数组
        Annotation[][] paramterAnnotations = method.getParameterAnnotations();
        Class[] parameterTypes = method.getParameterTypes();
        int i = 0;
        for (Annotation[] annotations : paramterAnnotations) {
            Class parameterType = parameterTypes[i++];
            for (Annotation annotation : annotations) {
                if (annotation instanceof ParameterAnnotation) {
                    ParameterAnnotation parameterAnnotation = (ParameterAnnotation) annotation;
                    System.out.println("param " + parameterType.getName());
                    System.out.println(parameterAnnotation.name());
                    System.out.println(parameterAnnotation.value());
                }
            }
        }
        System.out.println("----------");

        // 变量注解
        Field field = clazz.getField("filed");
        Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
        for (Annotation annotation : fieldAnnotations) {
            if (annotation instanceof FieldAnnotation) {
                FieldAnnotation parameterAnnotation = (FieldAnnotation) annotation;
                System.out.println(parameterAnnotation.name());
                System.out.println(parameterAnnotation.value());
            }
        }

        Annotation fieldAnnotation = field.getAnnotation(FieldAnnotation.class);
        if (fieldAnnotation instanceof FieldAnnotation) {
            FieldAnnotation parameterAnnotation = (FieldAnnotation) fieldAnnotation;
            System.out.println(parameterAnnotation.name());
            System.out.println(parameterAnnotation.value());
        }
    }
}
