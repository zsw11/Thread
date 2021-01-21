package com.thread.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zsw
 * @date 2021/1/21 10:51
 * @description :synchronized(锁的对象)可以保证方法或代码块在运行时，同一时刻只有一个线程可以进入到临界区（互斥性），同时它还保证了共享变量的内存可见性。
 *
 * Java中的每个对象都可以作为锁。
 *
 * 普通同步方法，锁是当前实例对象。
 * 静态同步方法，锁是当前类的class对象。
 * 同步代码块，锁是括号中的对象。
 */
@Slf4j
public class Synchronized {
    static boolean flg =true;
    static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new wait(), "wait").start();
        Thread.sleep(1000);
        new Thread(new notify(),"notify").start();
    }

    static class wait implements Runnable{
        @Override
        public void run() {
            synchronized (obj){
                while (flg){
                    log.info("1--线程开始：{}",Thread.currentThread().getName());
                    try {
                        obj.wait(); // 释放obj对象锁，把wait加入等待队列
                        log.info("1--释放obj对象锁, 进入等待flg:{}",flg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("1--唤醒flg：{}",flg);
            }
        }
    }
    static class notify implements Runnable{
        @Override
        public void run() {
            synchronized (obj){  // 因为 在线程wait中调用了wait()方法，释放了对象锁，这里可以获取obj对象锁
                while (flg){
                    log.info("2--线程开始：{}",Thread.currentThread().getName());
                    flg =false;
                    obj.notifyAll();  // 把 wait 移出等待队列，但是notify拿到了对象锁，所以等notify运行完，才运行wait后面部分。
                }
                log.info("2--唤醒flg：{}",flg);
            }
        }
    }

}
