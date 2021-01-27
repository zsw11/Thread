package com.thread.demoPro;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zsw
 * @date 2021/1/26 15:52
 * @description :  编程思想练习
 */
@Slf4j
public class RunnableTest implements Runnable {

    public RunnableTest() {
        log.info("构造器");
    }

    @Override
    public void run() {
      log.info("run");
//        Thread.yield();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Thread(new RunnableTest()));
        }
        executorService.shutdown();// 整个线程是任务运行完，就退出，防止新任务被提交到executorService
//        executorService.submit(new RunnableTest()); //RejectedExecutionException

    }
}
