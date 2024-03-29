package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class IdentifyCar extends AppCompatActivity {
    private TextView tvCarBrand;
    private TextView tvResult;
    private ImageView ivCar1;
    private ImageView ivCar2;
    private ImageView ivCar3;
    private SortedCars sortedCars;
    private ArrayList<Car> uniqueCar;
    private String strCorrectBrand;
    private Bundle savedState;
    private boolean imageClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_car);
        getSupportActionBar().setTitle("");

        sortedCars = new SortedCars();
        uniqueCar = new ArrayList<>();
        tvCarBrand = findViewById(R.id.textView_IDCarBrand);
        tvResult = findViewById(R.id.textView_IDCarResult);
        ivCar1 = findViewById(R.id.imageView_IDCar);
        ivCar2 = findViewById(R.id.imageView_IDCar2);
        ivCar3 = findViewById(R.id.imageView_IDCar3);

        if (savedInstanceState == null) {
            ShowCars();
        }
    }

    //sets up the picture of the 3 cars, chooses which one is to be selected at random
    private void ShowCars() {
        uniqueCar = sortedCars.getUniqueBrand(3);
        ivCar1.setImageBitmap((BitmapFactory.decodeFile(uniqueCar.get(0).getFilePath())));
        ivCar1.setTag(uniqueCar.get(0).getBrand());
        ivCar2.setImageBitmap((BitmapFactory.decodeFile(uniqueCar.get(1).getFilePath())));
        ivCar2.setTag(uniqueCar.get(1).getBrand());
        ivCar3.setImageBitmap((BitmapFactory.decodeFile(uniqueCar.get(2).getFilePath())));
        ivCar3.setTag(uniqueCar.get(2).getBrand());

        Random random = new Random();
        int position = random.nextInt(uniqueCar.size());
        strCorrectBrand = uniqueCar.get(position).getBrand();
        tvCarBrand.setText(strCorrectBrand);
    }

    public void imageOnClick(View view) {
        if(!imageClicked) {
            imageClicked = true;
            ImageView clickedImage = findViewById(view.getId());
            String strSelectedBrand = clickedImage.getTag().toString();
            if (strSelectedBrand.equalsIgnoreCase(strCorrectBrand)) {
                tvResult.setText(getString(R.string.correct));
                tvResult.setTextColor(Color.GREEN);
            } else {
                tvResult.setText(getString(R.string.wrong));
                tvResult.setTextColor(Color.RED);
            }
        }
    }

    public void nextOnClick(View view) {
        if (imageClicked) {
            tvResult.setText("");
            ShowCars();
            imageClicked = false;
        } else {
            Toast.makeText(this, "Please click an image", Toast.LENGTH_SHORT).show();
        }
    }
}