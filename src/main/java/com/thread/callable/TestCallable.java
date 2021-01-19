package com.thread.callable;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.Future;

/**
 * @author zsw
 * @date 2021/1/19 9:57
 * @description :
 */
@Slf4j(topic = "TestCallable")
public class TestCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        /*  newCachedThreadPool() 参数说明
        * 1 corePoolSize	int	核心线程池大小
2	maximumPoolSize	int	最大线程池大小
3	keepAliveTime	long	线程最大空闲时间
4	unit	TimeUnit	时间单位
5	workQueue	BlockingQueue<Runnable>	线程等待队列
6	threadFactory	ThreadFactory	线程创建工厂
7	handler	RejectedExecutionHandler	拒绝策略
        * */
        Task task = new Task();
        Future<Integer> submit = executorService.submit(task);
        Thread.sleep(1000);
        if (submit.isDone()) {
            log.info("已完成");
        }
        Integer integer = submit.get();
        Integer integer1 = submit.get(3000, TimeUnit.SECONDS); //等待3S
        log.info(String.valueOf(integer1));


    }

    public static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            log.info("子线程计算中");
            int sum = 0;
            for (int i = 0; i <= 10; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
