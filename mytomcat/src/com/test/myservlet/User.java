package com.test.myservlet;

public class User {
    private String name;
    private int age;

    public User(String a, int i) {
        this.name=a;
        this.age=i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
