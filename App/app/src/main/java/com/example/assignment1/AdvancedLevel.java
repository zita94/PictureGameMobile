package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdvancedLevel extends AppCompatActivity {
    private ImageView ivCar0;
    private ImageView ivCar1;
    private ImageView ivCar2;
    private EditText etCar0;
    private EditText etCar1;
    private EditText etCar2;
    private TextView tvResult;
    private TextView tvTries;
    private TextView tvHint;
    private SortedCars sortedCars;
    private ArrayList<Car> uniqueCar;
    private Bundle savedState;
    private int tries = 3;
    private String strCorrect0;
    private String strCorrect1;
    private String strCorrect2;
    private String strTries = "Tries left: " + tries;
    private Button btnSubmit;
    private boolean nextClicked = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level);
        getSupportActionBar().setTitle("");
        savedState = savedInstanceState;
        String strHint = "Write in text box next ot each picture the brand of the car and press submit. You have 3 tries in total";

        sortedCars = new SortedCars();
        uniqueCar = new ArrayList<>();
        ivCar0 = findViewById(R.id.imageView_AdCar);
        ivCar1 = findViewById(R.id.imageView_AdCar2);
        ivCar2 = findViewById(R.id.imageView_AdCar3);
        etCar0 = findViewById(R.id.text_Car);
        etCar1 = findViewById(R.id.text_Car2);
        etCar2 = findViewById(R.id.text_Car3);
        tvResult = findViewById(R.id.textView_AdResult);
        tvTries = findViewById(R.id.textView_AdTries);
        tvHint = findViewById(R.id.textView_AdHint);
        tvHint.setText(strHint);
        btnSubmit = findViewById(R.id.button_AdSubmit);

        if (savedInstanceState == null) {
            ShowCars();
        }

    }

    //sets up the picture of the 3 cars
    private void ShowCars() {
        btnSubmit.setText(getText(R.string.submit));
        tvTries.setText(strTries);
        etCar0.setEnabled(true);
        etCar1.setEnabled(true);
        etCar2.setEnabled(true);

        uniqueCar = sortedCars.getUniqueBrand(3);

        ivCar0.setImageBitmap((BitmapFactory.decodeFile(uniqueCar.get(0).getFilePath())));
        ivCar0.setTag(uniqueCar.get(0).getBrand());
        ivCar1.setImageBitmap((BitmapFactory.decodeFile(uniqueCar.get(1).getFilePath())));
        ivCar1.setTag(uniqueCar.get(1).getBrand());
        ivCar2.setImageBitmap((BitmapFactory.decodeFile(uniqueCar.get(2).getFilePath())));
        ivCar2.setTag(uniqueCar.get(2).getBrand());

        strCorrect0 = uniqueCar.get(0).getBrand();
        strCorrect1 = uniqueCar.get(1).getBrand();
        strCorrect2 = uniqueCar.get(2).getBrand();
    }

    //on click method for the submit button
    public void submitOnClick(View view) {
        if(!nextClicked) {
            while (tries > 0) {
                boolean first = checkAnswer(etCar0, strCorrect0);

            boolean second = checkAnswer(etCar1, strCorrect1);
            boolean third = checkAnswer(etCar2, strCorrect2);
            if (first && second && third) {
                btnSubmit.setText(getText(R.string.next));
                etCar0.setText("");
                etCar0.setBackgroundColor(Color.TRANSPARENT);
                etCar1.setText("");
                etCar1.setBackgroundColor(Color.TRANSPARENT);
                etCar2.setText("");
                etCar2.setBackgroundColor(Color.TRANSPARENT);
                ShowCars();
                tries = 3;
                strTries = "Tries left: " + tries;
                tvTries.setText(strTries);
                tvResult.setText(getString(R.string.correct));
                tvResult.setTextColor(Color.GREEN);
            } else {
                tries--;
                strTries = "Tries left: " + tries;
                tvTries.setText(strTries);
            }
        }
        }
    }

    public boolean checkAnswer(EditText input, String answer) {
        if (input.getText().toString().equalsIgnoreCase(answer)) {
            input.setBackgroundColor(getColor(R.color.green));
            input.setEnabled(false);
            return true;
        } else {
            input.setBackgroundColor(getColor(R.color.red));
            input.setEnabled(true);
            return false;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        try {
            outState.putString("filePath0", uniqueCar.get(0).getFilePath());
            outState.putString("brand0", uniqueCar.get(0).getBrand());
            outState.putString("filePath1", uniqueCar.get(1).getFilePath());
            outState.putString("brand1", uniqueCar.get(1).getBrand());
            outState.putString("filePath2", uniqueCar.get(2).getFilePath());
            outState.putString("brand2", uniqueCar.get(2).getBrand());
        } catch (Exception e) {
            outState.putAll(savedState);
            Log.e("Error: ", e.toString());
        }

        outState.putString("Correct0", strCorrect0);
        outState.putString("Correct1", strCorrect1);
        outState.putString("Correct2", strCorrect2);
        outState.putString("result", tvResult.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstance) {
        super.onRestoreInstanceState(savedInstance);
        ivCar0.setImageBitmap(BitmapFactory.decodeFile(savedInstance.getString("filePath0")));
        ivCar0.setTag(savedInstance.getString("brand0"));
        ivCar1.setImageBitmap(BitmapFactory.decodeFile(savedInstance.getString("filePath1")));
        ivCar1.setTag(savedInstance.getString("brand1"));
        ivCar2.setImageBitmap(BitmapFactory.decodeFile(savedInstance.getString("filePath2")));
        ivCar2.setTag(savedInstance.getString("brand2"));

        strCorrect0 = savedInstance.getString("tvCorrectBrand");
        tvResult.setText(savedInstance.getString("result"));

        strCorrect0 = savedInstance.getString("Correct0");
        strCorrect1 = savedInstance.getString("Correct1");
        strCorrect2 = savedInstance.getString("Correct2");

        tvResult.setText(savedInstance.get("result").toString());
        if (tvResult.getText().equals(getString(R.string.correct))) {
            tvResult.setTextColor(Color.GREEN);
        } else {
            tvResult.setText("");
        }
    }
}