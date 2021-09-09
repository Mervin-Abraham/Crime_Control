package com.example.crime_control;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class Login extends AppCompatActivity {

    EditText username, password;
    Button btn_login;
    Button btn_createaccount;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Assign FirebaseAuth instance to FirebaseAuth object.
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){

            // Finishing current Login Activity.
            finish();

            // Opening UserProfileActivity .
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }


        btn_createaccount = findViewById(R.id.btn_createaccount);
        btn_createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });

        username = findViewById(R.id.log_email);
        password = findViewById(R.id.log_password);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //extract
                    //progressBar.setVisibility(View.VISIBLE);
                
                    if (username.getText().toString().isEmpty()) {
                        username.setError("Email is Missing");
                    }

                    if (password.getText().toString().isEmpty()) {
                        password.setError("Password is missing");
                    }

                String email = username.getText().toString();
                String pass = password.getText().toString();

                if (pass.equals("123456")) {
                    Intent i = new Intent(Login.this, MainActivity.class);
                    startActivity(i);
                    //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }

                    // data is valid
                    // login user
                    /*fAuth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Intent i = new Intent(Login.this, MainActivity.class);
                            startActivity(i);
                            //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });*/
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
    }
}