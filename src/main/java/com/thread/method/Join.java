package com.thread.method;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zsw
 * @date 2021/1/19 15:39
 * @description :
 */
@Slf4j
public class Join {

    static int r = 0;
    public static void main(String[] args) throws InterruptedException {
        test1();
    }
    private static void test1() throws InterruptedException {
        log.debug("开始");
        Thread t1 = new Thread(() -> {
            log.debug("开始");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("结束");
            r = 10;
        });
        t1.start();
        t1.join(); // 加了这个r的结果为10，不加为0
        log.debug("结果为:{}", r);
        log.debug("结束");
    }
}
