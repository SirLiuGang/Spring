package com.cn.lg.springthreadpool.b.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 刘钢
 * @Date: 2019/2/17 15:21
 * @Description: 线程池的几种实现方式
 */
public class ThreadPool {

    public static void main(String[] args) {
        threadPool();
    }

    /**
     * java通过Executors提供了四个静态方法创建线程池，分别是：
     *  1.newCachedThreadPool：创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     *  2.newFixedThreadPool ：创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     *  3.newScheduledThreadPool： 创建一个定长线程池，支持定时及周期性任务执行。
     *  4.newSingleThreadExecutor： 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     */
    private static void threadPool() {
//        cacheThreadPool();
        // 项目中常用的是newFixedThreadPool，可以自定义线程池大小，后期可通过CompletionService来进行管理线程的执行情况
//        fixedThreadPool();
//        scheduledThreadPool();
//        singleThreadExecutor();
    }

    private static void shutdownThreadPool() {
//        shutdown:
//        1. 调用之后不允许继续往线程池内继续添加线程;
//        2. 线程池的状态变为SHUTDOWN状态;
//        3. 所有在调用shutdown()方法之前提交到ExecutorSrvice的任务都会执行;
//        4. 一旦所有线程结束执行当前任务，ExecutorService才会真正关闭。

//        shutdownNow():
//        1. 该方法返回尚未执行的 task 的 List;
//        2. 线程池的状态变为STOP状态;
//        3. 阻止所有正在等待启动的任务, 并且停止当前正在执行的任务;

//        简单点来说，就是:
//        shutdown()调用后，不可以再 submit 新的 task，已经 submit 的将继续执行
//        shutdownNow()调用后，试图停止当前正在执行的 task，并返回尚未执行的 task 的 list
    }

    /**
     * 1.newCachedThreadPool：创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     *      线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
     */
    private static void cacheThreadPool() {
        ExecutorService es = Executors.newCachedThreadPool();

        for (int i = 0; i < 20; i++) {
//            final int index = i;
//            es.execute(() -> LOG.info("cacheThreadPool：线程 = {}，index = {}", Thread.currentThread().getName(), index));
            es.execute(new ThreadRunnalbe("cacheThreadPool", i));
        }

        es.shutdown();

    }

    /**
     * 2.newFixedThreadPool ：创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     */
    private static void fixedThreadPool() {
        ExecutorService es = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 20; i++) {
            es.execute(new ThreadRunnalbe("fixedThreadPool", i));
        }

        es.shutdown();
    }

    /**
     * 3.newScheduledThreadPool： 创建一个定长线程池，支持定时及周期性任务执行。
     */
    private static void scheduledThreadPool() {
        // 定时任务线程池
        ScheduledExecutorService es = Executors.newScheduledThreadPool(2);

        for (int i = 0; i < 2; i++) {
            // 参数1：目标对象
            // 参数2：隔多长时间开始执行线程
            // 参数3：执行周期
            // 参数4：时间单位
            es.scheduleAtFixedRate(new ThreadRunnalbe("scheduledThreadPool", i), 5, 10, TimeUnit.SECONDS);
        }

//        es.shutdown();
    }

    /**
     * 4.newSingleThreadExecutor： 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     */
    private static void singleThreadExecutor() {
        ExecutorService es = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            es.execute(new ThreadRunnalbe("singleThreadExecutor", i));
        }

        es.shutdown();
    }
}

/**
 * 实现Runnable的方法
 */
class ThreadRunnalbe implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(ThreadRunnalbe.class);

    private String threadMethod;    // 线程池的实现
    private Integer index;          // 索引

    ThreadRunnalbe(String threadMethod, Integer index) {
        this.threadMethod = threadMethod;
        this.index = index;
    }

    @Override
    public void run() {
        LOG.info("{}：线程 = {}，index = {}", threadMethod, Thread.currentThread().getName(), index);
    }
}
