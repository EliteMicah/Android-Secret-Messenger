package com.example.secret_messenger;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button encode;
    Button decode;
    Button education;
    Button home;
    Button settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        encode = findViewById(R.id.EncodeButton);
        encode.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this,encode_screen.class);
                        startActivity(i);
                    }
                }
        );

        decode = findViewById(R.id.DecodeButton);
        decode.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this,decode_screen.class);
                        startActivity(i);
                    }
                }
        );

        education = findViewById(R.id.EducationButton);
        education.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this,education_screen.class);
                        startActivity(i);
                    }
                }
        );


    }
}
