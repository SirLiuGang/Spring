package com.cn.lg.springthreadpool.c.threadpoolmanage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Auther: 刘钢
 * @Date: 2019/2/18 23:26
 * @Description: 线程池使用中的管理
 */
public class ThreadPoolManage {

    private static final Logger LOG = LoggerFactory.getLogger(ThreadPoolManage.class);

    public static void main(String[] args) {
        // ExecutorService实际上是一个线程池的管理工具
        // 手动获取返回结果
//        futureManage();
        // 升级版：通过CompletionService管理
//        poolManage();
    }

    /**
     * 带返回结果的批量任务运行（循环Future中线程的状态）
     * 获取任务的执行的返回值
     */
    private static void futureManage() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Integer>> resultList = new ArrayList<>();

        // 创建10个任务并执行
        for (int i = 0; i < 10; i++) {
            // 使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
            Future<Integer> future = executorService.submit(new SomeCallable<>(i));
            // ↑ submit和execute方法的区别见：com.cn.lg.springthreadpool.b.threadpool.ThreadPool.shutdownThreadPool()方法
            // 将任务执行结果存储到List中
            resultList.add(future);
        }

        // 遍历任务的结果
        for (Future<Integer> fs : resultList) {
            try {
                LOG.info("执行结果：{}", fs.get());      // 打印各个线程（任务）执行的结果
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                // 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。如果已经关闭，则调用没有其他作用。
                executorService.shutdown();
            }
        }

    }

    /**
     * 带返回结果的批量任务运行（通过CompletionService管理）
     * CompletionService将Executor（线程池）和BlockingQueue（堵塞队列）结合在一起，同一时候使用Callable作为任务的基本单元，整个过程就是生产者不断把Callable任务放入堵塞队列，Executor作为消费者不断把任务取出来运行，并返回结果；
     *  优势：
     *      a、堵塞队列防止了内存中排队等待的任务过多，造成内存溢出（毕竟一般生产者速度比較快，比方爬虫准备好网址和规则，就去运行了，运行起来（消费者）还是比較慢的）
     *      b、CompletionService能够实现，哪个任务先运行完毕就返回，而不是按顺序返回，这样能够极大的提升效率；
     */
    private static void poolManage() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        CompletionService<Integer> ecs = new ExecutorCompletionService<>(executor);

        // 假设一共有20个任务，每个任务执行1s，使用线程池
        int taskNum = 20;
        for(int i = 0; i < taskNum; i++) {
            final int index = i + 1;
            // Callable的call方法能够返回运行的结果
            ecs.submit(() -> {
                LOG.info("第{}个任务：执行1s，当前线程：{}", index, Thread.currentThread().getName());
                Thread.sleep(2000L);
                return index;   // 因为在CompletionService定义了Integer的返回类型，所以这里需要进行返回
            });
        }

        List<Integer> resultList = new ArrayList<>();
        for(int i = 0; i < taskNum; i++) {
            try {
                Integer result = ecs.take().get();  // 使用阻塞的方式，打印各个线程（任务）执行的结果
                resultList.add(result);
            } catch (Exception e) {
                // 如果执行抛出异常，会在这里捕获
                e.printStackTrace();
            }
        }
        LOG.info("获取到的返回结果：{}", resultList.toString());

        // 当所有线程执行完毕后，关闭线程池
        executor.shutdown();
    }


}

/**
 * 实现Callable接口可以获取到返回值
 * @param <Integer>
 */
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
