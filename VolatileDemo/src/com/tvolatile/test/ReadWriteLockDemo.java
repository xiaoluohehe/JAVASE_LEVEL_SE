package com.tvolatile.test;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache
{
    //缓存的东西要保证可见性 volatile
    private volatile Map<String,Object> map=new HashMap<>();
//    private Lock lock =
    private ReentrantReadWriteLock rwLock=new ReentrantReadWriteLock();



    public void put(String key, Object value) throws InterruptedException {

        rwLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入"+value);
            Thread.sleep(900);
            map.put(key,value);

            System.out.println(Thread.currentThread().getName() + "\t 写入完成");

        }finally {
            rwLock.writeLock().unlock();
        }

           }
    //读
    public void get(String key) throws InterruptedException {

        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取");

            Thread.sleep(900);
            Object o = map.get(key);

            System.out.println(Thread.currentThread().getName() + "\t 读取完成："+ o);

        }finally {
            rwLock.readLock().unlock();
        }
          }

          //模拟清除缓存
    public void clearMap(){

        map.clear();

    }

}

/**
 * 多个线程同时读一个资源类没有任何问题，为了满足并发量，读取共享资源应该可以同时进行
 *      读-读能共享
 *      读-写不能共享
 *      写-写不能共享
 *
 *      写操作：原子+独占锁，整个过程是一个完整的统一体，中间不许被分割，被打断
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {

        MyCache myCache=new MyCache();

        for (int i = 0; i < 5; i++) {
            final int tempInt= i;
            new Thread(()->{
                try {
                    myCache.put(tempInt+"",tempInt+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int tempInt= i;
            new Thread(()->{
                try {
                    myCache.get(tempInt+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
