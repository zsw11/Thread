package com.thread.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zsw
 * @date 2021/1/22 10:47
 * @description : ReentrantLock 可重入，可打断，锁超时，公平锁，条件变量
 */
@Slf4j
public class ReenTrantLoclTest {

    static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        method1();
    }


    public static void method1() {
        lock.lock();
        try {
            log.debug("execute method1");
            method2();
        } finally {
            lock.unlock();
        }
    }
    public static void method2() {
        lock.lock();
        try {
            log.debug("execute method2");
            method3();
        } finally {
            lock.unlock();
        }
    }
    public static void method3() {
        lock.lock();
        try {
            log.debug("execute method3");
        } finally {
            lock.unlock();
        }
    }
}
