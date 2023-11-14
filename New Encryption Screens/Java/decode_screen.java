package com.example.secret_messenger;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class decode_screen extends AppCompatActivity {


    ImageButton home;
    ImageButton swap;
    ImageButton education;
    ImageButton settings;

    Button enter;

    EditText decodedmessage;
    String encodedmessage;
    TextView encodedtext;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decode_screen);


        Spinner staticSpinner = findViewById(R.id.spinner1);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.encoding_levels,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);



        //HOME BUTTON
        home = findViewById(R.id.homeButton);
        home.setOnClickListener(
                v -> {
                    Intent i = new Intent(decode_screen.this,MainActivity.class);
                    startActivity(i);
                }
        );

        //ENTER BUTTON
        enter = findViewById(R.id.EnterButton);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String level = staticSpinner.getSelectedItem().toString();
                switch(level) {
                    case("Low") :
                        decodedmessage = (EditText) findViewById(R.id.decodeText);
                        String decodestring = decodedmessage.getText().toString();
                        encodedmessage = decipher(decodestring, 5);
                        encodedtext = (TextView) findViewById(R.id.encodeText);
                        encodedtext.setText(encodedmessage);
                        break;
                    case("Medium"):
                        break;
                    case("High"):
                        break;
                    default:
                        break;
                }
            }
        });


        //SWAP BUTTON
        swap = findViewById(R.id.swapbutton);
        swap.setOnClickListener(
                v -> {
                    Intent i = new Intent(decode_screen.this,encode_screen.class);
                    startActivity(i);
                }
        );

        //EDUCATION BUTTON
        education = findViewById(R.id.educationButton);
        education.setOnClickListener(
                v -> {
                    Intent i = new Intent(decode_screen.this,education_screen.class);
                    startActivity(i);
                }
        );

        settings = findViewById(R.id.settingsButton);
        settings.setOnClickListener(
                v -> {
                    Intent i = new Intent(decode_screen.this,settings_screen.class);
                    startActivity(i);
                }
        );
    }

    String decipher(String msg, int shift){
        shift=-shift;
        String s = "";
        int len = msg.length();
        for(int x = 0; x < len; x++){
            char c = (char)(msg.charAt(x) + shift);
            if (c > 'z')
                s += (char)(msg.charAt(x) - (26-shift));
            else
                s += (char)(msg.charAt(x) + shift);
        }
        return s;
    }
}
