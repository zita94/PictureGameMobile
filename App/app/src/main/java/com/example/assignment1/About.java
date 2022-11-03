package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class About extends AppCompatActivity {
    private TextView AboutTextView;
    final public static String AboutText = "StudentId: B00786645\nStudentName: Zita Koczka" +
            "\n\nI confirm that I understand what plagiarism is and have read the University's " +
            "policy on plagiarism and understand the definition of plagiarism. The work that I have " +
            "submitted is entirely my own. Any work from other authors is duly referenced and " +
            "acknowledged. I understand that I may face sanctions in accordance with the policies" +
            " and procedures of the University.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        AboutTextView = findViewById(R.id.textView_About);
        AboutTextView.setText(AboutText);
        AboutTextView.setTextSize(18);
        AboutTextView.setPadding(15,0,15,0);
        AboutTextView.setClickable(false);

        ImageButton BackButton = findViewById(R.id.imageButton_BackAbout);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(About.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}