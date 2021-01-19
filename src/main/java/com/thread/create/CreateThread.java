package com.thread.create;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zsw
 * @date 2021/1/19 10:34
 * @description :   直接使用 Thread
 */
@Slf4j(topic = "thread")
public class CreateThread {

    public static void thread(){
        Thread thread = new Thread(() -> log.info("创建开始"));
        thread.setName("thread");
        thread.start();
    }

    public static void main(String[] args) {
        thread();
    }

}
