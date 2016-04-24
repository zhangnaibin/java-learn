package com.znb.java.learn.basic.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 泛型
 * 可以获取泛型的类型,通过getGeneric* 方法获取到泛型的信息
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 下午3:12
 */
public class TemplateTest {
    public class Test {
        public List<String> stringList;
        public List<String> getStringList() {
            return stringList;
        }

        public void setStringList(List<String> stringList) {
            this.stringList = stringList;
        }
    }

    public static void main(String[] args) throws Exception{
        // 泛型方法返回类型
        Method method = Test.class.getMethod("getStringList", null);

        // 这个获取参数方法，如果是泛型，会返回具体的类型
        Type returnType = method.getGenericReturnType();

        if (returnType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) returnType;
            Type[] typeArguments = type.getActualTypeArguments();
            for (Type typeArgument : typeArguments) {
                Class typeArgClass = (Class)typeArgument;
                System.out.println(typeArgClass);
            }
        }
        System.out.println("---------- ");

        // 泛型方法参数类型
        method = Test.class.getMethod("setStringList", List.class);
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        for(Type genericParameterType : genericParameterTypes){
            if(genericParameterType instanceof ParameterizedType){
                ParameterizedType aType = (ParameterizedType) genericParameterType;
                Type[] parameterArgTypes = aType.getActualTypeArguments();
                for(Type parameterArgType : parameterArgTypes){
                    Class parameterArgClass = (Class) parameterArgType;
                    System.out.println(parameterArgClass);
                }
            }
        }
        System.out.println("----------");

        // 泛型变量
        Field field = Test.class.getField("stringList");
        Type genericFieldType = field.getGenericType();
        if(genericFieldType instanceof ParameterizedType){
            ParameterizedType aType = (ParameterizedType) genericFieldType;
            Type[] fieldArgTypes = aType.getActualTypeArguments();
            for(Type fieldArgType : fieldArgTypes){
                Class fieldArgClass = (Class) fieldArgType;
                System.out.println(fieldArgClass);
            }
        }

    }
}
