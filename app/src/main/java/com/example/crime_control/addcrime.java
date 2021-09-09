package com.example.crime_control;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class addcrime extends AppCompatActivity {
    private static final String TAG = "Add Crime";
    private static final String KEY_TITLE = "cname";
    private static final String KEY_LOCATION = "clocation";
    private static final String KEY_DES = "cdescription";

    EditText name;
    EditText location;
    EditText description;
    Button button;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcrime);

        name = findViewById(R.id.crime_name);
        location = findViewById(R.id.crime_location);
        description = findViewById(R.id.crime_description);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=name.getText().toString();
                String l=location.getText().toString();
                String d=description.getText().toString();

                if (name.getText().toString().isEmpty()) {
                    name.setError("Crime name is missing");
                }

                if (location.getText().toString().isEmpty()) {
                    location.setError("Location of the crime is missing");
                }

                if (description.getText().toString().isEmpty()) {
                    description.setError("Description of the crime is missing");
                }

                Map<String,Object> note=new HashMap<>();
                note.put(KEY_TITLE,n);
                note.put(KEY_LOCATION,l);
                note.put(KEY_DES,d);
                db.collection("Notebook").document("crime").set(note)
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(addcrime.this,"Not saved",Toast.LENGTH_SHORT).show();
                                Log.d(TAG,"onFailure");
                            }
                        })
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid)
                            {Toast.makeText(addcrime.this,"saved",Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

    }
    /*public void saveNote(View v)
    {
        String n=name.getText().toString();
        String l=location.getText().toString();
        String d=description.getText().toString();

        Map<String,Object> note=new HashMap<>();
        note.put(KEY_TITLE,n);
        note.put(KEY_LOCATION,l);
        note.put(KEY_DES,d);
        db.collection("Crime Control").document("Crime Details").set(note)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addcrime.this,"Not saved",Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"onFailure");
                    }
                })
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid)
                    {Toast.makeText(addcrime.this,"Saved",Toast.LENGTH_SHORT).show();
                    }
                });
    }*/

}

