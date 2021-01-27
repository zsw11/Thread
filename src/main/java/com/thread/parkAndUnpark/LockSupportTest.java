package com.thread.parkAndUnpark;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author zsw
 * @date 2021/1/22 9:38
 * @description : Park & Unpark
 */
@Slf4j
public class LockSupportTest {

    public static void threadTest() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("Thread start....");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("start park.....");
                LockSupport.park();   //  暂停当前的线程
                log.info("end unPark......");
            }
        },"t1");
        thread.start();
//        Thread.sleep(3000);
        log.info("un park........");
//        LockSupport.unpark(thread);

    }
    public static void main(String[] args) throws InterruptedException {
        threadTest();

    }
}
