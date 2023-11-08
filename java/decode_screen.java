package com.example.secret_messenger;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;



public class decode_screen extends AppCompatActivity {


    ImageButton home;
    ImageButton settings;
    ImageButton swap;

    ImageButton education;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decode_screen);


        Spinner staticSpinner = (Spinner) findViewById(R.id.spinner1);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.encoding_levels,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);



        //HOME BUTTON
        home = findViewById(R.id.homeIcon);
        home.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(decode_screen.this,MainActivity.class);
                        startActivity(i);
                    }
                }
        );


        //SWAP BUTTON
        swap = findViewById(R.id.swapbutton);
        swap.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(decode_screen.this,encode_screen.class);
                        startActivity(i);
                    }
                }
        );

        //EDUCATION BUTTON
        education = findViewById(R.id.education);
        education.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(decode_screen.this,education_screen.class);
                        startActivity(i);
                    }
                }
        );
    }



}

