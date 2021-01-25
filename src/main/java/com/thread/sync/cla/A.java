package com.thread.sync.cla;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zsw
 * @date 2021/1/20 11:57
 * @description :
 */
@Slf4j
public abstract class A {
    abstract void a();

    static boolean f = true;


    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (f) {
                    log.info("true");
                    int i=0;
                    System.out.println(i++);
                }
                log.info("end");
            }
        }).start();

        new Thread(()->{
           f = false;
        }).start();

    }

}
