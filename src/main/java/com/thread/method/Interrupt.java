package com.thread.method;

import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

/**
 * @author zsw
 * @date 2021/1/19 15:49
 * @description :
 */
@Slf4j
public class Interrupt {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.debug("线程Thread1开始：");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 100; i++) {
                    System.out.println("i="+(i+1));
                }

            }
        });
        thread1.start();
        thread1.interrupt();
        log.info(String.valueOf(thread1.isInterrupted()));

    }
}
