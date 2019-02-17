package com.cn.lg.springthreadpool.a.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Auther: 刘钢
 * @Date: 2019/2/16 23:23
 * @Description: 单线程的使用
 */
public class SingleThread {

    public static void main(String[] args) throws Exception{
        threadStart();
    }


    /**
     * 新建线程
     * 1.继承Thread
     * 2.实现Runnable接口
     * 3.实现Callable接口通过FutureTask包装器来创建Thread线程
     */
    private static void threadStart() throws InterruptedException {
        // 1.继承Thread
        threadExtend threadExtend = new threadExtend();
        threadExtend.start();

        Thread.sleep(500L);
        System.out.println("------------");

        // 2.实现Runnable接口
        new Thread(() -> System.out.println(Thread.currentThread().getName() + "线程启动： 实现Runnalbe接口")).start();

        Thread.sleep(500L);
        System.out.println("------------");

        // 3.实现Callable接口通过FutureTask包装器来创建Thread线程
        Callable<Integer> oneCallable = new SomeCallable<>(10);
        //由Callable<Integer>创建一个FutureTask<Integer>对象：
        FutureTask<Integer> oneTask = new FutureTask<>(oneCallable);
        // FutureTask<Integer>是一个包装器，它通过接受Callable<Integer>来创建，它同时实现了Future和Runnable接口。
        // 由FutureTask<Integer>创建一个Thread对象：
        Thread oneThread = new Thread(oneTask);
        oneThread.start();

        Thread.sleep(500L);

        // 或者：
        int result = 10;
        new Thread(new FutureTask<>(()->{
            System.out.println(Thread.currentThread().getName() + "线程启动： 实现Callable接口 Lambda表达式");
            return result;
        })).start();

    }

}

class threadExtend extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程启动： 继承Thread类");
    }

}

class SomeCallable<Integer> implements Callable<Integer> {
    private Integer result;

    // 通过构造函数将参数注入
    SomeCallable(Integer result) {
        this.result = result;
    }

    /**
     * 实现call方法
     */
    @Override
    public Integer call() {
        System.out.println(Thread.currentThread().getName() + "线程启动： 实现Callable接口");
        return result;
    }

}