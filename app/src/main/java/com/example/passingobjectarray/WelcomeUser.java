package com.example.passingobjectarray;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeUser extends AppCompatActivity {

    TextView tvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_user);

        tvUsername = (TextView) findViewById(R.id.tvUsername);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        tvUsername.setText(String.valueOf(username));
    }
}