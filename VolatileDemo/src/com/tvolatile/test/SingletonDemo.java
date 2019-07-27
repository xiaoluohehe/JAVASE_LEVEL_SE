package com.tvolatile.test;

/**
 * 单例模式
 */
public class SingletonDemo {

    private static volatile SingletonDemo instance=null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName() + "\t 我是单例方法SingletonDemo");
    }

    //加锁 synchronized
    //DCL (Double Check Lock 双端检锁机制，进来前和进来后分别判断两次)，会出现问题有99.99%指令重排
    public static  SingletonDemo getInstance(){
        //TODOaaa123213
        if (instance==null){  //加锁之前和加锁之后都有判断，双端检锁机制
            synchronized (SingletonDemo.class){
                if (instance==null){
                    instance=new SingletonDemo();
                }
            }
        }
        return instance;
    }

    //多次获得
    public static void main(String[] args) {
        //单线程main线程
//        System.out.println(SingletonDemo.getInstance() == getInstance());
//        System.out.println(SingletonDemo.getInstance() == getInstance());
//        System.out.println(SingletonDemo.getInstance() == getInstance());

        //并发的多线程后
        for (int i = 0; i < 10; i++) {
            new Thread(()->{

                SingletonDemo.getInstance();

            },String.valueOf(i)).start();
        }
    }



}
