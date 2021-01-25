package com.thread.demo;

import java.io.Serializable;

/**
 * @author zsw
 * @date 2021/1/22 14:53
 * @description : 单例
 * 饿汉式：类加载就会导致该单实例对象被创建
 * 懒汉式：类加载不会导致该单实例对象被创建，而是首次使用该对象时才会创建
 */
public final class Singleton implements Serializable {
    private Singleton(){};
    private static final Singleton singleton = new Singleton();
    public static Singleton getSingleton(){
        return singleton;
    }
    public Singleton readSingleton(){
        return singleton;
    }
//方式二
    enum Singleton2{
        SINGLETON_2;
    }
//    方式三
       public  class Singleton3 {
        private Singleton3 singleton3;
        private Singleton3(){};
        public  Singleton3 getSingleton3(){
            if (singleton3 != null) {
                return singleton3;
            }
          return  singleton3 = new Singleton3();
        }
    }


}
