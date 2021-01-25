package com.thread.sync;

import com.sun.org.apache.xpath.internal.operations.String;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zsw
 * @date 2021/1/21 10:46
 * @description :
 * java5之后，并发包中新增了Lock接口（以及相关实现类）用来实现锁的功能，它提供了与synchronized关键字类似的同步功能。
 * 既然有了synchronized这种内置的锁功能，为何要新增Lock接口？先来想象一个场景：手把手的进行锁获取和释放，先获得锁A，然后再获取锁B，当获取锁B后释放锁A同时获取锁C，当锁C获取后，再释放锁B同时获取锁D，
 * 以此类推，这种场景下，synchronized关键字就不那么容易实现了，而使用Lock却显得容易许多。
 * 使用方式：
 * Lock lock = new ReentrantLock();
 * Condition condition = lock.newCondition();
 * lock.lock();
 * try {
 * while(条件判断表达式) {
 * condition.wait();
 * }
 * // 处理逻辑
 * } finally {
 * lock.unlock();
 * }
 * 需要显示的获取锁，并在finally块中显示的释放锁，目的是保证在获取到锁之后，最终能够被释放。
 * -----------------------------------------------------------------
 * java中已经有了内置锁：synchronized,synchronized的特点是使用简单，一切交给JVM去处理,不需要显示释放
 * 从用法上可以看出，与synchronized相比， ReentrantLock就稍微复杂一点。因为必须在finally中进行解锁操作，如果不在 finally解锁，有可能代码出现异常锁没被释放，
 * ReentrantLock的性能是明显优于synchronized的,ReentrantLock在功能上更加丰富，它具有可重入、可中断、可限时、公平锁等特点。
 * 可重入: 可重入是指同一个线程如果首次获得了这把锁，那么因为它是这把锁的拥有者，因此有权利再次获取这把锁
 *        如果是不可重入锁，那么第二次获得锁时，自己也会被锁挡住.
 */
@Slf4j
public class ReentrantLockTest extends Thread {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    public ReentrantLockTest(java.lang.String thread1) {
        super.setName(thread1);
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000; j++) {
            lock.lock();
            try {
                System.out.println(this.getName() + " " + i);
                i++;
            } finally {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
//        ReentrantLockTest thread1 = new ReentrantLockTest("thread1");
//        ReentrantLockTest thread2 = new ReentrantLockTest("thread2");
//        thread1.start();
//        thread2.start();
//        try {
//            thread1.join();
//            thread2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(i);
        m1();
    }

    /*
     * 可重入
     * */
    static void m1() {
        lock.lock();
        try{
            log.info("m1,execute");
            m2();
        }finally {
            lock.unlock();

        }

    }

    private static void m2() {
        lock.lock();
        try{
            log.info("m2,execute");
            m3();
        }finally {
            lock.unlock();

        }
    }

    private static void m3() {
        lock.lock();
        try{
            log.info("m3,execute");
        }finally {
            lock.unlock();

        }
    }


}
