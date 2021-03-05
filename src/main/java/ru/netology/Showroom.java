package ru.netology;

import java.util.ArrayList;
import java.util.List;

public class Showroom {
    final Manufacturer seller = new Manufacturer(this);
    List<Car> cars = new ArrayList<>();

    public Car sellCar() {
        return seller.sellCar();
    }

    public void createCar() {
        seller.createCar();
    }

    List<Car> getCars() {
        return cars;
    }

    public int size() {
        return cars.size();
    }
}
