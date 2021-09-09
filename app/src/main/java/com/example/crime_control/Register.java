package com.example.crime_control;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Register extends AppCompatActivity {
    EditText reg_full_name;
    EditText reg_username;
    EditText reg_phone;
    EditText reg_CountryCode;
    EditText reg_email;
    EditText reg_password;
    EditText reg_conf_password;
    Button btn_register,btn_Already_Registered;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg_full_name = findViewById(R.id.reg_full_name);
        reg_username = findViewById(R.id.reg_username);
        reg_CountryCode = findViewById(R.id.reg_CountryCode);
        reg_phone = findViewById(R.id.reg_phone);
        reg_email = findViewById(R.id.reg_email);
        reg_password = findViewById(R.id.reg_Password);
        reg_conf_password = findViewById(R.id.reg_conf_password);

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        btn_Already_Registered =findViewById(R.id.btn_Already_Registered);
        btn_Already_Registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this,Login.class);
                startActivity(i);
            }
        });

        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = reg_email.getText().toString().trim();
                String password = reg_password.getText().toString().trim();
                String fullname = reg_full_name.getText().toString().trim();
                String pass2 = reg_conf_password.getText().toString().trim();

                //if (fullname.isEmpty()){
                //    full_name.setError("Full Name is required");
                //    return;
                //}

                if (email.isEmpty()){
                    reg_email.setError("Email is required");
                    return;
                }

                //if (pass2.isEmpty()){
                //    mEmail.setError("Confirm password is required");
                //     return;
                //}

                if(password.isEmpty()){
                    reg_password.setError("Password is required");
                    return;
                }

                //if((password).length() < 10){
                //    reg_conf_password.setError("Password must be at least of 10 characters");
                //    return;
                //}

                //if (!password.equals(pass2)){
                //    reg_password.setError("Password does not match");
                //    reg_conf_password.setError("Password does not match");
                //    return;
                //}

                // Data is validated
                // Register the user with FireBase

                Toast.makeText(Register.this, "Data Validated", Toast.LENGTH_SHORT).show();


                if(TextUtils.equals(password, pass2)){
                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Successful Registration", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                        if(!task.isSuccessful()){
                            Toast.makeText(Register.this, "Failed Registration", Toast.LENGTH_SHORT).show();
                        }
                    }});
                }
            }
        });
    }
}
