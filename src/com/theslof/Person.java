package com.theslof;

public class Person {
    private String name;
    private int age;

    public Person(){

    }

    public Person(String name, int age){
        setName(name);
        setAge(age);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length() == 0)
            return;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age < 0)
            return;
        this.age = age;
    }

    public String toString(){
        return getName() + ", " + getAge();
    }
}
