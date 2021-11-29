package com.thread.hreadPoolExecutor.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author 54623
 * @date 2021/11/29 22:50
 * Future虽然可以实现获取异步执行结果的需求，但是它没有提供通知的机制，我们无法得知Future什么时候完成。
 *
 * 要么使用阻塞，在future.get()的地方等待future返回的结果，这时又变成同步操作。要么使用isDone()轮询地判断Future是否完成，这样会耗费CPU的资源。 https://www.jianshu.com/p/dff9063e1ab6
 *
 */
public class futureTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<String> future = executor.submit(() -> { //Lambda 是一个 callable， 提交后便立即执行，这里返回的是 FutureTask 实例
            System.out.println("running task");
//            Thread.sleep(10000);
            return "return task";
        });
        Thread.sleep(1000);
        System.out.println("do something else");  //前面的的 Callable 在其他线程中运行着，可以做一些其他的事情

        try {
            System.out.println(future.get());  //等待 future 的执行结果，执行完毕之后打印出来
        } catch (InterruptedException | ExecutionException ignored) {
        } finally {
            executor.shutdown();
        }
    }

}
