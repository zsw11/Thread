package com.thread.demoPro;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @author zsw
 * @date 2021/1/26 16:03
 * @description : 获取Callable返回值结果
 */
@Slf4j
public class CallableTest implements Callable {
    private int id;

    public CallableTest(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {

        return "return of TaskRest:" + id;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            futures.add(executorService.submit(new CallableTest(i)));
        }
        for (Future fs : futures) {
            try {
                log.info((String) fs.get());
                log.info("是否：{}",fs.isDone());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                executorService.shutdown();
            }
        }
    }
}
