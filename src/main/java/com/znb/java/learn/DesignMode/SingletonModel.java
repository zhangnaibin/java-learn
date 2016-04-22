package com.znb.java.learn.DesignMode;

/**
 * 单例模式
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-22 上午10:14
 */
public class SingletonModel {
    // 常规写法,延迟初始化
    private static SingletonModel _instance;

    // 添加volatile,前面那种方法在new Object()时会发生操作重排序，造成获取的对象还没有初始化
    // 初始化对象过程：分配内存，初始化对象，设置对象指向初始化地址。后两部会因为编译优化乱序，所以需要添加voliatile阻止
//    private volatile static SingletonModel _instance;

    private SingletonModel () {
        System.out.println("construct init!");
    }

    // delay init
    public static SingletonModel getInstance() {
        if (null == _instance) {
            synchronized (_instance) {
                if (null == _instance) {
                    _instance = new SingletonModel();
                }
            }
        }
        return _instance;
    }

    // 延迟初始化。基于类的初始化方案。类初始化时，jvm会通过锁来来保证多个线程同步
    private static class InstanceHolder {
        public static SingletonModel instance = new SingletonModel();
    }

    public static SingletonModel getInstance2() {
        return InstanceHolder.instance;
    }

    public static void main(String[] args) {

    }

}
