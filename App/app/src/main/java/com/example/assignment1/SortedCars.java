package com.example.assignment1;

import android.os.Environment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class SortedCars {
    private ArrayList<ArrayList<Car>> carsList;
    private ArrayList<Car> singleBrandCarList;
    private boolean bPopulated = false;

    public SortedCars(){
        carsList = new ArrayList<>();
        singleBrandCarList = new ArrayList<>();
        populateCarsList();
    }

    private void populateCarsList() {
        for(String brand : Cars.BRANDS){
            populateBrand(brand);
        }
    }

    private void populateBrand(String brand) {
        ArrayList<Car> temp = new ArrayList<>();
        for(int i = 1; i <= 10; i++){
            String filePath = Environment.getExternalStorageDirectory().getPath()+ "/images/" + brand + "/" + brand + " (" + i + ").jpg";
            temp.add(new Car(brand, filePath));
        }
        carsList.add(temp);
    }

    //
    public ArrayList<Car> getUniqueBrand(int count) {
        ArrayList<Integer> uniqueCount = getRandomNumbers();
        ArrayList<Car> uniqueCars = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < count; i++){
            int brand = uniqueCount.remove(0);
            int leftoverCars = carsList.get(brand).size();
            boolean brandNeedsRepopulated = false;
            String strBrandName = carsList.get(brand).get(0).getBrand();

            //if all cars for 1 brand are used, repopulate brand
            if(leftoverCars == 1){
                brandNeedsRepopulated = true;
            }

            int pos = rand.nextInt(leftoverCars);
            Car randomCar = carsList.get(brand).remove(pos);
            uniqueCars.add(randomCar);

            if(brandNeedsRepopulated){
                removeEmptyBrand();
                Cars.populateCarList(singleBrandCarList);
            }
        }
        return uniqueCars;
    }

    //populate list of cars for 1 brand
    public void populateSingleBrand(String brand){
        boolean brandExists = false;
        for(String carBrand : Cars.BRANDS){
            if(carBrand.equalsIgnoreCase(brand)){
                brandExists = true;
                break;
            }
        }

        if(!brandExists){
            return;
        }
        singleBrandCarList = new ArrayList<>();
        Cars.populateCarList(singleBrandCarList);
    }

    public Car getCarByBrand(String brand){
        if(singleBrandCarList == null){
            return null;
        }
        if(singleBrandCarList.size() == 0) {
            populateSingleBrand(brand);
        }
        if(singleBrandCarList.get(0).getBrand().equalsIgnoreCase(brand)){
            Random rand = new Random();
            int pos = rand.nextInt(singleBrandCarList.size());
            return singleBrandCarList.remove(pos);
        }
        return null;
    }

    //remove brand list if all cars are used    }
    private void removeEmptyBrand(){
        for(int i = 0; i < carsList.size(); i++){
            if(carsList.get(i).size() == 0){
                carsList.remove(i);
            }
        }
    }
    private ArrayList<Integer> getRandomNumbers() {
        ArrayList<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < Cars.BRANDS.length; i++){
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }
}
