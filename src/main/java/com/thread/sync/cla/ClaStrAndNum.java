package com.thread.sync.cla;

import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zsw
 * @date 2021/1/20 11:04
 * @description :不可变类线程安全性
 *
 * String、Integer 等都是不可变类，因为其内部的状态不可以改变，因此它们的方法都是线程安全的，
 */
@Slf4j
public class ClaStrAndNum {
    public static class Immutable{
        private static int value = 0;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            Immutable.value = value;
        }

        public Immutable(int value) {
            Immutable.value = value;
        }

        // 添加值的方法
        public Immutable add(int v){
            return new Immutable(value + v);
        }

        public int add2(int v){
            return value + v;
        }
    }

    public class MyServlet extends HttpServlet {
        // 是否安全？
        Map<String,Object> map = new HashMap<>();
        // 是否安全？
        String S1 = "...";
        // 是否安全？
        final String S2 = "...";
        // 是否安全？
        Date D1 = new Date();
        // 是否安全？
        final Date D2 = new Date();
        public void doGet(HttpServletRequest request, HttpServletResponse response) {
       // 使用上述变量
        }
    }

    public static void main(String[] args) {
//        Immutable.value
        Immutable immutable = new Immutable(0);
        Immutable immutable1 = immutable.add(1);
        immutable1.add2(1);
        log.info(String.valueOf(immutable1.getValue()));
        int immutable2 = immutable.add2(1);
        log.info(String.valueOf(immutable2));
    }
}
