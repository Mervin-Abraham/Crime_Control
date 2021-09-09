package com.example.crime_control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Adminpage extends AppCompatActivity {

    Button add_crime,search_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        add_crime = findViewById(R.id.btn_add_crime);
        add_crime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Adminpage.this, addcrime.class));
                finish();
            }
        });

        search_record = findViewById(R.id.btn_search_record);
        search_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Adminpage.this, Search_complaint.class));
                finish();
            }
        });
    }
}