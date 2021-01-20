package com.thread.sync.solve;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zsw
 * @date 2021/1/20 10:09
 * @description : synchronized 加在方法上
 */
@Slf4j
public class SyncF {
    static class Number{
        public synchronized void a() {
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            log.info("1");
        }

        public synchronized void b(){
            log.info("2");
        }

        public void c() {
            log.debug("3");
        }
    }

    public static void main(String[] args) {
        Number number = new Number();
        new Thread(number::a).start();
        new Thread(number::b).start();
        new Thread(number::c).start(); //输出结果 3 睡眠1秒 1 2

    }
}
