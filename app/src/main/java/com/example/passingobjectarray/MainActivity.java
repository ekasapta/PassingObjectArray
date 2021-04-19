package com.example.passingobjectarray;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<User> users;
    int jumlahData=0;
    int toleransi = 5;
    EditText etUsername, etPassword;
    Button btnLogin, btnRegister;
    TextView tvToleransi, tvRegisterNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        tvToleransi = (TextView) findViewById(R.id.tvToleransi);
        tvRegisterNow = (TextView) findViewById(R.id.tvRegisterNow);

        Intent intent = getIntent();
        users = intent.getParcelableArrayListExtra("users");
        jumlahData=intent.getIntExtra("jumlahData", 0);

        tvRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(MainActivity.this, Register.class);
                startActivity(intentRegister);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etUsername.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Username masih kosong!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(etPassword.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Password masih kosong!", Toast.LENGTH_SHORT).show();
                    return;
                }

                checkLogin(etUsername.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    private void checkLogin(String username,  String password) {
        for (int i=0; i < users.size(); i++) {
            String usernameRegistered = users.get(i).getUsername();
            String passwordRegistered = users.get(i).getPassword();
            if(username.equals(usernameRegistered) && password.equals(passwordRegistered)) {
                Intent welcomeIntent = new Intent(MainActivity.this, WelcomeUser.class);
                welcomeIntent.putExtra("username", usernameRegistered);
                startActivity(welcomeIntent);
            } else {
                toleransi--;
                Toast.makeText(getApplicationContext(), "Username dan Password Salah!", Toast.LENGTH_SHORT).show();
                tvToleransi.setText("Total Fault Tolerance: "+toleransi);
                if(toleransi == 0) {
                    btnLogin.setEnabled(false);
                }
            }
        }
    }
}