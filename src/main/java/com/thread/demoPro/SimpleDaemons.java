package com.thread.demoPro;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author zsw
 * @date 2021/1/26 16:26
 * @description :
 */
@Slf4j
public class SimpleDaemons implements Runnable{
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
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new SimpleDaemons());
            thread.setName(String.valueOf(i));
            thread.setDaemon(true); // 设置为后台线程，会发现，这些线程在main线程之后运行完。main是后台线程。
            thread.start();
        }
        log.info("ALL Daemons started");
        TimeUnit.MILLISECONDS.sleep(175);
    }
}
