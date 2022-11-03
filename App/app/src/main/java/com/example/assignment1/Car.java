package com.example.assignment1;

public class Car {

    private final String brand;
    private final String filePath;

    public Car(String brand, String filePath){
        this.brand = brand;
        this.filePath = filePath;
    }

    public String getBrand(){return this.brand;}

    public String getFilePath(){return this.filePath;}
}
