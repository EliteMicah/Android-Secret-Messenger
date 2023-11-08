package com.example.secret_messenger;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

public class education_screen extends AppCompatActivity {

    ImageButton encoding;
    ImageButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_screen);

        //HOME BUTTON
        home = findViewById(R.id.homeIcon);
        home.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(education_screen.this,MainActivity.class);
                        startActivity(i);
                    }
                }
        );

        //HOME BUTTON
        encoding = findViewById(R.id.EncodingScreen);
        encoding.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(education_screen.this,encode_screen.class);
                        startActivity(i);
                    }
                }
        );


    }
}
