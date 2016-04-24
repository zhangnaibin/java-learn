package com.znb.java.learn.basic.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-24 下午3:43
 */
public class ProxyTest {
    public static class MyHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }

    }

    public interface MyInterface {

    }
    public static void main(String[] args) {

        //在执行完这段代码之后，变量proxy包含一个MyInterface接口的的动态实现。
        // 所有对proxy的调用都被转向到实现了InvocationHandler接口的handler上。有关InvocationHandler的内容会在下一段介绍。
        InvocationHandler handler = new MyHandler();
        MyInterface proxy = (MyInterface) Proxy.newProxyInstance(MyInterface.class.getClassLoader(), new Class[]{MyInterface.class}, handler);
    }
}
