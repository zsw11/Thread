package com.thread.JUC;
  
import java.util.concurrent.ArrayBlockingQueue;  
import java.util.concurrent.BlockingQueue;  
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
  
/**
 *BlockingQueueTest
 */  
public class BlockingQueueTest {  
    public static class Basket {  
  
        BlockingQueue<String> basket = new ArrayBlockingQueue<String>(2);  
  
        //生产苹果  
        public void product() throws InterruptedException {  
            basket.put("An apple");
            System.out.println(basket);
        }  
  
        public String consume() throws InterruptedException {  
            return basket.take();  
        }  
    }  
  
    public static void testBasket(){  
        final Basket basket = new Basket();//建立一个装苹果的篮子  
  
        class Producer implements Runnable {  
  
            @Override  
            public void run() {  
                try {  
                    while(true) {  
                        //生产苹果  
  
                        System.out.println("..................生产者准备生产苹果: " + System.currentTimeMillis());  
  
                        basket.product();  
  
//                        System.out.println("生产者生产苹果完毕: " + System.currentTimeMillis());  
  
                        //休眠300ms  
  
                        Thread.sleep(300);  
                    }  
                } catch (InterruptedException  e1){  
                    e1.printStackTrace();  
                }  
            }  
  
        }  
  
        class Cousumer implements Runnable {  
  
            @Override  
            public void run() {  
                try {  
                    while(true){  
  
                        //消费苹果  
                        System.out.println("*******************消费者准备消费苹果: " + System.currentTimeMillis());  
  
                        basket.consume();  
  
//                        System.out.println("消费者消费苹果完毕: " + System.currentTimeMillis());  
  
                        //休眠1000ms  
  
                        Thread.sleep(1000);  
  
                    }  
                } catch (InterruptedException e2) {  
  
                }  
            }  
  
        }  
  
        ExecutorService service = Executors.newCachedThreadPool();  
        Producer producer = new Producer();  
        Cousumer cousumer = new Cousumer();  
        service.submit(producer);  
        service.submit(cousumer);  
  
        try{  
  
            Thread.sleep(50000);  
  
        }catch(InterruptedException ex){  
  
        }  
  
        service.shutdownNow();  
    }  
  
    public static void main(String[] args){  
  
        BlockingQueueTest.testBasket();  
  
    }  
}  