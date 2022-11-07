package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Hints extends AppCompatActivity {
    private ImageView ivCar;
    private TextView tvBrandName;
    private TextView tvHints;
    private TextView tvTries;
    private TextView tvResult;
    private EditText etLetter;
    private Cars cars;
    private Car car;
    private String strHints;
    private String strTries;
    private String strCorrectAnswer;
    private int Tries = 3;
    private ArrayList<Character> letterList = new ArrayList<>();
    private ArrayList<Character> dashesList = new ArrayList<>();
    private boolean correctLetter = false;
    private boolean correctTry = false;
    private boolean correctAnswer = false;
    private boolean submitPressed = true;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints);
        getSupportActionBar().setTitle("");
        strHints = "Enter a single letter into the box and press submit, if the letter is in the brand" +
                " it will reveal under the dash.";
        strTries = "Tries left: " + Tries;

        ivCar = findViewById(R.id.imageView_HintsCar);
        cars = new Cars();
        tvHints = findViewById(R.id.textView_HintsHint);
        tvHints.setText(strHints);
        tvBrandName = findViewById(R.id.textView_HintsBrand);
        tvTries = findViewById(R.id.textView_HintsTries);
        tvResult = findViewById(R.id.textView_HintsResult);
        btnSubmit = findViewById(R.id.button_HintsSubmit);
        etLetter = findViewById(R.id.editText_HintsGuess);
        ShowNewCar();
    }

    //shows new image of a car
    private void ShowNewCar() {
        btnSubmit.setText(getString(R.string.submit));
        car = cars.getRandomCar();
        strCorrectAnswer = car.getBrand().toLowerCase(Locale.ROOT);
        Bitmap bitmap = BitmapFactory.decodeFile(car.getFilePath());
        ivCar.setImageBitmap(bitmap);
        SetUpDashes();
        tvBrandName.setText(dashesList.toString().replaceAll(",", " ")
                .replaceAll("\\[", "").replaceAll("]", ""));
        tvBrandName.setTextColor(Color.WHITE);
        tvTries.setText(strTries);
    }

    //sets up the dashes
    private void SetUpDashes() {
        for (Character letter : strCorrectAnswer.toCharArray()) {
            letterList.add(letter);
        }
        for (int i = 0; i < letterList.size(); i++) {
            dashesList.add('-');
        }
    }

    //checks if the letter entered is contained in the correct answer
    private void CheckLetter(char letter) {
        correctTry = false;
        for (int i = 0; i < letterList.size(); i++) {
            if (letterList.get(i).equals(letter)) {
                dashesList.set(i, letter);
                correctTry = true;
            }
        }
        correctAnswer = !dashesList.contains('-');
    }

    //resets all the information needed for a new try
    private void resetTries() {
        Tries = 3;
        letterList.clear();
        dashesList.clear();
        strTries = "Tries left: " + Tries;
        tvTries.setText(strTries);
        tvResult.setText("");
    }

    //deals with the submit button and changes it to "next" when needed
    public void submitOnClick(View view) {
        try {
            if (btnSubmit.getText().equals(getString(R.string.submit))) {
                if (Tries > 0) {
                    char temp = etLetter.getText().toString().charAt(0);
                    CheckLetter(temp);
                    if (correctAnswer) {
                        tvResult.setText(getString(R.string.correct));
                        tvResult.setTextColor(Color.GREEN);
                        btnSubmit.setText(getString(R.string.next));
                    }
                    if (correctTry) {
                        tvBrandName.setText(dashesList.toString().replaceAll(",", " ")
                                .replaceAll("\\[", "").replaceAll("]", ""));
                    } else {
                        Tries--;
                        strTries = "Tries left: " + Tries;
                        tvTries.setText(strTries);
                    }
                }
                if(Tries == 0) {
                    tvResult.setText(getString(R.string.wrong));
                    tvResult.setTextColor(Color.RED);
                    tvBrandName.setText(strCorrectAnswer);
                    tvBrandName.setTextColor(Color.YELLOW);
                    btnSubmit.setText(getString(R.string.next));
                }
            } else {
                resetTries();
                ShowNewCar();
            }
            etLetter.setText("");
        }catch(Exception e){
            Toast.makeText(this, "Please enter a letter", Toast.LENGTH_SHORT).show();
        }
    }
}
