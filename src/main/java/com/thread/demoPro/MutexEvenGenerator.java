package com.thread.demoPro;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zsw
 * @date 2021/1/27 10:13
 * @description :Lock对象必须显示的创建锁定释放
 */
public class MutexEvenGenerator extends IntGenerator {

    private int currentEventValue = 0;
    private Lock lock = new ReentrantLock();
    @Override
    public int next() {
        try {
            lock.lock();
            ++currentEventValue;
            Thread.yield(); // 提高并发问题，但是不会发生
            ++currentEventValue;
            return currentEventValue;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        EvenChecker.test(new MutexEvenGenerator());
    }
}
