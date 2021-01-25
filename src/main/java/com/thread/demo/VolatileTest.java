package com.thread.demo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zsw
 * @date 2021/1/22 13:36
 * @description :Volatile ,,这里flag变量，不用volatile修饰，在main线程中修改了flag值，在VolatileTest线程中也能读到。目前理解是，VolatileTest线程修改了值，又把数据同步组内存中
 */
@Slf4j
public class VolatileTest extends Thread{

    boolean flag = false;
    int i=0;

    @Override
    public void run() {
        while (!flag){  //那就是因为VolatileTest线程每次判断flag标记的时候是从它自己的“工作内存中”取值，而并非从主main内存中取值！
            i++;
            log.info(String.valueOf(i));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTest volatileTest = new VolatileTest();
        volatileTest.start();
        Thread.sleep(1000);
        volatileTest.flag = true;
        log.info("i:{}",volatileTest.i);
    }
}
