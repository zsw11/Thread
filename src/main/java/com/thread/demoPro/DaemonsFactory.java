package com.thread.demoPro;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zsw
 * @date 2021/1/26 16:53
 * @description :
 */
@Slf4j
public class DaemonsFactory implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(100); //100ms
                log.info("当前的线程：{}",Thread.currentThread());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool(new DaemonsThreadFactory()); // 工厂,设置后台线程
        for (int i = 0; i < 10; i++) {
            executorService.execute(new DaemonsFactory());
        }
        log.info("All Deamons End");
        TimeUnit.MILLISECONDS.sleep(300);
    }
}
