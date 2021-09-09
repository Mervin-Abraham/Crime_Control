package com.example.crime_control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Userpage extends AppCompatActivity {
    Button face_recon,maps,add_crime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpage);

        maps = findViewById(R.id.btn_crime_locations);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Userpage.this, MapsActivity.class));
                finish();
            }
        });

        add_crime = findViewById(R.id.btn_submit_complaint);
        add_crime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Userpage.this, addcrime.class));
                finish();
            }
        });

        face_recon =(Button)findViewById(R.id.btn_scan_research);
        face_recon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Userpage.this, Facerecongnotion.class));
                finish();
            }
        });
    }
}