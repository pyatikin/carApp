package com.example.layouts.Models;

import com.yandex.mapkit.geometry.Point;

import java.util.Date;

public class Car {
    private String number;
    private int fuel;
    private int price;
    Point arrival;
    Point departure;
    Date start;
    Date end;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Point getArrival() {
        return arrival;
    }

    public void setArrival(Point arrival) {
        this.arrival = arrival;
    }

    public Point getDeparture() {
        return departure;
    }

    public void setDeparture(Point departure) {
        this.departure = departure;
    }

    public Car(String number, int fuel, int price, Date start, Date end) {
        this.number = number;
        this.fuel = fuel;
        this.price = price;
        this.start = start;
        this.end = end;
    }

    public int getFuel() {
        return fuel;
    }

    public int getPrice() {
        return price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
