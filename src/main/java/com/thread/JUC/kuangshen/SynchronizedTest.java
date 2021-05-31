package com.thread.JUC.kuangshen;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zsw
 * @date 2021/5/31 11:41
 * @description : synchronized
 */
public class SynchronizedTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        sale sale = new sale();
        for (int i=0;i<40;i++) {
            executorService.execute(sale::sell);
        }
    }
}
// 线程是一个单独的资源类，把线程资源抽出一个单独的类
 class sale{
    private int ticket = 30;
    synchronized void sell(){
        if (ticket > 0) {
            System.out.println("出售倒数第"+ticket--+",张票，剩余："+(ticket));
        }
    }
}