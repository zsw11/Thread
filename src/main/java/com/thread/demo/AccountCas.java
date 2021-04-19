package com.thread.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zsw
 * @date 2021/1/25 10:53
 * @description : 另外一种是乐观锁思想，它其实不是互斥
 * AtomicInteger 原子类操作 使用非阻塞算法来实现并发控制。、
 *  AtomicInteger可以在并发情况下达到原子化更新，避免使用了synchronized，而且性能非常高。
 * 对于Java中的运算操作，例如自增或自减，若没有进行额外的同步操作，在多线程环境下就是线程不安全的
 * num++解析为num=num+1，明显，这个操作不具备原子性，多线程并发共享这个变量时必然会出现问题
 *
 */
@Slf4j
public class AccountCas implements Account {

    private AtomicInteger balance;

    public AccountCas(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(Integer amount) {
        while (true) {
            // 获取余额的最新值
            int prev = balance.get();
            // 修改后的余额
            int next = prev - amount;
            //真正修改的
            if (balance.compareAndSet(prev, next)) {
                break;
            }
        }
    }

    static void demo(AccountCas accountCas) {
        ArrayList<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ts.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    accountCas.withdraw(10);
                }
            }));
        }

    }

    public static void main(String[] args) throws InterruptedException {
//        AccountCas accountCas = new AccountCas(100);
//        demo(accountCas);
////        Thread.currentThread().join();
//        Thread.sleep(1000);
//        log.info("余额:{}", accountCas.getBalance());
        AtomicInteger atomicInteger = new AtomicInteger();  //AtomicInteger的基本方法
        log.info("i:{}",atomicInteger.get()); // 0
        log.info("i:{}",atomicInteger.getAndIncrement()); // 0
        log.info("i:{}",atomicInteger.getAndSet(1)); // 1
        atomicInteger.set(11);
        // 把atomicInteger值和expect值做比较，如果相等，则返回true，atomicInteger值为update。如果不相等返回false，atomicInteger值为原来的值
        while (true){
            boolean b = atomicInteger.compareAndSet(11, 12);
            if (b){
                break;
            }
        }
        log.info("boolean:{},比较后结果：{}",atomicInteger.compareAndSet(12,13),atomicInteger);
    }
}
