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
    EditText Logemail, Logpass;
    Button loginbutton;
    TextView newhere;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Logemail = findViewById(R.id.Logemail);
        Logpass = findViewById(R.id.Logpass);
        fAuth = FirebaseAuth.getInstance();
        loginbutton = findViewById(R.id.loginbutton);
        newhere = findViewById(R.id.newhere);


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Logemail.getText().toString().trim();
                String password = Logpass.getText().toString().trim();

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
//
//                // authenticate the user
//
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

        newhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        Intent a = new Intent(Login.this,Register.class);
        startActivity(a);
        finish();
    }
}
