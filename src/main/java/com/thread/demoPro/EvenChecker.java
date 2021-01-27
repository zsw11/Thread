package com.thread.demoPro;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zsw
 * @date 2021/1/26 17:35
 * @description :
 */
@Slf4j
public class EvenChecker implements Runnable {

    public IntGenerator intGenerator;
    private final int id;

    public EvenChecker(IntGenerator intGenerator, int id) {
        this.intGenerator = intGenerator;
        this.id = id;
    }

    @Override
    public void run() {
        while (!intGenerator.isCanceled()){
            int val = intGenerator.next();    // 10个线程不停地运行
//            log.info("-----------------------,{}",val);
            if (val % 2 != 0) {
                log.info("不能整除：{}",val);
                intGenerator.cancel();
            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            intGenerator.stop();
        }
    }

     public static void test(IntGenerator intGenerator,int count) {
         ExecutorService executorService = Executors.newCachedThreadPool();
         for (int i = 0; i < count; i++) {
             executorService.execute(new EvenChecker(intGenerator,i));
         }
         executorService.shutdown();
     }

     public static void test(IntGenerator intGenerator) {
        test(intGenerator,10);
     }


}
