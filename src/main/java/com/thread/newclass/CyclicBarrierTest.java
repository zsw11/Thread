package com.thread.newclass;

import lombok.SneakyThrows;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author zsw
 * @date 2021/1/27 15:25
 * @description : CyclicBarrier 可以还是多个线程挂起，到达某一个状态下执行。
 * 用来挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务；
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        final int n=4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(n, new Runnable() {
            @Override
            public void run() {
                System.out.println("我达到了状态");
            }
        });
        for (int i = 0; i < n; i++) {
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"线程开始");
//                    TimeUnit.SECONDS.sleep(3);
                    cyclicBarrier.await(); // 线程先挂起，到达了状态才运行。
                    System.out.println(Thread.currentThread().getName()+"线程结束");
                }
            }).start();
        }
    }
}
/**
 * Thread-1线程开始
 * Thread-2线程开始
 * Thread-0线程开始
 * Thread-3线程开始
 * 我达到了状态
 * Thread-3线程结束
 * Thread-1线程结束
 * Thread-2线程结束
 * Thread-0线程结束
 */
