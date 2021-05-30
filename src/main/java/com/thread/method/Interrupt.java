package com.thread.method;

import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.util.concurrent.TimeUnit;

/**
 * @author zsw
 * @date 2021/1/19 15:49
 * @description :
 */
@Slf4j
public class Interrupt {
    public static void main(String[] args) {
//        interruptTest();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.debug("线程Thread1开始：");
                while (true) {
                    try {
                        Thread.sleep(3000000); // 打断睡眠，抛出异常
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                    for (int i = 0; i < 100; i++) {
                        System.out.println("i=" + (i + 1));
                    }
                }
            }
        });
        thread1.start();
        thread1.interrupt();
        log.info(String.valueOf(thread1.isInterrupted())); //中断标志为true
    }

    public class ThreadSafe extends Thread {
        public void run() {
            while (!isInterrupted()) { //非阻塞过程中通过判断中断标志来退出
                try {
                    Thread.sleep(5 * 1000);//阻塞过程捕获中断异常来退出
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;//捕获到异常之后，执行 break 跳出循环
                }
            }
        }
    }
}
