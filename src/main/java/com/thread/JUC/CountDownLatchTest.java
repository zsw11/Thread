package com.thread.JUC;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zsw
 * @date 2021/1/27 14:41
 * @description : CountDownLatch  闭锁   CountDownLatch是一个异步辅助类，它能让一个和多个线程处于等待状态，直到其他线程完成了一些列操作。比如某个线程需要其他线程执行完毕才能执行其他的
 *
 * countDownLatch这个类使一个线程等待其他线程各自执行完毕后再执行。
 * 是通过一个计数器来实现的，计数器的初始值是线程的数量。每当一个线程执行完毕后，计数器的值就-1，当计数器的值为0时，表示所有线程都执行完毕，然后在闭锁上等待的线程就可以恢复工作了。
 *
 * //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
 * public void await() throws InterruptedException { };
 * //和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
 * public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };
 * //将count值减1
 * public void countDown() { };
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(1000); //1000个线程
        for (int i = 0; i < 100; i++) {
            CountDownLatchable countDownLatchable = new CountDownLatchable(countDownLatch);
            executorService.execute(countDownLatchable);
        }
        try {
            countDownLatch.await(); // 线程挂起，直到count为0时，才继续运行,1000个线程减去100个，线程运行到900个时，会阻塞。
            System.out.println("："+(100-countDownLatch.getCount()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class CountDownLatchable implements Runnable{
        private CountDownLatch countDownLatch;

        public CountDownLatchable(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            synchronized (countDownLatch){
                // 每次减一
                countDownLatch.countDown();
                System.out.println("thread counts:"+countDownLatch.getCount());
            }



        }
    }
}
