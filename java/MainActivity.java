package com.example.secret_messenger;

import static com.example.secret_messenger.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.Button;


import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    Button encode;
    Button decode;
    Button education;
    ImageButton settings;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        encode = findViewById(id.EncodeButton);
        encode.setOnClickListener(
                v -> {
                    Intent i = new Intent(MainActivity.this,encode_screen.class);
                    startActivity(i);
                }
        );

        decode = findViewById(id.DecodeButton);
        decode.setOnClickListener(
                v -> {
                    Intent i = new Intent(MainActivity.this,decode_screen.class);
                    startActivity(i);
                }
        );

        education = findViewById(id.EducationButton);
        education.setOnClickListener(
                v -> {
                    Intent i = new Intent(MainActivity.this,education_screen.class);
                    startActivity(i);
                }
        );

        settings = findViewById(id.settingsButton);
        settings.setOnClickListener(
                v -> {
                    Intent i = new Intent(MainActivity.this,settings_screen.class);
                    startActivity(i);
                }
        );


    }
}
