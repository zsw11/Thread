package com.thread.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author zsw
 * @date 2021/4/12 15:24
 * @description : 输出对象头信息
 */
public class T {
    private static Object o = new Object();
    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
