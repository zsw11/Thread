package com.thread.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zsw
 * @date 2021/1/21 10:23
 * @description : wait notify
 *
 * sleep 是 Thread 方法，而 wait 是 Object 的方法
 * 2) sleep 不需要强制和 synchronized 配合使用，但 wait 需要 和 synchronized 一起用
 * 3) sleep 在睡眠的同时，不会释放对象锁的，但 wait 在等待的时候会释放对象锁
 */
@Slf4j
public class Wait {
    final static Object obj = new Object();
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    log.info("线程{}开始执行", Thread.currentThread().getName());
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("业务{}",Thread.currentThread().getName());
                }
            }
        }, "t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    log.info("线程{}开始执行", Thread.currentThread().getName());
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("业务{}",Thread.currentThread().getName());
                }
            }
        }, "t2").start();
        Thread.sleep(1000);
        log.info("开始随机唤醒线程：");
        synchronized (obj){
            obj.notifyAll();
            //        obj.notify();
        }
    }
}
