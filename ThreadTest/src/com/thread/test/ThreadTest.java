package com.thread.test;

public class ThreadTest {

    public static void main(String[] args) {
        MyThread myThread=new MyThread();
        myThread.run();
    }
}

class MyThread extends Thread{

    @Override
    public void run() {
        for (int i=0 ;i<100;i++){
            System.out.println(i + Thread.currentThread().getName());
        }
    }
}
