package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CarGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_game);
        getSupportActionBar().setTitle("");

        Button IdentifyBrand = findViewById(R.id.button_IdentifyBrand);
        IdentifyBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarGame.this, IdentifyBrand.class);
                startActivity(intent);
            }
        });

        Button HintsButton = findViewById(R.id.button_Hints);
        HintsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarGame.this, Hints.class);
                startActivity(intent);
            }
        });

        Button IdentifyCar = findViewById(R.id.button_IdentifyCar);
        IdentifyCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarGame.this, IdentifyCar.class);
                startActivity(intent);
            }
        });

        Button AdvancedLevel = findViewById(R.id.button_Advanced);
        AdvancedLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarGame.this, AdvancedLevel.class);
                startActivity(intent);
            }
        });

    }
}