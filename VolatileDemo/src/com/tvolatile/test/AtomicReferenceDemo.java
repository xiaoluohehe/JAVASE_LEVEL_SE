package com.tvolatile.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@Getter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
class User{
    String userName;
    int age;

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}

public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User user=new User("aa",00);
        User user1=new User();

        AtomicReference<User> atomicReference=new AtomicReference<>();

        atomicReference.set(user);
        System.out.println(atomicReference.compareAndSet(user, user1) + "\t " + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(user, user1) + "\t " + atomicReference.get().toString());

    }
}
