package com.example.assignment1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class IdentifyBrand extends AppCompatActivity {
    private Spinner spBrandsDropDown;
    private Cars cars;
    private Car car;
    private ImageView ivCar;
    private TextView tvAnswer;
    private TextView tvCorrectA;
    private Button btnSubmit;
    private String strCorrectAnswer;
    private Bundle state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_brand);
        getSupportActionBar().setTitle("");
        state = savedInstanceState;

        btnSubmit = findViewById(R.id.button_SubmitBrand);
        ivCar = findViewById(R.id.imageView_Car);
        tvAnswer = findViewById(R.id.textView_BrandAnswer);
        tvCorrectA = findViewById(R.id.textView_CorrectAnswer);
        spBrandsDropDown = findViewById(R.id.spinner_Brands);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, Cars.BRANDS);
        spBrandsDropDown.setAdapter(arrayAdapter);

        cars = new Cars();
        if(state == null) {
            ShowNewCar();
        }
    }

    //shows new image of a car
    private void ShowNewCar() {
        car = cars.getRandomCar();
        strCorrectAnswer = car.getBrand();
        Bitmap bitmap = BitmapFactory.decodeFile(car.getFilePath());
        ivCar.setImageBitmap(bitmap);
    }

    //on click instance for submit button
    public void SubmitOnClick(View view){
        if(btnSubmit.getText().toString().equals(getString(R.string.submit))){
            checkAnswer();
            tvCorrectA.setText(strCorrectAnswer);
            btnSubmit.setText(getString(R.string.next));

        } else if(btnSubmit.getText().toString().equals(getString(R.string.next))){
            ShowNewCar();
            btnSubmit.setText(R.string.submit);
            tvAnswer.setText("");
            tvCorrectA.setText("");
        }
    }

    //checks if given answer is correct
    private void checkAnswer() {
        if(car != null) {
            strCorrectAnswer = car.getBrand();
        }

        String strSelectedBrand = spBrandsDropDown.getSelectedItem().toString();

        if(strSelectedBrand.equals(strCorrectAnswer)){
            tvAnswer.setText(getString(R.string.correct));
            tvAnswer.setTextColor(Color.GREEN);
        }else{
            tvAnswer.setText(getString(R.string.wrong));
            tvAnswer.setTextColor(Color.RED);
        }
    }

    //saves state
//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState){
//        super.onSaveInstanceState(outState);
//
//        try{
//            if(btnSubmit.getText().toString().equals(getString(R.string.next))){
//                outState.putString("result", tvAnswer.getText().toString());
//            }
//            outState.putString("strCorrectAnswer", strCorrectAnswer);
//            outState.putString("filePath", car.getFilePath());
//            outState.putString("brand", car.getBrand());
//            outState.putString("button", btnSubmit.getText().toString());
//        } catch (Exception e) {
//            //if car doesn't exist, exception is thrown, restores state
//            outState.putAll(state);
//            outState.putString("strCorrectAnswer", strCorrectAnswer);
//        }
//    }
//
//    //restores saved state
//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedState){
//        super.onRestoreInstanceState(savedState);
//
//        Bitmap bitmap = BitmapFactory.decodeFile(savedState.getString("filepath"));
//        ivCar.setImageBitmap(bitmap);
//        btnSubmit.setText(savedState.getString("button"));
//        strCorrectAnswer = savedState.getString("strCorrectAnswer");
//
//        if(state.containsKey("result")){
//            String strResult = savedState.getString("result");
//            tvCorrectA.setText(strResult);
//
//            if(strResult.equals("Correct")){
//                tvCorrectA.setTextColor(Color.GREEN);
//            }else{
//                tvCorrectA.setTextColor(Color.RED);
//            }
//
//            tvAnswer.setText(strCorrectAnswer);
//            btnSubmit.setText("Next");
//        }
//    }
}