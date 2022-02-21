package com.thread.JUC;

import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue 也是一个队列来的，但它的特别之处在于它内部没有容器，一个生产线程，当它生产产品（即put的时候），
 * 如果当前没有人想要消费产品(即当前没有线程执行take)，此生产线程必须阻塞，等待一个消费线程调用take操作，take操作将会唤醒该生产线程，
 * 同时消费线程会获取生产线程的产品（即数据传递），这样的一个过程称为一次配对过程(当然也可以先take后put,原理是一样的)。
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        final SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();

        Thread putThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("put thread start");
                try {
                    queue.put(1);
                } catch (InterruptedException e) {
                }
                System.out.println("put thread end");
            }
        });

        Thread putThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("put thread start2");
                try {
                    queue.put(2);
                } catch (InterruptedException e) {
                }
                System.out.println("put thread end2");
            }
        });

        Thread takeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("take thread start");
                try {
                    System.out.println("take from Thread: " + queue.take());
                } catch (InterruptedException e) {
                }
                System.out.println("take thread end");
            }
        });

        putThread.start();
        putThread2.start();//  1和2只有一个会被 take()另外一个会被阻塞，直到被一个线程take()
        Thread.sleep(1000);
        takeThread.start();
    }
//    put thread start   // queue.put(1);后线程就阻塞了。只有 queue.take()消费了，put线程才会返回
//    take thread start
//    take from putThread: 1
//    put thread end
//    take thread end
}