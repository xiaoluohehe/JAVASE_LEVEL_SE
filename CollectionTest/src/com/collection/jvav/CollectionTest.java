package com.collection.jvav;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionTest {

    @Test
    public void test01(){
        Collection collection=new ArrayList();
        collection.add("aa");
        collection.add(new Persion("a",15));
        boolean aa = collection.contains("aa");

        System.out.println(aa);
    }
}
