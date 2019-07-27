package com.tvolatile.test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 手写一个自旋锁
 *      自旋锁好处：循环比较获取直到成功为止，没有类似wait的阻塞
 *      好处没有堵塞，坏处就是长时间的堵塞影响系统的性能
 *  通过CAS完成自旋锁
 */
public class SpinLockDemo {
    //原子引用,我们要自己实现，以前装的是User,现在装的是线程
    //原子引用线程
    AtomicReference<Thread> atomicReference=new AtomicReference<>();

    //我们用反复的循环比较，没有上锁，和lock
    public void myLock()
    {
        Thread thread=Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in O(∩_∩)O哈哈~");

        //期望和真实都为null,我就把自己放进去
        while (!atomicReference.compareAndSet(null,thread))
        {

        }
    }

    public void myUnlock(){//解锁

        Thread thread=Thread.currentThread();
        atomicReference.compareAndSet(thread,null);//用完了就变为null，出去就变为null，给下一个人用
        System.out.println(Thread.currentThread().getName() + "\t invoke myUnlock");


    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo=new SpinLockDemo();

        new Thread(()->{
           spinLockDemo.myLock();  //占用这把锁
            try {
                Thread.sleep(5000); //要保证aa线程先启动,持有这把锁5秒钟
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        },"aa").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            spinLockDemo.myLock();  //占用这把锁
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        },"bb").start();

    }
}
