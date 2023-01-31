package com.thread.JUC;

import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zsw
 * @date 2021/6/2 13:50
 * @description : 读写锁 ReadWriteLock (多个线程读读不加锁，读写加锁，写写加锁)
 *  独占锁（写锁）一次只能被一个线程占有
 *  共享锁（读锁） 多个线程可以占有
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        myCache myCache = new myCache();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        // 最好用这种方法创建线程池， 这里可以自己指定参数，并且用ScheduledExecutorService 中线程最大值为 Integer.MAX_VALUE，太大了。
        new ThreadPoolExecutor(5,8,10, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
        for (int i = 1; i <= 5; i++) {
            int a= i;
            executorService.execute(()->{myCache.put(String.valueOf(a),a);});
        }
        for (int i = 1; i <= 5; i++) {
            int a= i;
            executorService.execute(()->{myCache.get(String.valueOf(a));});
        }
        executorService.shutdown();
    }

}

class myCache {
    private volatile HashMap<Object, Object> cache = new HashMap<>();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put (String key, Object value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始写入");
            cache.put(key, value);
            System.out.println(Thread.currentThread().getName()+"写入完成"+value);
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
//            System.out.println(Thread.currentThread().getName() + "开始读取");
            Object o = cache.get(key);
            System.out.println(Thread.currentThread().getName() + "读取到了" + o);
            TimeUnit.SECONDS.sleep(5);//自己运行会发现 在运行写数据是串行的，每次写入数据都需要等待1S，而读数据则是瞬间5个线程一起读取的
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}