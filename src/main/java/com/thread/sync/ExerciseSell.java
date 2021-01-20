package com.thread.sync;

import lombok.extern.slf4j.Slf4j;

import javax.activation.MailcapCommandMap;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author zsw
 * @date 2021/1/20 13:44
 * @description : 卖票练习
 */
@Slf4j
public class ExerciseSell {
    /*
    * 面向对象思想，票对象
    * */
    static class TicketWindows{
        private int count;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public TicketWindows(int count) {
            this.count = count;
        }
// 定义出售方法,
        public synchronized int sell(int amount){
            if(count>= amount){
                this.count -= amount;
                return amount;
            }else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        TicketWindows ticketWindows = new TicketWindows(500);// 总票数
        ArrayList<Thread> ThreadCount = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<100;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    //出售票,随机0到5张
                    int sell = ticketWindows.sell(new Random().nextInt(5) + 1);
                    log.info("出售：{}",sell);
                    list.add(sell);
                }
            },"thread"+i);
            thread.start();
            ThreadCount.add(thread);
        }
        ThreadCount.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });// 保证主线程在其他线程之后运行
        log.info("sell count:{}",list.stream().mapToInt(c->c).sum());
        log.info("remain count:{}", ticketWindows.getCount());


    }
}
