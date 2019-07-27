package com.tvolatile.test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合不安全问题
 *  ArrayList
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {



//        SetNotSafe.invoke();
        Set<String> set=new CopyOnWriteArraySet<>();  //--->底层是ArrayList的copy
//        Map<String,String> map=new HashMap<>();
        Map<String,String> map=new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,7));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
        //面试题：hashset底层是什么？(就是hashmap)
        new HashSet<>().add("a");//初始化是16，加载因子是0.76,值是key，value是present的常量，不管value，value是恒定



    }

    public void listNotSafe(){
        //****举例一个ArrayList线程不安全的例子,  "因为是单线程环境下,add1根本就没有加锁"
//        ArrayList<String> asList = new ArrayList<String>();
//       方法 1. List<String> asList = new Vector<>(); //加锁的数据性可以保证，但是并发性极具下降，为了杜绝这种情况，就开发ArrayList（性能，线程不安全）
        //1.要加锁(add)，还用ArrayList,就可以用Vector
        //加锁的数据性可以保证，但是并发性极具下降，为了杜绝这种情况，就开发ArrayList（性能，线程不安全）
//         那我们用什么呢？
        //方法 2.
//        List<String> asList = Collections.synchronizedList(new ArrayList<>()); //构建一个线程安全的ArrayList
        //方法3 CopyOnWriteArrayList
        List<String> asList=new CopyOnWriteArrayList<>();

        //List<String> asList = Arrays.asList("aaa", "cc");
//        asList.forEach(System.out::println);
//        Stream<String> stream = asList.stream();
//        stream.forEach(System.out::println);
//        Exception in thread "17" java.util.ConcurrentModificationException  //juc并发异常多线程异常
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                asList.add(UUID.randomUUID().toString().substring(0,7));
                System.out.println(asList);
            },String.valueOf(i)).start();
        }
        /**
         * 1.故障现象，
         *  java.util.ConcurrentModificationException
         * 2.导致原因
         *      并发争抢修改导致的，参考我们的花名册，
         *         一个人正在写入，另外一个人过来抢夺，导致数据不一致异常，并发修改异常
         * 3.解决方案
         * //       方法 1. List<String> asList = new Vector<>(); //加锁的数据性可以保证，但是并发性极具下降，为了杜绝这种情况，就开发ArrayList（性能，线程不安全）
         *          //方法 2.
         *          //List<String> asList = Collections.synchronizedList(new ArrayList<>()); //构建一个线程安全的ArrayList
         *         //方法3 CopyOnWriteArrayList
         *         List<String> asList=new CopyOnWriteArrayList<>();
         *
         *
         * 4.杜绝（优化建议,同样的错误不犯2次）
         */
    }

    private static class SetNotSafe {
        private static void invoke() {
            //hashset线程不安全
//        1.Set<String> set=new HashSet<>();
//        2.Set<String> set=Collections.synchronizedSet(new HashSet<>());
            Set<String> set=new CopyOnWriteArraySet<>();  //--->底层是ArrayList的copy
            for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,7));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
        //面试题：hashset底层是什么？(就是hashmap)
            new HashSet<>().add("a");//初始化是16，加载因子是0.76,值是key，value是present的常量，不管value，value是恒定
    }
    }
}
