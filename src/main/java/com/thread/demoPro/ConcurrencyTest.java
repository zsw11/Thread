package com.thread.demoPro;

import com.thread.method.Join;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zsw
 * @date 2021/1/25 17:21
 * @description : 并发不一定比串行时间短
 * 当并发执行累加操作不超过百万次时，速度会比串行执行累加操作要
 * 慢。那么，为什么并发执行的速度会比串行慢呢？这是因为线程有创建和上下文切换的开销。
 *
 * 本章介绍了在进行并发编程时，大家可能会遇到的几个挑战，并给出了一些解决建议。有
 * 的并发程序写得不严谨，在并发下如果出现问题，定位起来会比较耗时和棘手。所以，对于
 * Java开发工程师而言，笔者强烈建议多使用JDK并发包提供的并发容器和工具类来解决并发
 * 问题，因为这些类都已经通过了充分的测试和优化，均可解决了本章提到的几个挑战。
 */
@Slf4j
public class ConcurrencyTest {
    private final static long count =100000000;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        tonBu();
    }
    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (int i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for (int i = 0; i < count; i++) {
            b--;
        }
        long end = System.currentTimeMillis();
        thread.join();
       log.info("花费时间为：{}ms",end-start);
    }
    private static void tonBu(){
        int a = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            a+=5;
        }
        int b = 0;
        for (int j = 0; j < count; j++) {
            b--;
        }
        long end = System.currentTimeMillis();
        log.info("花费时间为：{}ms", end - start);
    }
}
