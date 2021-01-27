package com.thread.demoPro;

/**
 * @author zsw
 * @date 2021/1/26 15:29
 * @description :
 */
public class LiftOff implements Runnable {
    int countDown = 10;
    private static int taskCount = 0;
    private final int id =taskCount++;

    public String status(){
        return "#" + id + "(" + (countDown > 0 ? countDown : "liffOff")+"),";
    }
    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
//            Thread.yield();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
//        LiftOff liftOff = new LiftOff();
//        liftOff.run();  创建任务

//        Thread thread = new Thread(new LiftOff());
//        thread.start();
//        System.out.println("waitting for LiffOff");  主线程和thread线程一起运行

        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
            System.out.println("waiting for LiffOff");  // 多个线程交织进行
        }
    }
}
