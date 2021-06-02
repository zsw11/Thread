package com.thread.JUC.kuangshen;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author zsw
 * @date 2021/5/31 16:54
 * @description :
 */
public class ListTest {
    public static void main(String[] args) throws InterruptedException {
        // java.util.ConcurrentModificationException  并发修改异常
//        ArrayList<String> list = new ArrayList<>();
//        Vector list = new Vector();  // 使用synchronized 实现同步
//        List<Object> list = Collections.synchronizedList(new ArrayList<>()); // 使用synchronized
        // 写入时加锁复制一份，避免覆盖。读取不加锁。
        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>(); // 使用 lock
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0, 4));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
//        TimeUnit.SECONDS.sleep(1);
//        System.out.println(list);
    }
}
