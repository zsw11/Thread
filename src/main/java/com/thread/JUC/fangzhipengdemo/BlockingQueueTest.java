package com.thread.JUC.fangzhipengdemo;

import com.sun.jmx.remote.internal.ArrayQueue;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zsw
 * @date 2021/1/26 11:41
 * LinkedBlockingQueue中维持两把锁，一把锁用于入队，一把锁用于出队，这也就意味着，同一时刻，只能有一个线程执行入队，其余执行入队的线程将会被阻塞；
 * 同时，可以有另一个线程执行出队，其余执行出队的线程将会被阻塞
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @description :认识BlockingQueue 先进先出的队列（Queue），为什么说是阻塞? 因为 BlockingQueue支持当获取队列元素但是队列为空时，会阻塞等待队列中有元素再返回；也支持添加元素时，如果队列已满，那么等到队列可以放入新元素时再放入
 * BlockingQueue 继承queue对插入操作、移除操作、获取元素操作提供了四种不同的方法用于不同的场景中使用：1、抛出异常；2、返回特殊值（null 或 true/false，取决于具体的操作）；3、阻塞等待此操作，直到这个操作成功；4、阻塞等待此操作，直到成功或者超时指定时间。总结如下
 * BlockingQueue 的实现都是线程安全的 ，但是批量操作addAll不一定是原子的
 */
@Slf4j
public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        //声明一个缓存为10的队列
        BlockingQueue<String> strings = new LinkedBlockingQueue<>(10);
//        ArrayQueue<String> strings= new ArrayQueue<String>(10);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    if (strings.isEmpty()){
                        strings.put("zsw");
                    }else {
                        String s = strings.take();
                        strings.put(s + "z");
                    }

                }
            }));
        }

//        executorService.shutdown();
        log.info("take:{}",strings);
//        String take = strings.take();
//        log.info("take:{}",take);

    }

}
