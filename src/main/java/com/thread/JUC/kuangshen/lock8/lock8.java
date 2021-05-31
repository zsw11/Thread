package com.thread.JUC.kuangshen.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author zsw
 * @date 2021/5/31 15:37
 * @description : lock8 什么是锁，锁住的是什么？ 对象实例？ 还是class？
 */
public class lock8 {
    public static void main(String[] args) {
        phone phone = new phone();
        new Thread(()->{phone.sendSms();}).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{phone.sendPhone();}).start();
        new Thread(()->{phone.hello();}).start();
    }
}
class phone{
    // synchronized 锁住的是 phone的实例（对象）
    // 两个 方法用的是同一个锁，谁先拿到谁先执行
    public synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
            System.out.println("发短信");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendPhone(){
        System.out.println("打电话");
    }

    // 静态锁的是class,只有一个锁
    public static synchronized void sendPhone1(){
        System.out.println("打电话");
    }

    // 这里没有锁
    public void hello(){
        System.out.println("hello");
    }
}
