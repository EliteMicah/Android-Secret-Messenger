package com.example.secret_messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import java.util.Objects;


public class MediumEducation extends AppCompatActivity {

    ImageButton home;
    ImageButton settings;
    ImageButton encoding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mediumleveleducation);

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
                        Intent i = new Intent(MediumEducation.this,MainActivity.class);
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
                        Intent i = new Intent(MediumEducation.this,encode_screen.class);
                        startActivity(i);
                    }
                }
        );

        settings = findViewById(R.id.SettingsButton);
        settings.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MediumEducation.this,settings_screen.class);
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

