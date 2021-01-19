package com.thread.sync;

import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

/**
 * @author zsw
 * @date 2021/1/19 16:37
 * @description : 共享变量问题
 *
 * 阻塞式的解决方案：synchronized，Lock
 * 非阻塞式的解决方案：原子变量
 */
@Slf4j
public class SyncTest {
    static int count = 1;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1022; i++) {
                    count ++;  // 不是原子操作
                }
            }
        },"t1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1022; i++) {
                    count --;
                }
            }
        },"t2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        log.info("count；{}",count);

    }
}
