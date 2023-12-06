package com.example.secret_messenger;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
//import android.util.Base64;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.spec.GCMParameterSpec;


public class encode_screen extends AppCompatActivity {

    ImageButton home;
    ImageButton swap;
    ImageButton education;
    ImageButton settings;
    ImageButton copyButton;
    ImageButton pasteButton;
    Button enter;
    EditText decodedMessage;
    String encodedMessage;
    String decodestring;
    TextView encodedText;
    SecretKey AESkey;
    KeyGenerator keyGenerator;
    SecretKey secretKey;

    IvParameterSpec ivParameterSpec;

   // private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04glagN2vgoZoHuOPqa5and6kAmK2ujmCHu6D1auJhE2tXP+yLkpSiYMQucDKmCsWMnW9XlC5K7OSL77TXXcfvTvyZcjObEz6LIBRzs6+FqpFbUO9SJEfh6wIDAQAB";
  //  private static String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKAUZV+tjiNBKhlBZbKBnzeugpdYPhh5PbHanjV0aQ+LF7vetPYhbTiCVqA3a+Chmge44+prlqd3qQCYra6OYIe7oPVq4mETa1c/7IuSlKJgxC5wMqYKxYydb1eULkrs5IvvtNddx+9O/JlyM5sTPosgFHOzr4WqkVtQ71IkR+HrAgMBAAECgYAkQLo8kteP0GAyXAcmCAkA2Tql/8wASuTX9ITD4lsws/VqDKO64hMUKyBnJGX/91kkypCDNF5oCsdxZSJgV8owViYWZPnbvEcNqLtqgs7nj1UHuX9S5yYIPGN/mHL6OJJ7sosOd6rqdpg6JRRkAKUV+tmN/7Gh0+GFXM+ug6mgwQJBAO9/+CWpCAVoGxCA+YsTMb82fTOmGYMkZOAfQsvIV2v6DC8eJrSa+c0yCOTa3tirlCkhBfB08f8U2iEPS+Gu3bECQQCrG7O0gYmFL2RX1O+37ovyyHTbst4s4xbLW4jLzbSoimL235lCdIC+fllEEP96wPAiqo6dzmdH8KsGmVozsVRbAkB0ME8AZjp/9Pt8TDXD5LHzo8mlruUdnCBcIo5TMoRG2+3hRe1dHPonNCjgbdZCoyqjsWOiPfnQ2Brigvs7J4xhAkBGRiZUKC92x7QKbqXVgN9xYuq7oIanIM0nz/wq190uq0dh5Qtow7hshC/dSK3kmIEHe8z++tpoLWvQVgM538apAkBoSNfaTkDZhFavuiVl6L8cWCoDcJBItip8wKQhXwHp0O3HLg10OEd14M58ooNfpgt+8D8/8/2OOFaR0HzA+2Dm";

    private static final String PREF_SELECTED_SPINNER_POSITION = "selected_spinner_position";

    // MEDIUM ENCODING
    private static final String AES_ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

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
        Spinner staticSpinner = findViewById(R.id.spinner);
        staticSpinner.setEnabled(false);

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

        encodedText = findViewById(R.id.encodedText);
        decodedMessage = findViewById(R.id.decodeText);
        copyButton = findViewById(R.id.copy_button);
        pasteButton = findViewById(R.id.paste_button);

        copyButton.setOnClickListener(v -> {
            String textToCopy = encodedText.getText().toString();
            copyToClipboard(textToCopy);
        });

        pasteButton.setOnClickListener(v -> pasteFromClipboard());


        //HOME BUTTON
        home = findViewById(R.id.homeButton);
        home.setOnClickListener(
                v -> {
                    Intent i = new Intent(encode_screen.this,MainActivity.class);
                    startActivity(i);
                }
        );

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

        Cipher cipher = null;

        //ENTER BUTTON
        enter = findViewById(R.id.EnterButton);
        enter.setOnClickListener(v -> {
            String level = staticSpinner.getSelectedItem().toString();
            switch(level) {
                case("Low") :
                    decodedMessage = findViewById(R.id.decodeText);
                    decodestring = decodedMessage.getText().toString();
                    encodedMessage = cipher(decodestring);
                    encodedText = findViewById(R.id.encodedText);
                    encodedText.setText(encodedMessage);
                    break;
                case("Medium"):
                    decodedMessage = findViewById(R.id.decodeText);
                    decodestring = decodedMessage.getText().toString();
                    byte[] decodebytes= new byte[0];
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        decodebytes = Base64.getDecoder().decode(decodestring);
                    }
                    byte[] iv = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B};
                    byte[] cipherBytes = null;

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        try {
                            cipherBytes = encrypt(decodebytes, secretKey, iv);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    String cipherText = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        cipherText = Base64.getEncoder().encodeToString(cipherBytes);
                    }
                    encodedText = findViewById(R.id.encodedText);
                    encodedText.setText(cipherText);
                    //String plainText = decrypt(algorithm, cipherText, key, ivParameterSpec);
                    break;
                case("High"):
                    decodedMessage = findViewById(R.id.decodeText);
                    decodestring = decodedMessage.getText().toString();
                    byte[] encryptedMessageBytes = new byte[0];
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        encryptedMessageBytes = asymencrypt(decodestring, publicKey);
                    }
                    String encodedMessage = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);
                    }
                    encodedText = findViewById(R.id.encodedText);
                    encodedText.setText(encodedMessage);

                    break;
                default:
                    break;
            }
        });


        //SWAP BUTTON
        swap = findViewById(R.id.swapbutton);
        swap.setOnClickListener(
                v -> {
                    Intent i = new Intent(encode_screen.this,decode_screen.class);
                    i.putExtra("KeyPair", keyPair);
                    i.putExtra("AESKey", secretKey);
                    //i.putExtra("Cipher", cipher);
                    startActivity(i);
                }
        );


        //EDUCATION BUTTON
        education = findViewById(R.id.educationButton);
        education.setOnClickListener(
                v -> {
                    Intent i = new Intent(encode_screen.this,education_screen.class);
                    i.putExtra("KeyPair", keyPair);
                    i.putExtra("AESKey", secretKey);
                    startActivity(i);
                }
        );

        settings = findViewById(R.id.settingsButton);
        settings.setOnClickListener(
                v -> {
                    Intent i = new Intent(encode_screen.this,settings_screen.class);
                    i.putExtra("KeyPair", keyPair);
                    i.putExtra("AESKey", secretKey);
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

    private void copyToClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied Text", text);
        if (clipboard != null) {
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_SHORT).show();
        }
    }

    private void pasteFromClipboard() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        if (clipboard != null && clipboard.hasPrimaryClip()) {
            ClipData.Item item = Objects.requireNonNull(clipboard.getPrimaryClip()).getItemAt(0);
            CharSequence pasteData = item.getText();
            if (pasteData != null) {
                decodedMessage.setText(pasteData); // Set pasted text to decodeText EditText
                Toast.makeText(this, "Text pasted from clipboard", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Clipboard is empty", Toast.LENGTH_SHORT).show();
        }
    }

    // LOW ENCRYPTION
    String cipher(String msg){
        StringBuilder s = new StringBuilder();
        int len = msg.length();
        for(int x = 0; x < len; x++){
            char c = (char)(msg.charAt(x) + 5);
            if (c > 'z')
                s.append((char) (msg.charAt(x) - (26 - 5)));
            else
                s.append((char) (msg.charAt(x) + 5));
        }
        return s.toString();
    }

    // MEDIUM ENCRYPTION (AES)

    public static byte[] encrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] cipherText = cipher.doFinal(plaintext);
        return cipherText;
    }

    /*
    THE BELOW CODE IS PREVIOUS ATTEMPTS AT AES ENCRYPTION
    THAT WOULD POTENTIALLY WORK BETTER WITH DECRYPTION
    BUT DID NOT END UP HELPIN
     */


    /*public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    byte[] encrypt2(String data, SecretKey key) {
        byte[] dataInBytes = data.getBytes(StandardCharsets.UTF_8);
        Cipher encryptionCipher;
        try {
            encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
            //encryptionCipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
        byte[] iv = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B};
        try {
            encryptionCipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(128, iv));
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        byte[] encryptedBytes = new byte[0];
        try {
            encryptedBytes = encryptionCipher.doFinal(dataInBytes);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        }
        return encryptedBytes;
        //return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static byte[] encrypt2(byte[] plaintext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] cipherText = cipher.doFinal(plaintext);
        return cipherText;
    }
    */

    //HIGH ENCRYPTION (RSA)
    @RequiresApi(api = Build.VERSION_CODES.O)
   byte[] asymencrypt(String msg, PublicKey pubkey) {
       Cipher encryptCipher = null;
       try {
           encryptCipher = Cipher.getInstance("RSA");
       } catch (NoSuchAlgorithmException e) {
           throw new RuntimeException(e);
       } catch (NoSuchPaddingException e) {
           throw new RuntimeException(e);
       }
       try {
           encryptCipher.init(Cipher.ENCRYPT_MODE, pubkey);
       } catch (InvalidKeyException e) {
           throw new RuntimeException(e);
       }
       byte[] secretMessageBytes = msg.getBytes(StandardCharsets.UTF_8);
       byte[] encryptedMessageBytes = new byte[0];
       try {
           encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
       } catch (BadPaddingException e) {
           throw new RuntimeException(e);
       } catch (IllegalBlockSizeException e) {
           throw new RuntimeException(e);
       }
       return encryptedMessageBytes;
   }

    String asymdecrypt(byte[] msg, PrivateKey privkey) {
        Cipher decryptCipher = null;
        try {
            decryptCipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
        try {
            decryptCipher.init(Cipher.DECRYPT_MODE, privkey);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        byte[] decryptedMessageBytes = new byte[0];

        try {
            decryptedMessageBytes = decryptCipher.doFinal(msg);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        }
        String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
        return decryptedMessage;
    }


}
