package com.thread.method;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

import static java.lang.System.out;

/**
 * @author zsw
 * @date 2021/1/19 14:27
 * @description : 礼让yield(), 礼让后两个线程是交替运行的，join
 * A线程的输出十分依赖B线程的输入，这个时候A线程就必须等待B线程执行完之后再根据线程B的执行结果进行输出。而JDK正提供了 join() 方法来实现这个功能
 * 结论：1，多个线程调用start（）方法时，线程都开始执行了，然后随机抢占cpu，yield()礼让，还是一起执行，（Yield告诉当前正在执行的线程为线程池中具有相同优先级的线程提供机会。）
 *      2，sleep(),让当前线程睡眠ms sleep 过程中线程不会释放锁，只会阻塞线程
 *      3, join(), 2线程中调用4线程的join(),则2线程会等4线程执行完，4加入2中，2先执行，在执行4。
 *
 */
@Slf4j
public class Yield {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Thread.yield();
            Calendar c = Calendar.getInstance();
            Date Start = c.getTime();
            log.info("1线程开始进行");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            int count = 0;
            for (int i=0;i<100;i++) {
                out.println("------1:" + count++);
            }
            Calendar c1 = Calendar.getInstance();
            Date end = c1.getTime();
            log.info("1线程结束进行,花费时间毫秒：" + (end.getTime() - Start.getTime()));
        });
//        thread.start();

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("3线程开始进行");
                Thread.yield();// 礼让，还是一起执行
                int count = 0;
                for (int i = 0; i < 100; i++) {
                    out.println("------3礼让:" + count++);
                }
            }
        });
//        thread3.start();

        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("4线程开始进行");
                int count = 0;
                for (int i = 0; i < 100; i++) {
                    out.println("------4join:" + count++);
                }
                log.info("4线程结束");
            }
        });
        thread4.start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                log.info("2线程开始进行");
                thread4.join();
                int count = 0;
                for (int i=0;i<100;i++) {
                    out.println("------2:" + count++);
                }
                log.info("2线程结束");
            }
        }).start();
    }


}
