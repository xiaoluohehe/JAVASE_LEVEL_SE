package com.tvolatile.test;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA带时间戳的解决方案   AtomicStampedReference
 */
public class ABATest {

    //这个不支持时间戳的原子引用，是普通的原子引用
    static AtomicReference<Integer> atomicReference=new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference=new AtomicStampedReference<>(100,1);//初始化的值100，时间戳为1
    public static void main(String[] args) throws  Exception {

        System.out.println("======ABA问题的产生=====");
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"t1").start();

        new Thread(()->{
            try {
                Thread.sleep(1000);  //保证完成一次ABA操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get());
        },"t2").start();

        try {
            Thread.sleep(1000);  //保证完成一次ABA操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("——------ABA问题的解决------------");

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();//初始化版本号1
            System.out.println(Thread.currentThread().getName() + "\t  one version：" + atomicStampedReference.getStamp());
            try {
                Thread.sleep(1000);  //保证完成一次ABA操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t  2 版本号：" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t  3 版本号：" + atomicStampedReference.getStamp());
        },"t3").start();

        new Thread(()->{

            int stamp = atomicStampedReference.getStamp();//初始化版本号1
            System.out.println(Thread.currentThread().getName() + "\t  1 版本号：" + atomicStampedReference.getStamp());
            try {
                //暂停3秒钟t4线程，保证上面的3线程完成一次ABA操作(给足够时间让t3完成操作)
                Thread.sleep(4000);  //保证完成一次ABA操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicStampedReference.compareAndSet(100,2019,stamp,stamp+1));
            System.out.println(Thread.currentThread().getName()+"最终版本号："+atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName()+"\t 当前更新值："+atomicStampedReference.getReference());

        },"t4").start();
    }
}
