package com.thread.demoPro;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zsw
 * @date 2021/1/27 11:30
 * @description :  优化：1，变量i需要加volatile，保证变量的可见性。2，getValue需要加synchronized,尽管return i; 是原子的，但是缺少同步使其数值可能处于不稳定的中间状态读被取。
 */
@Slf4j
public class AtomicTest implements Runnable {
//    private int i =0;
    private volatile int i =0;
    public  synchronized int getValue(){
        return i;
    }
//    public  int getValue(){
//        log.info(String.valueOf(i));
//        return i;
//    }
    private synchronized void evenIncrement(){
        i++;
        i++;
    }

    @Override
    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicTest atomicTest = new AtomicTest();
        executorService.execute(atomicTest);
        while (true) {
            int value = atomicTest.getValue();
            if (value %2 != 0) {
                log.info(String.valueOf(value));
                System.exit(0);
            }
        }

    }
}
