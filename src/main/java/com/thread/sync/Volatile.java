package com.thread.sync;

import com.thread.demo.VolatileTest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author zsw
 * @date 2021/1/20 10:41
 * @description : 局部变量和成员变量线程安全问题
 * 如果它们没有共享，则线程安全
 * 如果它们被共享了，根据它们的状态是否能够改变，又分两种情况
 * 如果只有读操作，则线程安全
 * 如果有读写操作，则这段代码是临界区，需要考虑线程安全
 * --------------------------------------------------------------------------------
 * volatile 的特性:(1)保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。（实现可见性）
 * 它可以用来修饰成员变量和静态成员变量，他可以避免线程从自己的工作缓存中查找变量的值，必须到主存中获取
 * 它的值，线程操作 volatile 变量都是直接操作主存
 * (2) 避免指令重排
 * (3) 不保证原子性
 */
@Slf4j
public class Volatile {
    //先来看一个现象，main 线程对 run 变量的修改对于 t 线程不可见，导致了 t 线程无法停止
    boolean run = true;

    public static void main(String[] args) throws InterruptedException {
        Volatile aVolatile = new Volatile();
        volTest(aVolatile);
        vlTest(aVolatile);
    }
    static void volTest(Volatile v) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (v.run) {
                    log.info("doSomthing");
                }
            }
        });
        thread1.start();


//        try {
//            Thread.sleep(1);
//            run = false;
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
        static void vlTest(Volatile v){
            Thread thread2 = new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    Thread.sleep(1);
                    v.run = false;
//                    LockSupport.park();
                }
            });
            thread2.start();
//            LockSupport.unpark(thread2);
        }

}
