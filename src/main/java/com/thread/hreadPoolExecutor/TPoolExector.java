package com.thread.hreadPoolExecutor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zsw
 * @date 2021/1/20 16:23
 * @description : 线程池,
 */
@Slf4j
public class TPoolExector {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newCachedThreadPool(); // 创建可缓存的线程池，线程池固定大小为0，最大值为Integer.MAX_VALUE，60S没使用，就会销毁
//        ExecutorService executorService = Executors.newSingleThreadExecutor();// 创建固定大小为1，最大为1的线程池
//        ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();// 创建 1，max, 0秒
//        ExecutorService executorService = Executors.newFixedThreadPool(10);// 创建 10，10, 0秒
        ExecutorService executorService = Executors.newScheduledThreadPool(10);// 创建 10，max, 0秒

        for (int i = 0; i < 100; i++) {
            executorService.submit(new Thread(new Runnable() {
                @Override
                public void run() {
                    log.info(Thread.currentThread().getName()+"执行线程任务");
                }
            }));
        } // 创建不到100 个线程来执行100个任务

    }
}
