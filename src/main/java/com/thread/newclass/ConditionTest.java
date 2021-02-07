package com.thread.newclass;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zsw
 * @date 2021/1/27 15:54
 * @description : Condition 依赖于lock，可以实现线程之间的通信，使await()让线程等待，signal()唤醒condition里的线程 比notify和wait更加的高效
 */
public class ConditionTest {
    static class ThreadA extends Thread {
        private MyService myService;

        public ThreadA(MyService myService) {
            this.myService = myService;
        }

        @Override
        public void run() {
           myService.awaitA();
        }
    }

    static class ThreadB extends Thread {
        private MyService myService;

        public ThreadB(MyService myService) {
            this.myService = myService;
        }

        @Override
        public void run() {
            myService.awaitB();
        }
    }

    static class MyService {
        private Lock lock = new ReentrantLock();
        // 使用多个condition实现通知部分线程
        public Condition conditionA = lock.newCondition();
        public Condition conditionB = lock.newCondition();

        public void awaitA(){
            lock.lock();
            try {
                System.out.println("A一会会await");
                conditionA.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
        public void awaitB(){
            lock.lock();
            try {
                System.out.println("B一会会await");
                conditionB.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
        public void signA(){
            lock.lock();
            try {
                System.out.println("A一会会Sign");
                conditionA.signalAll();
            }finally {
                lock.unlock();
            }
        }
        public void signB(){
            lock.lock();
            try {
                System.out.println("A一会会Sign");
                conditionB.signalAll();
            }finally {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        ThreadA threadA = new ThreadA(myService);
        threadA.start();
        ThreadB threadB = new ThreadB(myService);
        threadB.start();
        TimeUnit.SECONDS.sleep(3); // 睡眠3 S
        myService.signA();
        // ConditionA范围内的线程被唤醒
    }
}
