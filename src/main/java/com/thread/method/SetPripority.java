package com.thread.method;

import static java.lang.System.out;

/**
 * @author zsw
 * @date 2021/1/19 13:57
 * @description : 线程优先级
 *
 */
public class SetPripority {

    public static void main(String[] args) throws InterruptedException {
        Runnable task1 = () -> {
            int count = 0;
            for (int i=0;i<100;i++) {
                out.println("------1:" + count++);
            }
        };
        Runnable task2 = ()->{
            Thread.yield();// 线程礼让
            int count = 0;
            for(int i=0;i<100;i++){
                out.println("------2:"+count++);
            }
        };
        Runnable task3 = () -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            int count = 0;
            for(int i=0;i<100;i++){
                out.println("------3:"+count++);
            }
        };
        Thread thread1 = new Thread(task1,"task1");
        Thread thread2 = new Thread(task2, "task2");//两个线程交替一起进行，cpu来回切换
        Thread thread3 = new Thread(task3, "task3");
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.MIN_PRIORITY);// 优先级高的会更容易获取cpu执行权，但是两个线程还是交替进行
        thread1.start();
        thread2.start();
        thread3.start();
        thread3.join();// 线程3加入到线程1，2之间
    }

}
