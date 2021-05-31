package com.thread.JUC.kuangshen;

import com.thread.sync.cla.A;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zsw
 * @date 2021/5/31 14:43
 * @description : 生产者消费者 进阶版
 * 工厂生产线    生产A，后生产B,再生产C. 按顺序生产
 */
public class ProductTest {
    public static void main(String[] args) {
        product product = new product();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 9; i++) {
            executorService.execute(() -> {
                product.A();
            });
            executorService.execute(() -> {
                product.B();
            });
            executorService.execute(() -> {
                product.C();
            });
        }
    }
}

class product {
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    private int number = 1; //1A - 2B -  3C

    public void A() {
        lock.lock();
        try {
            while (number != 1) {
                conditionA.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>AAAAAAAAAAA");
            number = 2;
            conditionB.signal(); // 唤醒指定的B
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public void B() {
        lock.lock();
        try {
            while (number != 2) {
                conditionB.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>BBBBBBBBBB");
            number = 3;
            conditionC.signal(); // 唤醒指定的C
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public void C() {
        lock.lock();
        try {
            while (number != 3) {
                conditionC.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>CCCCCCCCCC");
            number = 1;
            conditionA.signal(); // 唤醒指定的A
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}