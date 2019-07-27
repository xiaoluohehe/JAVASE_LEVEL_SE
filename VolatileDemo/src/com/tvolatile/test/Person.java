package com.tvolatile.test;


public class Person {

    private Integer id;
    private String personName;

    public Person(Integer id, String personName) {
        this.id = id;
        this.personName = personName;
    }

    public Person(String s) {
        this.personName=s;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", personName='" + personName + '\'' +
                '}';
    }


}
