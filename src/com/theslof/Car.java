package com.theslof;

import java.util.Arrays;

public class Car {
    private double weight;
    private int seats;
    private double fuelAmount;
    private double mpg;
    private boolean[] seatOccupied;
    private String color;
    private Person owner;

    public Car(){
        this(1000,5,50,1);
    }

    public Car(String color, Person owner){
        setColor(color);
        setOwner(owner);
    }

    public Car(double weight, int seats, double fuelAmount, double mpg) {
        this.seatOccupied = new boolean[seats];
        this.setWeight(weight);
        this.setSeats(seats);
        this.setFuelAmount(fuelAmount);
        this.setMpg(mpg);
    }

    public double getWeight() {
        return weight;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

    public double getMpg() {
        return mpg;
    }

    public int getSeats() {
        return seats;
    }

    public void setFuelAmount(double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public void setMpg(double mpg) {
        this.mpg = mpg;
    }

    public void setSeats(int seats) {
        if (seats <= 0 || seats == this.seats)
            return;
        seatOccupied = Arrays.copyOf(seatOccupied, seats);
        this.seats = seats;
    }

    public void setWeight(double weight) {
        if (weight > 0)
            this.weight = weight;
    }

    public String toString() {
        return "{Owner: " + owner.getName() + ", Color: " + color + "}";
    }

    public double getRange() {
        return fuelAmount / mpg;
    }

    public void setSeatOccupied(int seat, boolean occupied) {
        if(seat >= 0 && seat < seats)
        seatOccupied[seat] = occupied;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
