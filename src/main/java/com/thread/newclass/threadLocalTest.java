package com.thread.newclass;

/**
 * @author zsw
 * @date 2021/1/27 16:31
 * @description :threadLocal 一句话概括：Synchronized用于线程间的数据共享，而ThreadLocal则用于线程间的数据隔离
 * Thread有个TheadLocalMap类型的属性，叫做threadLocals，该属性用来保存该线程本地变量。这样每个线程都有自己的数据，就做到了不同线程间数据的隔离，保证了数据安全。
 */
public class  threadLocalTest {

    public static void main(String[] args) {
        Thread zsw = new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadLocal<String> threadLocal = new ThreadLocal<>();
                threadLocal.set("zsw");
                threadLocal.set("zsw111");
//                threadLocal.remove();
                System.out.println( threadLocal.get());
            }
        });
        zsw.start();
    }
}
