package com.thread.method;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zsw
 * @date 2021/1/19 13:45
 * @description :start 与 run区别
 */
@Slf4j
public class StartAndRun {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug(Thread.currentThread().getName());
            }
        };
//        t1.run();
        t1.start();
        log.info("do other things ...");
    }
}
