package com.thread.create;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

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

    static void runnable(){
        Runnable runnable = () -> log.info("runnable创建开始");
        Thread thread = new Thread(runnable);
        thread.setName("runnable");
        thread.start();
    }

    static void Future() throws ExecutionException, InterruptedException {
        FutureTask<String> stringFutureTask = new FutureTask<>(() -> {
            log.info("hello,Future");
            return "100";
        });
        Thread futureThread = new Thread(stringFutureTask, "FutureThread");
        futureThread.start();
        stringFutureTask.get();


    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        thread();
        runnable();
        Future();

    }

}
