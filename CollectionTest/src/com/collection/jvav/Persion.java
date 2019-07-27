package com.collection.jvav;

public class Persion {

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Persion(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Persion(String name) {
        this.name = name;
    }

    public Persion() {
    }

    @Override
    public String toString() {
        return "Persion{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persion persion = (Persion) o;

        if (name != null ? !name.equals(persion.name) : persion.name != null) return false;
        return age != null ? age.equals(persion.age) : persion.age == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }
}
