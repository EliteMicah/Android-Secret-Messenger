package com.example.secret_messenger;

import static com.example.secret_messenger.R.id;
import static com.example.secret_messenger.R.layout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity {

    Button encode;
    Button decode;
    Button education;
    ImageButton settings;
    ImageButton encode1;
    KeyGenerator keyGenerator;
    SecretKey secretKey;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //GENERATING KEYS TO PASS TO ENCODE AND DECODE


        //Generating Keys for Asymmetric Encryption
        // Step 2: Create a KeyGenerator object
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        // Step 3: Initialize the KeyGenerator with a certain keysize
        keyPairGenerator.initialize(512);
        // Step 4: Generate the key pairs
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // Step 5: Extract the keys
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        try {
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            secretKey = keyGenerator.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }




        encode = findViewById(id.EncodeButton);
        encode.setOnClickListener(
                v -> {
                    Intent i = new Intent(MainActivity.this,encode_screen.class);
                    i.putExtra("KeyPair", keyPair);
                    i.putExtra("AESKey", secretKey);
                    startActivity(i);
                }
        );

        encode1 = findViewById(id.EncodingScreen);
        encode1.setOnClickListener(
                v -> {
                    Intent i = new Intent(MainActivity.this,encode_screen.class);
                    startActivity(i);
                }
        );

        decode = findViewById(id.DecodeButton);
        decode.setOnClickListener(
                v -> {
                    Intent i = new Intent(MainActivity.this,decode_screen.class);
                    i.putExtra("KeyPair", keyPair);
                    i.putExtra("AESKey", secretKey);
                    startActivity(i);
                }
        );

        education = findViewById(id.EducationButton);
        education.setOnClickListener(
                v -> {
                    Intent i = new Intent(MainActivity.this,education_screen.class);
                    startActivity(i);
                }
        );

        settings = findViewById(id.settingsButton);
        settings.setOnClickListener(
                v -> {
                    Intent i = new Intent(MainActivity.this,settings_screen.class);
                    i.putExtra("KeyPair", keyPair);
                    i.putExtra("AESKey", secretKey);
                    startActivity(i);
                }
        );
    }
}
