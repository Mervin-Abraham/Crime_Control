package com.example.crime_control;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class GoogleMaps extends AppCompatActivity {private static String TAG = "GoogleMaps";

    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpage);

        if (isServicesOK()){
            init();

        }
    }
    private void init(){
        Button btn_map = findViewById(R.id.btn_Maps);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GoogleMaps.this,MapsActivity.class); // continue from https://youtu.be/M0bYvXlhgSI?t=353
                startActivity(i);
            }
        });
    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: Checking Google Services Version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(GoogleMaps.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Service is working ");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d(TAG, "isServices: An error occurred but we can fix it ");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(GoogleMaps.this,available,ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}