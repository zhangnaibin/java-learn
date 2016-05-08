package com.znb.java.learn.basic;

/**
 * 线程通信
 * see http://ifeve.com/thread-signaling/
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-05 下午8:34
 */
public class ThreadSignalTest {

    // 通过共享对象通信，必须持有同一个对象
    public static class MySignal {
        protected boolean hasDataToProcess = false;
        public synchronized boolean hasDataToProcess() {
            return this.hasDataToProcess;
        }

        public synchronized void setHasDataToProcess(boolean hasData) {
            this.hasDataToProcess = hasData;
        }
    }

    // 通过Object的wait(),notify(),notifyAll()方法
    public static class MonitorObject {

    }

    public static class MyWaitNotify {
        MonitorObject myMonitorObject = new MonitorObject();

        public void doWait() {
            synchronized (myMonitorObject) {
                try {
                    // 调用wait()方法后就释放了对象锁，知道被唤醒时，退出notify的同步代码才能重新获取。
                    myMonitorObject.wait();
                } catch (InterruptedException e) {
                }
            }
        }

        public void doNotify() {
            synchronized (myMonitorObject) {
                // 能够获取wait()时释放的锁，必须退出同步代码才释放锁
                myMonitorObject.notify();
            }
        }
    }

    // 处理信号丢失。丢失就是在wait()前就已经调用notify()了，可能一直wait()。添加一个标志
    public static class MyWaitNotify2 {
        MonitorObject myMonitorObject = new MonitorObject();
        boolean wasSignaled = false;

        public void doWait() {
            synchronized (myMonitorObject) {
                if (!wasSignaled) {
                    try {
                        // 调用wait()方法后就释放了对象锁，知道被唤醒时，退出notify的同步代码才能重新获取。
                        myMonitorObject.wait();
                    } catch (InterruptedException e) {
                    }
                }
                wasSignaled = false;
            }
        }

        public void doNotify() {
            synchronized (myMonitorObject) {
                // 能够获取wait()时释放的锁，必须退出同步代码才释放锁
                wasSignaled = true;
                myMonitorObject.notify();
            }
        }
    }

    // 为了防止假唤醒，改为自旋方式
    public static class MyWaitNotify3 {
        MonitorObject myMonitorObject = new MonitorObject();
        boolean wasSignaled = false;

        public void doWait() {
            synchronized (myMonitorObject) {
                while (!wasSignaled) {
                    try {
                        // 调用wait()方法后就释放了对象锁，知道被唤醒时，退出notify的同步代码才能重新获取。
                        myMonitorObject.wait();
                        // 这样醒了之后会检查一下是不是真的应该被唤醒
                    } catch (InterruptedException e) {
                    }
                }
                wasSignaled = false;
            }
        }

        public void doNotify() {
            synchronized (myMonitorObject) {
                // 能够获取wait()时释放的锁，必须退出同步代码才释放锁
                wasSignaled = true;
                myMonitorObject.notify();
            }
        }
    }

    // 不要在字符串常量或全局对象中使用wait()，那样只有一个对象
}
