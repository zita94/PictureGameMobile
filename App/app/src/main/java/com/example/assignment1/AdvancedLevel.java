package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
    private TextView tvCar0;
    private TextView tvCar1;
    private TextView tvCar2;
    private TextView tvResult;
    private TextView tvTries;
    private TextView tvHint;
    private TextView tvScore;
    private SortedCars sortedCars;
    private ArrayList<Car> uniqueCar;
    private int tries = 3;
    private int score = 0;
    private String strCorrect0;
    private String strCorrect1;
    private String strCorrect2;
    private String strTries = "Tries left: " + tries;
    private String strScore = "Score: " + score;
    private Button btnSubmit;
    private boolean first = false;
    private boolean second = false;
    private boolean third = false;
    private Drawable originalBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level);
        getSupportActionBar().setTitle("");
        String strHint = "Write in the text box next to each picture the brand of the car and press submit. You have 3 tries in total";

        sortedCars = new SortedCars();
        uniqueCar = new ArrayList<>();
        ivCar0 = findViewById(R.id.imageView_AdCar);
        ivCar1 = findViewById(R.id.imageView_AdCar2);
        ivCar2 = findViewById(R.id.imageView_AdCar3);
        etCar0 = findViewById(R.id.text_Car);
        etCar1 = findViewById(R.id.text_Car2);
        etCar2 = findViewById(R.id.text_Car3);
        tvCar0 = findViewById(R.id.textView_AdCar0);
        tvCar0.setTextColor(Color.YELLOW);
        tvCar1 = findViewById(R.id.textView_AdCar1);
        tvCar1.setTextColor(Color.YELLOW);
        tvCar2 = findViewById(R.id.textView_AdCar2);
        tvCar2.setTextColor(Color.YELLOW);
        tvScore = findViewById(R.id.textView_AdScore);
        tvResult = findViewById(R.id.textView_AdResult);
        tvTries = findViewById(R.id.textView_AdTries);
        tvHint = findViewById(R.id.textView_AdHint);
        tvHint.setText(strHint);
        tvScore = findViewById(R.id.textView_AdScore);
        tvScore.setText(strScore);
        btnSubmit = findViewById(R.id.button_AdSubmit);
        originalBackground = etCar0.getBackground();

        if (savedInstanceState == null) {
            ShowCars();
        }

    }

    //sets up the picture of the 3 cars
    private void ShowCars() {
        btnSubmit.setText(getText(R.string.submit));
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
        if (btnSubmit.getText().equals(getString(R.string.submit))) {
            if (tries > 0) {
                first = checkAnswer(etCar0, strCorrect0);
                second = checkAnswer(etCar1, strCorrect1);
                third = checkAnswer(etCar2, strCorrect2);
                if (first && second && third) {
                    btnSubmit.setText(getString(R.string.next));
                    tvResult.setText(getString(R.string.correct));
                    tvResult.setTextColor(Color.GREEN);
                    if(first) score++;
                    if(second) score++;
                    if(third) score++;
                } else {
                    tries--;
                    strTries = "Tries left: " + tries;
                    tvTries.setText(strTries);
                }
            }
            if (tries == 0) {
                if (!first) {
                    tvCar0.setText(strCorrect0);
                    tvCar0.setTextColor(Color.YELLOW);
                }
                if (!second) {
                    tvCar1.setText(strCorrect1);
                    tvCar1.setTextColor(Color.YELLOW);
                }
                if (!third) {
                    tvCar2.setText(strCorrect2);
                    tvCar2.setTextColor(Color.YELLOW);
                }
                if(first) score++;
                if(second) score++;
                if(third) score++;
                tvResult.setText(getString(R.string.wrong));
                tvResult.setTextColor(Color.RED);
                btnSubmit.setText(getString(R.string.next));
            }
        } else {
            resetTries();
            ShowCars();
        }
        strScore = "Score: " + score;
        tvScore.setText(strScore);
    }

    //resets tries
    private void resetTries(){
        tries = 3;
        strTries = "Tries left: " + tries;
        tvTries.setText(strTries);
        etCar0.setEnabled(true);
        etCar1.setEnabled(true);
        etCar2.setEnabled(true);
        tvCar0.setText("");
        tvCar1.setText("");
        tvCar2.setText("");
        tvResult.setText("");
        etCar0.setBackground(originalBackground);
        etCar0.setEnabled(true);
        etCar0.setText("");
        etCar1.setBackground(originalBackground);
        etCar1.setEnabled(true);
        etCar1.setText("");
        etCar2.setBackground(originalBackground);
        etCar2.setEnabled(true);
        etCar2.setText("");
    }

    //checks entered answers
    public boolean checkAnswer(EditText input, String answer) {
        if (input.getText().toString().trim().equalsIgnoreCase(answer)) {
            input.setBackgroundColor(getColor(R.color.green));
            input.setBackgroundTintMode(PorterDuff.Mode.SRC_IN);
            input.setEnabled(false);
            return true;
        } else {
            input.setBackgroundColor(getColor(R.color.red));
            input.setBackgroundTintMode(PorterDuff.Mode.SRC_IN);
            input.setEnabled(true);
            return false;
        }
    }

}