package com.thread.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zsw
 * @date 2021/1/21 13:41
 * @description : synchronized  锁住的是对象和类（锁住类和对象有什么区别？？？），而不是代码。类的每个对象都有一个监视器锁(monitor)，当monitor被占用时就会处于锁定状态。
 */
@Slf4j
public class SyncCla {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool(); // 创建一个线程池
        for (int i = 0; i < 10; i++) {
            // 开启10个行程
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    testCla testCla = new testCla();
                    testCla.testSync();  // 结果打印了全部的锁的是SynCla对象 ，再打印结束：{}，说明synchronizeds锁的是对象
                }
            });
        }
    }


    public static class testCla {
        //        public  synchronized void testSync() {  //.修饰在方法上，多个线程调用同一个对象的同步方法会阻塞，调用不同对象的同步方法不会阻塞。
        public void testSync() {
            String obj = new String("zsw");
            synchronized (obj) {  // 锁的是String对象。
//            synchronized (this) { //这个this就是指当前对象（类的实例），多个线程调用同一个对象的同步方法会阻塞，调用不同对象的同步方法不会阻塞。（java对象的内存地址是否相同）
//            synchronized (testCla.class) {  //锁的对象是testCla.class 类，即testCla类的锁。
                log.info("锁的是SynCla对象");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int i = 0;
                i++;
                log.info("结束：{}", i);
            }

        }
    }


}
