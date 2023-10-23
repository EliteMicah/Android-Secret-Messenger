package com.example.rebooked1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Objects;

public class Activity_display_message extends AppCompatActivity {

    //OnOptionsItemsSelected()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        androidx.appcompat.widget.Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String messageFirstName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_FIRST);
        String messageLastName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_LAST);

        // Create a welcome message
        String welcomeMessage = "The following contact has been added! \n" + messageFirstName + " " + messageLastName + "!";

        // Display the welcome message in a TextView or another UI element
        TextView textViewWelcome = findViewById(R.id.contactAddedText);
        textViewWelcome.setText(welcomeMessage);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
