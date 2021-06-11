package com.thread.demo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zsw
 * @date 2021/1/22 10:03
 * @description : 死锁
 * jps -l 显示当前所有java进程pid的命令
 *  jstack  pid 显示java指定进制堆栈信息
 *
 * Java stack information for the threads listed above:
 * ===================================================
 * "Thread-1":
 *         at com.thread.demo.SiLock$2.run(SiLock.java:38)
 *         - waiting to lock <0x0000000780e73060> (a java.lang.Object)
 *         - locked <0x0000000780e73070> (a java.lang.Object)
 *         at java.lang.Thread.run(Thread.java:748)
 * "Thread-0":
 *         at com.thread.demo.SiLock$1.run(SiLock.java:24)
 *         - waiting to lock <0x0000000780e73070> (a java.lang.Object)
 *         - locked <0x0000000780e73060> (a java.lang.Object)
 *         at java.lang.Thread.run(Thread.java:748)
 *
 * Found 1 deadlock.
 */
@Slf4j
public class SiLock {
    static boolean flg;
    static void lockTest(){
        Object obj1 = new Object();
        Object obj2 = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj1) {
                    SiLock.flg = true;
                    log.info("t1,obj1");
                    synchronized (obj2) {
                        SiLock.flg = false;
                        log.info("t1,obj2");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj2) {
                    SiLock.flg = false;
                    log.info("t2,obj2");
                    synchronized (obj1) {
                        SiLock.flg = true;
                        log.info("t2,obj1");
                    }
                }

            }
        }).start();
    }

    public static void main(String[] args) {
        lockTest();
    }
}
