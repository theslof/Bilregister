package com.theslof;

import java.util.ArrayList;

public class Person {
    private String name;
    private int age;
    private ArrayList<Car> cars = new ArrayList<>();

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

    public ArrayList<Car> getCars() {
        return cars;
    }

    public String toString(){
        return getName() + ", " + getAge();
    }

    public void removeCar(Car c) {
        cars.remove(c);
    }

    public void addCar(Car c) {
        cars.add(c);
    }
}
