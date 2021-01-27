package com.thread.demoPro;

import java.util.concurrent.ThreadFactory;

/**
 * @author zsw
 * @date 2021/1/26 16:40
 * @description : 创建线程池工厂
 */
public class DaemonsThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true); // 工厂类里把线程都设置为后台线程
        return thread;

    }
}
