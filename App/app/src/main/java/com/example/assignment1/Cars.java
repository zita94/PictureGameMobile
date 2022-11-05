package com.example.assignment1;

import android.os.Environment;

import java.util.ArrayList;
import java.util.Random;

public class Cars {

    private final ArrayList<Car> cars;
    //list of car brands
    public static final String[] BRANDS = new String[] {"Audi", "Bentley", "BMW", "Fiat", "Ford",
            "Honda", "Hyundai", "Jaguar", "Mercedes-Benz", "Toyota"};

    public Cars(){
        cars = new ArrayList<>();
        populateCarList(cars);
    }

    //populates the cars list with the 100 cars (10 of each brand)
    public static void populateCarList(ArrayList<Car> list) {
        for (String brand : BRANDS){
            for(int i = 1; i <= 10; i++){
                String filePath = Environment.getExternalStorageDirectory().getPath()+ "/images/" + brand + "/" + brand + " (" + i + ").jpg";
                list.add(new Car(brand, filePath));
            }
        }
    }

    //gets a random car from the cars list, removing it from the list then automatically repopulates the list of cars when it's empty
    public Car getRandomCar(){
        if (cars.size() == 0) {
            populateCarList(cars);
        }

        Random rand = new Random();
        int pos = rand.nextInt(cars.size());
        return cars.remove(pos);
    }
}
