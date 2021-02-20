package com.thread.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author zsw
 * @date 2021/1/25 10:38
 * @description :  悲观互斥保护 synchronized
 */
@Slf4j
public class AccountSync implements Account {

    private Integer balance;

    public AccountSync(int i) {
        this.balance = i;
    }

    @Override
    public Integer getBalance() {
        synchronized (this){
            return this.balance;
        }
    }

    @Override
    public void withdraw(Integer amount) {
        synchronized (this) {
            this.balance -= amount;
        }
    }
    /*
     *方法内开启100个线程，每个线程做-10的原子操作
     * */
    static void demo(Account account) {
        ArrayList<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ts.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    account.withdraw(10);
                }
            }));
        }
        long start = System.nanoTime();
        ts.forEach(Thread::start);  // 开启集合里的每一个线程
        ts.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();  // 纳秒
        log.info("start:{},end:{}",start,end);
        System.out.println(account.getBalance()
                + " cost: " + (end-start)/1000_000 + " ms");
    }

    public static void main(String[] args) {
        AccountSync accountSync = new AccountSync(10000);
        demo(accountSync);
    }
}
