package com.thread.JUC;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 可以维护当前访问自身的线程个数，并提供了同步机制
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final Semaphore sp = new Semaphore(3);//创建Semaphore信号量，初始化许可大小为3
        for (int i = 0; i < 10; i++) {

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        sp.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "进入，当前已有" + (3 - sp.availablePermits()) + "个并发");
                 try {
                  Thread.sleep(1000);
                 } catch (InterruptedException e) {
                  e.printStackTrace();
                 }
                 System.out.println("线程" + Thread.currentThread().getName() + "即将离开");
                 sp.release();//释放许可，许可数加1
                 System.out.println("线程" + Thread.currentThread().getName() +
                         "已离开，当前已有" + (3-sp.availablePermits()) + "个并发");

                }
            };
         service.execute(runnable);
        }
    }
}
