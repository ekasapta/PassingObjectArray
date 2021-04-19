package com.example.passingobjectarray;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    ArrayList<User> users=new ArrayList<>();
    private int jumlahData = 0;

    EditText etUsername, etPassword;
    Button btnLogin, btnRegister;
    TextView tvLoginNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        tvLoginNow = (TextView) findViewById(R.id.tvLoginNow);

        tvLoginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(Register.this, MainActivity.class);
                intentLogin.putParcelableArrayListExtra("users", users);
                intentLogin.putExtra("jumlahData", jumlahData);
                startActivity(intentLogin);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
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

                save(etUsername.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    private void save(String username, String password) {
        if(jumlahData < 10) {
            try {
                users.add(new User(username, password));

                Toast.makeText(getApplicationContext(), "Save Successfully", Toast.LENGTH_SHORT).show();
                jumlahData = users.size();
                etUsername.setText("");
                etPassword.setText("");
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error Save", Toast.LENGTH_SHORT).show();
            }
        }
    }
}