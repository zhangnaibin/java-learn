package com.znb.java.learn.concurrent;

import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 多线程
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-24 上午10:09
 */
public class ExecutorTest {

    // 关系说明
    public static void executorTest1() {
        // see http://www.cnblogs.com/skywang12345/p/3509903.html

        // 接口，只有一个execute函数
        Executor executor;
        // 接口，继承Executor接口，丰富了各个功能
        ExecutorService executorService;
        // 抽象类，实现了ExecutorService的接口
        AbstractExecutorService abstractExecutorService;
        // 线程池，继承AbstractExecutorService
        ThreadPoolExecutor threadPoolExecutor;
        // 接口，继承ExecutorService，提供延时和周期执行功能
        ScheduledExecutorService scheduledExecutorService;
        // 继承ThreadPoolExecutor，实现ScheduledExecutorService接口。具有延迟和周期执行的线程池
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

        // 静态工程类，返回线程的对象
        Executors.newCachedThreadPool();
    }

    // ThreadPoolExecutor结构
    public static void ThreadPoolExecutorTest() {
        // see http://www.cnblogs.com/skywang12345/p/3509941.html

    }

    // 线程池状态
    public static void ThreadPoolStatus() {
        // see http://www.cnblogs.com/skywang12345/p/3509960.html
        // 由一个原子int记录，高三位记录状态，低29位记录线程数量
    }

    // 线程拒绝策略
    public static void rejectPolicy() {
        // see http://www.cnblogs.com/skywang12345/p/3512947.html
        // AbortPolicy         -- 当任务添加到线程池中被拒绝时，它将抛出 RejectedExecutionException 异常。
        // CallerRunsPolicy    -- 当任务添加到线程池中被拒绝时，会在线程池当前正在运行的Thread线程池中处理被拒绝的任务。--由正在执行的线程执行这个任务，就是插队
        // DiscardOldestPolicy -- 当任务添加到线程池中被拒绝时，线程池会放弃等待队列中最旧的未处理任务，然后将被拒绝的任务添加到等待队列中。
        // DiscardPolicy       -- 当任务添加到线程池中被拒绝时，线程池将丢弃被拒绝的任务。
    }
}
