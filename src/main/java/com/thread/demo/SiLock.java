package com.thread.demo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zsw
 * @date 2021/1/22 10:03
 * @description : 死锁
 */
@Slf4j
public class SiLock {
    static boolean flg;
    static void lockTest(){
        Object obj1 = new Object();
        Object obj2 = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj1) {
                    SiLock.flg = true;
                    log.info("t1,obj1");
                    synchronized (obj2) {
                        SiLock.flg = false;
                        log.info("t1,obj2");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj2) {
                    SiLock.flg = false;
                    log.info("t2,obj2");
                    synchronized (obj1) {
                        SiLock.flg = true;
                        log.info("t2,obj1");
                    }
                }

            }
        }).start();
    }

    public static void main(String[] args) {
        lockTest();
    }
}
