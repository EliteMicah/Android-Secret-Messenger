package com.example.secret_messenger;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;


public class encode_screen extends AppCompatActivity {

    ImageButton home;
    ImageButton swap;
    ImageButton education;
    ImageButton settings;
    ImageButton copy;
    Button enter;

    EditText decodedmessage;
    String encodedmessage;
    TextView encodedtext;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encode_screen);

        // Back Button Toolbar
        androidx.appcompat.widget.Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //DROPDOWN MENU SETUP
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
                    Intent i = new Intent(encode_screen.this,MainActivity.class);
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
                        decodedmessage = (EditText) findViewById(R.id.encodeText);
                        String decodestring = decodedmessage.getText().toString();
                        encodedmessage = cipher(decodestring, 5);
                        encodedtext = (TextView) findViewById(R.id.decodeText);
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
                    Intent i = new Intent(encode_screen.this,decode_screen.class);
                    startActivity(i);
                }
        );


        //EDUCATION BUTTON
        education = findViewById(R.id.educationButton);
        education.setOnClickListener(
                v -> {
                    Intent i = new Intent(encode_screen.this,education_screen.class);
                    startActivity(i);
                }
        );

        settings = findViewById(R.id.settingsButton);
        settings.setOnClickListener(
                v -> {
                    Intent i = new Intent(encode_screen.this,settings_screen.class);
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

    String cipher(String msg, int shift){
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
