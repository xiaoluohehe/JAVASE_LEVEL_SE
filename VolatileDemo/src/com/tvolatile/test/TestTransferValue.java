package com.tvolatile.test;

public class TestTransferValue {

    public void changeValue1(int age){
        age=20;
    }

    public void changeValue2(Person person){
        person.setPersonName("xx");
    }

    public void changeValue3(String str){
        str="xxx";
    }

    public static void main(String[] args) {
        TestTransferValue test=new TestTransferValue();
        //栈管运行，堆管存储
        int age=20;
        test.changeValue1(age);
        System.out.println("age---------" + age);  //age属于main方法的

        Person person=new Person("abc");
        test.changeValue2(person);
        System.out.println("person-----" + person.getPersonName());

        String string="abc";
        test.changeValue3(string);
        System.out.println("String--------" + string);

    }
}
