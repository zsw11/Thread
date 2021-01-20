package com.thread.sync.solve;

import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

/**
 * @author zsw
 * @date 2021/1/19 17:18
 * @description : 面向对象思想改进sync，把value 封装成一个room对象
 */
@Slf4j
public class RoomSync{
    static class room{
        private int value =0;
        public void incr(){
            synchronized(this){
                value++;
            }
        }
        public void decr(){
            synchronized(this){
                value--;
            }
        }

        public int getValue() {
            return this.value;
        }
    }

    public static void main(String[] args) {
        room room = new room();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    room.incr();
                }
            }
        },"c");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 11; i++) {
                    room.decr();
                }
            }
        },"thread2");
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //主线程 ,这个地方是主线程获取value的值，如果不加join（）方法，则可能没等thread1和thread2执行完，主线程main就获取了value的值，此时就不对
        int value = room.getValue();
        log.info("value:{}",value);

    }
}
