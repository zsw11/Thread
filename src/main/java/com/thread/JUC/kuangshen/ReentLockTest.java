package com.thread.JUC.kuangshen;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zsw
 * @date 2021/5/31 11:41
 * @description : ReentrantLock
 */
public class ReentLockTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        sale1 sale = new sale1();
        for (int i=0;i<40;i++) {
            executorService.execute(sale::sell);
        }
    }
}

// 线程是一个单独的资源类，把线程资源抽出一个单独的类
 class sale1{
    private int ticket = 30;
    void sell(){
        ReentrantLock reentrantLock = new ReentrantLock(false);// 可重入锁，无参false默认是非公平锁
//        reentrantLock.tryLock();
        reentrantLock.lock();
        try {
            if (ticket > 0) {
                System.out.println("出售倒数第"+ticket--+",张票，剩余："+(ticket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
}