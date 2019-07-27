package com.tvolatile.test;

import java.util.concurrent.atomic.AtomicInteger;

class Mydate{
   volatile   int number =0;

    public void add(){
        this.number=60;
    }

    //volatile 的原子性
    //1.synchronized解决原子性，不要用“杀鸡用牛刀”
    //为什么不够20000
    public synchronized void addPlus(){
        number++;
    }
    AtomicInteger atomicInteger=new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement(); //每次加一
    }
}

/**
 * 1.验证volatile的可见性
 *  1.1:假如 int number=0，number没有添加volatile关键字
    1.2:添加volatile，可以保证可见性
   2.验证不保证”原子性“
        不可以被分割，需要整体完整的，要么同时成功，同时失败
    2.1:如何解决原子性
        1.synchronized解决原子性
        2.直接使用JUC的  AtomicInteger.带原子Atomic,底层用的是CAS
 */
public class MyVolatileTest {

    public static void main(String[] args) throws Exception {
            Mydate mydate=new Mydate();

        //20个线程每个线程对他加1000次
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    mydate.addPlus();
                    System.out.println(mydate.number);
                    mydate.addAtomic();
                }
            },String.valueOf(i)).start();
        }

        //等待线程全部计算完，有2个线程在运行
        while (Thread.activeCount()>2){
            Thread.yield(); //退一步再执行
        }

        System.out.println(Thread.currentThread().getName() + "\t number value \t" + mydate.number);
        System.out.println(Thread.currentThread().getName() + "\t number value \t" + mydate.atomicInteger);


    }

     public void volatileTest(){
         Mydate mydate=new Mydate();
         new Thread(()->{
             System.out.println(Thread.currentThread().getName()+"\t aa \t"+mydate.number);
             try {
                 Thread.sleep(3000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             mydate.add();
             System.out.println(Thread.currentThread().getName()+"\t update \t"+mydate.number);
         },"aa").start();

         //第二个线程
         while (mydate.number==0){

         }
         System.out.println(Thread.currentThread().getName() + "\t is over"+mydate.number);

     }
}
