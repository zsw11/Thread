package com.thread.JUC.fangzhipengdemo;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zsw
 * @date 2021/1/26 13:56
 * @description :hashtable每次lock都要锁住整个结构，
 * ConcurrentHashMap锁的方式是稍微细粒度的。 ConcurrentHashMap将hash表分为16个桶（默认值）诸如get,put,remove等常用操作只锁当前需要用到的桶。
 * 试想，原来 只能一个线程进入，现在却能同时16个写线程进入。并发能力提高了很多。
 */
@Slf4j
public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        ConcurrentHashMap<Integer,Integer> concurrentHashMap = new ConcurrentHashMap<>();
        int i=1;
        int n = 1;
        for (int a = 0; a < 10; a++) {
            hashMap.put(i, i++);
            concurrentHashMap.put(n, n++);
        }


        for (int j = 0; j < 10; j++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    hashMap.isEmpty();
                    if (!hashMap.isEmpty()) {
                        hashMap.put(10, 999);
                        hashMap.put(10,666);
                    }
                }
            });
            thread.start();

        }

//        ExecutorService executorService = Executors.newCachedThreadPool();
//        final int[] j = {0};
//        final int[] a= {0};
//        for (int i = 0; i < 100; i++) {
//            executorService.submit(new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    hashMap.put(j[0]++, j[0]++);
//                    hashMap.remove(j[0]--);
//                }
//            }));
//            executorService.submit(new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    concurrentHashMap.put(a[0], a[0]++);
//                }
//            }));
//
//        }
        hashMap.forEach((integer, integer2) -> log.info("hashmap:{},{}",integer,integer2));
        concurrentHashMap.forEach((integer, integer2) -> log.info("concurrentHashMap:{},{}",integer,integer2));

    }
}
