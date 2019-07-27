package com.tvolatile.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  CAS 是什么？ -->compareAndSet
 *      比较交换
 */
public class CASTest {

    public static void main(String[] args) {
        //TODO sdfds
        AtomicInteger atomicInteger=new AtomicInteger(5); //主存(快照)
        System.out.println(atomicInteger.compareAndSet(65, 1) + "\t current \t" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2) + "\t current \t" + atomicInteger.get());

        atomicInteger.getAndIncrement();
    }
}
