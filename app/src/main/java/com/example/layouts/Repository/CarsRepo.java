package com.example.layouts.Repository;

import com.example.layouts.Models.Car;
import com.example.layouts.Models.Card;

import java.util.ArrayList;
import java.util.Date;

public class CarsRepo {
    public ArrayList<Car> cars = new ArrayList<Car>();

    public void AddNewCar(String number_car, int fuel, int price, Date start, Date end) {
        Car car = new Car(number_car, fuel, price, start, end);
        cars.add(car);
    }
    public ArrayList<Car> getCars() {
        return cars;
    }

    public Car getCarByNumber(String number_car){
        Car result = null;
        for (int i = 0; i < cars.size(); i++) {
            if (number_car.equals(cars.get(i).getNumber())) {
                result = cars.get(i);
            }
        }
        return result;
    }
}
