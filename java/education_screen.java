package com.example.secret_messenger;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class education_screen extends AppCompatActivity {

    ImageButton encoding;
    ImageButton home;
    ImageButton settings;

    Button low;
    Button medium;
    Button high;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_screen);

        // Back Button Toolbar
        androidx.appcompat.widget.Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

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

        low = findViewById(R.id.LowLevelButton);
        low.setOnClickListener(
                v -> {
                    Intent i = new Intent(education_screen.this, LowEducation.class);
                    startActivity(i);
                }
        );

        medium = findViewById(R.id.MediumLevelButton);
        medium.setOnClickListener(
                v -> {
                    Intent i = new Intent(education_screen.this, MediumEducation.class);
                    startActivity(i);
                }
        );

        high = findViewById(R.id.HighLevelButton);
        high.setOnClickListener(
                v -> {
                    Intent i = new Intent(education_screen.this, HighEducation.class);
                    startActivity(i);
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
