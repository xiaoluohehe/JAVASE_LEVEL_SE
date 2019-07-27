package com.tvolatile.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phon implements Runnable{
    public synchronized void sendSMS() throws Exception{

        System.out.println(Thread.currentThread().getId() + "\t" + "invoked send");

        sendEmile(); //是同一把锁还是两吧锁
    }

    public synchronized void sendEmile() throws Exception{

        System.out.println(Thread.currentThread().getId() + "\t" + "invoked sendEmile");
    }
//==============================================================

    Lock lock=new ReentrantLock();//填的是非公平锁

    @Override
    public void run() {
        get();
    }

    public void get()
    {
        lock.lock();
        lock.lock();
         try {
             System.out.println(Thread.currentThread().getId() + "\t" + "invoked get()");
            set();
         }finally {
             lock.unlock();
             lock.unlock();
         }
    }
    public void set()
    {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "\t" + "invoked set()");
        }finally {
            lock.unlock();
        }
    }
}

/***
 * 可重用锁
 *
 * 线程可以进入任何一个他已经拥有同步着的代码块
 *case:one  synchronized就是典型的重用锁
 * 11	invoked send   线程在外层获取锁的时候，在进入内层方法会自动获取锁
    * 11	invoked sendEmile
 * 12	invoked send
 * 12	invoked sendEmile
 *
 * case two ReentrantLock
 *  有lock.lock()和unlock几把都可以，要“成对”存在，不会报错，不然不会停止
 *
 *
 */
public class ReenterLockDemo {

    public static void main(String[] args) {

        Phon phon=new Phon();//线程操作资源类

        new Thread(()->{
            try {
                phon.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                phon.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();


       Thread t3=new Thread(phon);
       Thread t4=new Thread(phon);
       t3.start();
       t4.start();

    }

}
