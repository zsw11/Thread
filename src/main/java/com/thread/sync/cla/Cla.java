package com.thread.sync.cla;

import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;

/**
 * @author zsw
 * @date 2021/1/20 10:48
 * @description :常见线程安全类
 * String
 * Integer
 * StringBuffer
 * Random
 * Vector
 * Hashtable
 * java.util.concurrent 包下的类 currentHashMap atomic  lock
 *
 */
@Slf4j
public class Cla {
    // 这里说它们是线程安全的是指，多个线程调用它们同一个实例的某个方法时，是线程安全的。也可以理解为
   private static Hashtable<String, Object> hashtable = new Hashtable<>();
    private static void m(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("value1");
                Cla.hashtable.put("key", "value1");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("value2");
                hashtable.put("key", "value2");
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        m();
        Thread.sleep(1000); // 睡眠一秒等 m()方法线程执行完
        Object key = hashtable.get("key");
        log.info(key.toString());
    }
}
