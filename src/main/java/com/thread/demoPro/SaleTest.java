package com.thread.demoPro;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zsw
 * @date 2021/1/22 15:29
 * @description :  售票
 */
@Slf4j
public class SaleTest {
    public static void main(String[] args) throws InterruptedException {
        SaleNum saleNum = new SaleNum();
        saleNum.setCount(200);
        List<Integer> integers = new ArrayList<>();
        /**
         * 使用闭锁(CountDownLatch)来实现，CountDownLatch是一种灵活的闭锁实现，它可以使一个或多个线程等待一组事件发生。闭锁状态包括一个计数器，该计数器被初始化为一个正数，表示需要等待的事件数量。countDown方法递减计数器，表示有一个事件已经发生了
         * ，而await方法等待计数器达到零，即表示需要等待的事情都已经发生。可以使用闭锁来这样设计程序达到目的：
         */
        final CountDownLatch endGate = new CountDownLatch(10);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    int count = (new Random().nextInt(5) + 1);
                    integers.add(count);
                    saleNum.sale(count); // 随机出售0~5张票
                    log.info("{}出售了{}张票",Thread.currentThread().getName(),count);
                    endGate.countDown();
                }
            });
        }
        endGate.await();  // 线程都执行完了
        executorService.shutdown();
        log.info("剩余{}张---3",saleNum.getCount());
//        Thread.sleep(1000); // 让线程池的线程运行完
//        log.info("剩余{}张---1",saleNum.getCount());
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        log.info("剩余{}张,出售了{}张---2",200-sum,sum);
//        log.info(String.valueOf(executorService.isShutdown()));
    }
}
