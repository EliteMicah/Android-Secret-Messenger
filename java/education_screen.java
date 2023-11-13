package com.example.secret_messenger;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class education_screen extends AppCompatActivity {

    ImageButton encoding;
    ImageButton home;
    ImageButton settings;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_screen);

        //HOME BUTTON
        home = findViewById(R.id.homeButton);
        home.setOnClickListener(
                v -> {
                    Intent i = new Intent(education_screen.this,MainActivity.class);
                    startActivity(i);
                }
        );

        //HOME BUTTON
        encoding = findViewById(R.id.EncodeButton);
        encoding.setOnClickListener(
                v -> {
                    Intent i = new Intent(education_screen.this,encode_screen.class);
                    startActivity(i);
                }
        );

        settings = findViewById(R.id.settingsButton);
        settings.setOnClickListener(
                v -> {
                    Intent i = new Intent(education_screen.this,settings_screen.class);
                    startActivity(i);
                }
        );
    }
}
