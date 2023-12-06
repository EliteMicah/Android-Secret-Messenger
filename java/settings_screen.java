package com.example.secret_messenger;

import static com.example.secret_messenger.R.id;
import static com.example.secret_messenger.R.layout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Objects;

import javax.crypto.SecretKey;

public class settings_screen extends AppCompatActivity {

    ImageButton home;
    ImageButton encode;

    private static final String PREF_SELECTED_SPINNER_POSITION = "selected_spinner_position";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.settings_screen);

        // Back Button Toolbar
        androidx.appcompat.widget.Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //DROPDOWN MENU SETUP
        Spinner staticSpinner = findViewById(id.spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.encoding_levels,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);

        // Retrieve the saved position from SharedPreferences and set the spinner selection
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int selectedPosition = preferences.getInt(PREF_SELECTED_SPINNER_POSITION, 0);
        staticSpinner.setSelection(selectedPosition);

        // Set a listener to save the selected position when the spinner item is changed
        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                // Save the selected position in SharedPreferences
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(PREF_SELECTED_SPINNER_POSITION, position);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Not used, but required for interface implementation
            }
        });

        Bundle extras = getIntent().getExtras();
        KeyPair keyPair= (KeyPair) Objects.requireNonNull(extras).get("KeyPair");
        PrivateKey privateKey = Objects.requireNonNull(keyPair).getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        SecretKey secretkey=(SecretKey) extras.get("AESkey");

        home = findViewById(id.homeButton);
        home.setOnClickListener(
                v -> {
                    Intent i = new Intent(settings_screen.this,MainActivity.class);
                    i.putExtra("KeyPair", keyPair);
                    i.putExtra("AESKey", secretkey);
                    startActivity(i);
                }
        );

        encode = findViewById(id.EncodeButton);
        encode.setOnClickListener(
                v -> {
                    Intent i = new Intent(settings_screen.this,encode_screen.class);
                    i.putExtra("KeyPair", keyPair);
                    i.putExtra("AESKey", secretkey);
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
