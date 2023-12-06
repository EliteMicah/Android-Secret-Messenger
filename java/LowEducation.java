package com.example.secret_messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.Objects;

import static com.example.secret_messenger.R.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.Button;


import android.os.Bundle;
import android.widget.ImageButton;


public class LowEducation extends AppCompatActivity {

    ImageButton home;
    ImageButton settings;
    ImageButton encoding;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lowleveleducation);

        // Back Button Toolbar
        androidx.appcompat.widget.Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        //HOME BUTTON
        home = findViewById(R.id.homeIcon);
        home.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(LowEducation.this,MainActivity.class);
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
                        Intent i = new Intent(LowEducation.this,encode_screen.class);
                        startActivity(i);
                    }
                }
        );

        settings = findViewById(R.id.SettingsButton);
        settings.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(LowEducation.this,settings_screen.class);
                        startActivity(i);
                    }
                }
        );

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        // If we got here, the user's action was not recognized.
        // Invoke the superclass to handle it.
        return super.onOptionsItemSelected(item);
    }
}

