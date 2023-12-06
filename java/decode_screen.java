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
import androidx.appcompat.widget.Toolbar;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
//import android.util.Base64;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.io.UnsupportedEncodingException;
import javax.crypto.spec.GCMParameterSpec;




public class decode_screen extends AppCompatActivity {


    ImageButton home;
    ImageButton swap;
    ImageButton education;
    ImageButton settings;
    ImageButton copyButton;
    ImageButton pasteButton;
    Button enter;
    EditText encodedMessage;
    String decodedMessage;

    String decodestring;
    TextView decodedText;

    SecretKey AESkey;

    KeyGenerator keyGenerator;
    SecretKey secretKey;

    private final int DATA_LENGTH = 256;
    private static final String PREF_SELECTED_SPINNER_POSITION = "selected_spinner_position";

    // MEDIUM ENCODING
    private static final String AES_ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decode_screen);

        // Back Button Toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Spinner staticSpinner = findViewById(R.id.spinner2);
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

        decodedText = findViewById(R.id.decodedText);
        encodedMessage = findViewById(R.id.encodeText);
        copyButton = findViewById(R.id.copy_button);
        pasteButton = findViewById(R.id.paste_button);

        copyButton.setOnClickListener(v -> {
            String textToCopy = decodedText.getText().toString();
            copyToClipboard(textToCopy);
        });

        pasteButton.setOnClickListener(v -> pasteFromClipboard());


        //HOME BUTTON
        home = findViewById(R.id.homeButton);
        home.setOnClickListener(
                v -> {
                    Intent i = new Intent(decode_screen.this,MainActivity.class);
                    startActivity(i);
                }
        );

        //Grabbing private key from key pair
        Bundle extras = getIntent().getExtras();
        KeyPair keyPair= (KeyPair) extras.get("KeyPair");
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();


        SecretKey secretkey=(SecretKey) extras.get("AESkey");

        //ENTER BUTTON
        enter = findViewById(R.id.EnterButton);
        enter.setOnClickListener(v -> {
            String level = staticSpinner.getSelectedItem().toString();
            switch(level) {
                case("Low") :
                    encodedMessage = findViewById(R.id.encodeText);
                    decodestring = encodedMessage.getText().toString();
                    decodedMessage = decipher(decodestring, 5);
                    decodedText = findViewById(R.id.decodedText);
                    decodedText.setText(decodedMessage);
                    break;
                case("Medium"):
                    encodedMessage = findViewById(R.id.encodeText);
                    decodestring = encodedMessage.getText().toString();
                    byte[] decodestringbytes1= new byte[0];
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        decodestringbytes1 = Base64.getDecoder().decode(decodestring);
                    }
                    byte[] iv = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B};
                    decodedMessage = decrypt(decodestringbytes1, secretkey, iv);
                    decodedText = findViewById(R.id.decodedText);
                    decodedText.setText(decodedMessage);
                    break;
                case("High"):
                    encodedMessage = findViewById(R.id.encodeText);
                    decodestring = encodedMessage.getText().toString();
                    byte[] decodestringbytes= new byte[0];
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        decodestringbytes = Base64.getDecoder().decode(decodestring);
                    }
                    decodedMessage = asymdecrypt(decodestringbytes, privateKey);
                    decodedText = findViewById(R.id.decodedText);
                    decodedText.setText(decodedMessage);
                    break;
                default:
                    break;
            }
        });


        //SWAP BUTTON
        swap = findViewById(R.id.swapbutton);
        swap.setOnClickListener(
                v -> {
                    Intent i = new Intent(decode_screen.this,encode_screen.class);
                    i.putExtra("KeyPair", keyPair);
                    i.putExtra("AESKey", secretKey);
                    startActivity(i);
                }
        );

        //EDUCATION BUTTON
        education = findViewById(R.id.educationButton);
        education.setOnClickListener(
                v -> {
                    Intent i = new Intent(decode_screen.this,education_screen.class);
                    i.putExtra("KeyPair", keyPair);
                    i.putExtra("AESKey", secretKey);
                    startActivity(i);
                }
        );

        settings = findViewById(R.id.settingsButton);
        settings.setOnClickListener(
                v -> {
                    Intent i = new Intent(decode_screen.this,settings_screen.class);
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
                encodedMessage.setText(pasteData); // Set pasted text to decodeText EditText
                Toast.makeText(this, "Text pasted from clipboard", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Clipboard is empty", Toast.LENGTH_SHORT).show();
        }
    }

    // LOW ENCRYPTION
    String decipher(String msg, int shift){
        shift=-shift;
        StringBuilder s = new StringBuilder();
        int len = msg.length();
        for(int x = 0; x < len; x++){
            char c = (char)(msg.charAt(x) + shift);
            if (c > 'z')
                s.append((char) (msg.charAt(x) - (26 - shift)));
            else
                s.append((char) (msg.charAt(x) + shift));
        }
        return s.toString();
    }

    // MEDIUM ENCRYPTION (AES)

    /*
    NOTE:
    WE WERE NEVER ABLE TO GET THIS DECRYPTION FULLY FUNCTIONING
    I HAVE IDENTIFIED THAT THE PROBLEM COMES FROM THE LINE CONTAINING CIPHER.INIT
    BUT I HAVE BEEN UNABLE TO IDENTIFY WHO IT DOES NOT WORK IN THE TIME FRAME WE HAVE BEEN GIVEN
    SO CURRENTLY THIS FUNCTION JUST RETURNS AN ERROR MESSAGE TO OUTPUT
     */
    public static String decrypt(byte[] cipherText, SecretKey key, byte[] IV) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(IV);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] decryptedText = cipher.doFinal(cipherText);
            return new String(decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error decrypting text";
    }


    /*
    THE BELOW CODE IS OTHER ATTEMPTS AT AES DECRYPTION THAT DID NOT WORK
    */

    /*
    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
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

   String decrypt2(byte[] dataInBytes, SecretKey key) {
        Cipher decryptCipher = null;
        byte[] iv = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B};
        try {
            decryptCipher = Cipher.getInstance("AES/GCM/NoPadding");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }

        try {
            decryptCipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(128, iv));
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }

        byte[] decryptedMessageBytes = new byte[0];

        try {
            decryptedMessageBytes = decryptCipher.doFinal(dataInBytes);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        }
        String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
        return decryptedMessage;
    }

    */

    // HIGH ENCRYPTION (RSA)

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
