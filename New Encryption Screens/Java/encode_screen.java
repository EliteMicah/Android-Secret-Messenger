package com.example.secret_messenger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Button;
import android.content.ClipboardManager;
import android.content.ClipData;

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
    //final TextView encodedtext = (TextView) findViewById(R.id.decodeText);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encode_screen);

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


        //COPY BUTTON
        /*copy = findViewById(R.id.copy_button);
        copy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Context context;
                private void setClipboard (Context context, String text){
                    if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                        android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        clipboard.setText(text);
                    } else {
                        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
                        clipboard.setPrimaryClip(clip);
                    }
                }
            }
        });*/


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
