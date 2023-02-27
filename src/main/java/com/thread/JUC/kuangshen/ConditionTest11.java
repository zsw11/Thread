package com.thread.JUC.kuangshen;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 54623
 * @date 2023/2/27 16:51
 */
public class ConditionTest11 {

    private Lock lock = new ReentrantLock();
    private volatile int flag = 1;
    public Condition conditionA = lock.newCondition();
    public Condition conditionB = lock.newCondition();
    public Condition conditionC = lock.newCondition();

    public void  a(){
        lock.lock();
        try {
            while (flag != 1) {
                conditionA.await();
            }
            System.out.println("1");
            flag = 2;
            conditionB.signalAll();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void  b(){
        lock.lock();
        try {
            while (flag !=2) {
                conditionB.await();
            }
            System.out.println("2");
            flag = 3;
            conditionC.signalAll();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void  c(){
        lock.lock();
        try {
            while (flag !=3) {
                conditionC.await();
            }
            System.out.println("3");
            flag = 1;
            conditionA.signalAll();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionTest11 conditionTest11 = new ConditionTest11();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> conditionTest11.a(), "1").start();
            new Thread(() -> conditionTest11.b(), "2").start();
            new Thread(() -> conditionTest11.c(), "3").start();
        }

    }
}
