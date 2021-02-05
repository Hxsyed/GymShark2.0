package com.example.fitnesstest;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    // variable initialization
    EditText Logemail, Logpass;
    Button loginbutton;
    TextView newhere;
    FirebaseAuth fAuth;

    // on create activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // assigning xml values to variables
        Logemail = findViewById(R.id.Logemail);
        Logpass = findViewById(R.id.Logpass);
        fAuth = FirebaseAuth.getInstance();
        loginbutton = findViewById(R.id.alarmbutton);
        newhere = findViewById(R.id.newhere);

        // login button event listener
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // variable initialization
                String email = Logemail.getText().toString().trim();
                String password = Logpass.getText().toString().trim();

                // catching errors
                if (TextUtils.isEmpty(email)) {
                    Logemail.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Logpass.setError("Password is Required.");
                    return;
                }

                if (password.length() < 6) {
                    Logpass.setError("Password Must be >= 6 Characters");
                    return;
                }
                // authenticate the user
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Login.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
        // new user event listener
        newhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
    }
    // back pressed activity
    @Override
    public void onBackPressed()
    {
        Intent a = new Intent(Login.this,Register.class);
        startActivity(a);
        finish();
    }
}
