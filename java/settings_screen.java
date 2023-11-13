package com.example.secret_messenger;

import static com.example.secret_messenger.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

public class settings_screen extends AppCompatActivity {

    ImageButton home;
    ImageButton encode;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.settings_screen);

        //DROPDOWN MENU SETUP
        Spinner staticSpinner = (Spinner) findViewById(R.id.spinner1);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.encoding_levels,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);

        home = findViewById(id.homeButton);
        home.setOnClickListener(
                v -> {
                    Intent i = new Intent(settings_screen.this,MainActivity.class);
                    startActivity(i);
                }
        );

        encode = findViewById(id.EncodeButton);
        encode.setOnClickListener(
                v -> {
                    Intent i = new Intent(settings_screen.this,encode_screen.class);
                    startActivity(i);
                }
        );


    }
}
