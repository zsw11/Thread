package com.thread.fangzhipengdemo;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zsw
 * @date 2021/1/26 10:42
 * @description : 在Java中可以通过锁和循环CAS的方式来实现原子操作
 * 基于CAS线程安全的计数器
 * 方法safeCount和一个非线程安全的计数器count
 */
@Slf4j
public class AtomicIntegerTest {
    private static AtomicInteger atomicInteger= new AtomicInteger(0);
    private static int i = 0;

    public static void main(String[] args) {
       final Counter counter = new Counter();
        ArrayList<Thread> threads = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        counter.Counter();
                        counter.safeCounter();
                    }
                }
            });
            threads.add(thread);
        }
        for (Thread t : threads) {
            t.start();
        }
        // 等待所有的线程执行完
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("AtomicInteger计算结果:{}",String.valueOf(AtomicIntegerTest.atomicInteger));
        log.info("++i,计算结果：{}",String.valueOf(AtomicIntegerTest.i));
        log.info("花费时间：{}ms",System.currentTimeMillis()-start);

    }

    /**
     * 使用cas实现线程安全计数器
     */
    static class Counter{
        private void safeCounter(){
            for(;;){  // 死循环
                int a = atomicInteger.get();
                boolean b = atomicInteger.compareAndSet(a, ++a); // 如果续期值i等于本来的值i，则更新值为++i.(+1)
                if (b) {
                    break;  // 死循环退出
                }
            }
        }

        private void Counter(){
            i++;
        }
    }
}
