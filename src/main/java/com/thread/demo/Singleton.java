package com.thread.demo;

import java.io.Serializable;

/**
 * @author zsw
 * @date 2021/1/22 14:53
 * @description : 单例   反射会破坏单例模式，但是无法破坏枚举的单例模式，（因为在反射时，代码就判断不为枚举才能进行反射）
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
//方式二 枚举类单例模式， 可以防止反射破坏单例
    enum Singleton2{
        SINGLETON_2;
        public Singleton2 getInstance(){
            return SINGLETON_2;
        }
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

    public static void main(String[] args) {
//        Singleton2 zsw = Singleton2.valueOf("SINGLETON_2");
        Singleton2 singleton2 = Singleton2.SINGLETON_2;
        System.out.println(singleton2);

    }

}
