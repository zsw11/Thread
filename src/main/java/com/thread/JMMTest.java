package com.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zsw
 * @date 2021/1/22 17:20
 * @description : JMM多线程内存模型
 * 保证变量在线程间可见，对volatile变量所有的写操作都能立即反应到其他线程中，换句话说，volatile变量在各个线程中是一致的（得益于java内存模型—"先行发生原则"）；
 */
@Slf4j
public class JMMTest {
    private static  boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("Thread start");
                while (flag) {
//                    log.info("true");
                }
                log.info("thread end");
            }
        }, "t1");
        thread1.start();
        Thread.sleep(100); // 主线程睡眠

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("Thread2 start");
                flag = false;
            }
        }, "t2");
        thread2.start();
    }
}
