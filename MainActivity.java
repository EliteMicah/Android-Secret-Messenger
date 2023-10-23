package com.example.rebooked1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE_FIRST = "com.example.rebooked1.FIRST_NAME";
    public static final String EXTRA_MESSAGE_LAST = "com.example.rebooked1.LAST_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    /** Called when the user taps the button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Activity_display_message.class);
        // alternative:  Bundle extras = new Bundle();
        EditText editTextFirst = findViewById(R.id.firstNameText);
        EditText editTextLast = findViewById(R.id.lastNameText);

        String messageFirstName = editTextFirst.getText().toString();
        String messageLastName = editTextLast.getText().toString();

        intent.putExtra(EXTRA_MESSAGE_FIRST, messageFirstName);
        intent.putExtra(EXTRA_MESSAGE_LAST, messageLastName);
        startActivity(intent);
    }
}
