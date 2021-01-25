package com.thread.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author zsw
 * @date 2021/1/25 10:25
 * @description :互斥实际是悲观锁的思想
 */
public interface Account {
    /**
     * 获取余额
     * @return
     */
    Integer getBalance();

    /**
     * 取款
     * @param amount
     */
    void withdraw(Integer amount);


    public static void main(String[] args) {
//        demo();
    }
}
