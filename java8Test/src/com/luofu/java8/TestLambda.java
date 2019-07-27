package com.luofu.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**1.
 * ()->system.out.println()
 * 2.
 * (x)-> system.out.println()
 * 3.
 *  (x)-> {
 *  system.out.println();
 *      return x
 *  }
 *functioninterface  函数式接口
 */

public class TestLambda {

    @Test
    public void test01(){
        Runnable runnable=()-> System.out.println("qq");
        runnable.run();
    }

    //带参，无返回值
    @Test
    public void test02(){
        Consumer<String> consumer= x -> System.out.println(x);
        consumer.accept("hello word");
    }

    //supplier t get
    @Test
    public void test03(){
        List<Integer> number = getNumber(20, () -> (int) (Math.random()*100));
        for(Integer nu :number){
            System.out.println(nu);
        }
    }

    public List<Integer> getNumber(int num , Supplier<Integer> supplier){
        List<Integer> list=new ArrayList<>();
        for (int i=0;i<num;i++){
            Integer integer = supplier.get();
            list.add(integer);
        }
        return list;
    }

    //function
    @Test
    public void test04(){
        String newString=strHeadle("\t\t\t aaa",(str)->str.trim());
        System.out.println(newString);
    }

    public String strHeadle(String str, Function<String,String> fu){
      return fu.apply(str);
    }

}
