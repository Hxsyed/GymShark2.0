package com.example.fitnesstest;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Register extends AppCompatActivity {
    // variable initialization
    EditText fullName,Email,Password,Phone, PersonWeight, PersonHeight;
    Button registerbutton, guestworkout;
    FirebaseAuth fAuth;
    TextView alreadyregister;

    // on create activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // assigning xml values to variables
        fullName   = findViewById(R.id.fullName);
        Email      = findViewById(R.id.Email);
        Password   = findViewById(R.id.Password);
        Phone      = findViewById(R.id.Phone);
        PersonHeight      = findViewById(R.id.PersonHeight);
        PersonWeight      = findViewById(R.id.PersonWeight);
        registerbutton= findViewById(R.id.registerbutton);
        guestworkout= findViewById(R.id.guestworkout);
        alreadyregister = findViewById(R.id.alreadyregister);
        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        // register event listener
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String mfullName = fullName.getText().toString().trim();
                String phone    = Phone.getText().toString().trim();
                String mPersonHeight = PersonHeight.getText().toString().trim();
                String mPersonWeight = PersonWeight.getText().toString().trim();
                // catching a few errors
                if(TextUtils.isEmpty(mfullName)){
                    fullName.setError("Name is Required.");
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    Email.setError("Email is Required.");
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString()).matches()) {
                    Email.setError("Email is INVALID.");
                }

                if(TextUtils.isEmpty(password)){
                    Password.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    Password.setError("Password Must be >= 6 Characters");
                    return;
                }

                if(TextUtils.isEmpty(phone)){
                    Phone.setError("Phone Number is Required.");
                    return;
                }
                if(phone.length() < 10){
                    Phone.setError("Phone Number must be 10 digits");
                    return;
                }

                if(TextUtils.isEmpty(mPersonHeight)){
                    PersonHeight.setError("Height is Required.");
                    return;
                }

                if(TextUtils.isEmpty(mPersonWeight)){
                    PersonWeight.setError("Weight is Required.");
                    return;
                }

                // register the user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Privacy.class));

                        }else {
                            Toast.makeText(Register.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        // users who already registered go to login page
        alreadyregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
        // guest workout option goes to privacy page
        guestworkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Privacy.class));
            }
        });
    }
}
