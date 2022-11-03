package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class IdentifyBrand extends AppCompatActivity {
    private Spinner brandDropdown;
    private Cars cars;
    private Car car;
    private ImageView carView;
    private TextView answers;
    private Button submit;
    private String correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_brand);
        getSupportActionBar().setTitle("");

    }
}