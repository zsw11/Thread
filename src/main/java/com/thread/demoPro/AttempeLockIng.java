package com.thread.demoPro;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zsw
 * @date 2021/1/27 10:23
 * @description : 尝试获取锁一段时间，然后放弃它
 */
@Slf4j
public class AttempeLockIng{
    private static Lock lock = new ReentrantLock();
    public static void untimed(){
        boolean captured = lock.tryLock();
        try{
            log.info("tryLock:{}",captured);
        }finally {
            if (captured) {
                lock.unlock();
            }
        }
    }

    public  static void timed(){
        boolean captured = false;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);// 尝试获取锁  2S
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            log.info("tyrLock:{},   2 TimeUnitSeconds",captured);
        }finally {
            if (captured) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AttempeLockIng.untimed();
        AttempeLockIng.timed();
        Thread acquired = new Thread(new Runnable() {
            @Override
            public void run() {
                AttempeLockIng.lock.lock();
                log.info("acquired -------");
            }
        });
        acquired.setDaemon(true);
        acquired.start();
        acquired.join();
        Thread.yield();
        AttempeLockIng.untimed();
        AttempeLockIng.timed(); //尝试获取锁2S,但是没有获取到 ，false
    }

}
