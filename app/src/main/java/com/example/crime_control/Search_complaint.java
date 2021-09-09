package com.example.crime_control;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Search_complaint extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_complaint);
    }
}

/*{
    private static final String TAG = "Show_complaints";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private EditText editTextTitle;
    private EditText editTextDescription;
    private EditText editTextPriority;
    private TextView textViewData;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Notebook");
    private DocumentReference noteRef = db.document("Notebook/My First Note");
    Button add_crime,load_crime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_complaint);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextPriority = findViewById(R.id.edit_text_priority);
        textViewData = findViewById(R.id.text_view_data);

        add_crime = findViewById(R.id.btn_add_crime);
        add_crime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();
                if (editTextPriority.length() == 0) {
                    editTextPriority.setText("0");
                }
                int priority = Integer.parseInt(editTextPriority.getText().toString());
                Note note = new Note(title, description, priority);
                notebookRef.add(note);
            }
        });

        load_crime = findViewById(R.id.btn_admin_load);
        load_crime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    Task task1 = notebookRef.whereLessThan("priority", 2)
                            .orderBy("priority")
                            .get();
                    Task task2 = notebookRef.whereGreaterThan("priority", 2)
                            .orderBy("priority")
                            .get();
                    Task<List<QuerySnapshot>> allTasks = Tasks.whenAllSuccess(task1, task2);
                    allTasks.addOnSuccessListener(new OnSuccessListener<List<QuerySnapshot>>() {
                        @Override
                        public void onSuccess(List<QuerySnapshot> querySnapshots) {
                            String data = "";
                            for (QuerySnapshot queryDocumentSnapshots : querySnapshots) {
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    Note note = documentSnapshot.toObject(Note.class);
                                    note.setDocumentId(documentSnapshot.getId());
                                    String documentId = note.getDocumentId();
                                    String title = note.getTitle();
                                    String description = note.getDescription();
                                    int priority = note.getPriority();
                                    data += "ID: " + documentId
                                            + "\nTitle: " + title + "\nDescription: " + description
                                            + "\nPriority: " + priority + "\n\n";
                                }
                            }
                            textViewData.setText(data);
                        }
                    });
                }
            }
        });

    }
*//*
    @Override
    protected void onStart() {
        super.onStart();
        notebookRef.addSnapshotListener(this, new com.google.firebase.firestore.EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot queryDocumentSnapshots, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException e) {
                {
                    if (e != null) {
                        return;
                    }
                    String data = "";
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Note note = documentSnapshot.toObject(Note.class);
                        note.setDocumentId(documentSnapshot.getId());
                        String documentId = note.getDocumentId();
                        String title = note.getTitle();
                        String description = note.getDescription();
                        int priority = note.getPriority();
                        data += "ID: " + documentId
                                + "\nTitle: " + title + "\nDescription: " + description
                                + "\nPriority: " + priority + "\n\n";
                    }
                    textViewData.setText(data);
                }
            }
        });


        *//**//*public void addNote (View v){
            String title = editTextTitle.getText().toString();
            String description = editTextDescription.getText().toString();
            if (editTextPriority.length() == 0) {
                editTextPriority.setText("0");
            }
            int priority = Integer.parseInt(editTextPriority.getText().toString());
            Note note = new Note(title, description, priority);
            notebookRef.add(note);
        }*//**//*

        *//**//*public void loadNotes (View v){
            Task task1 = notebookRef.whereLessThan("priority", 2)
                    .orderBy("priority")
                    .get();
            Task task2 = notebookRef.whereGreaterThan("priority", 2)
                    .orderBy("priority")
                    .get();
            Task<List<QuerySnapshot>> allTasks = Tasks.whenAllSuccess(task1, task2);
            allTasks.addOnSuccessListener(new OnSuccessListener<List<QuerySnapshot>>() {
                @Override
                public void onSuccess(List<QuerySnapshot> querySnapshots) {
                    String data = "";
                    for (QuerySnapshot queryDocumentSnapshots : querySnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Note note = documentSnapshot.toObject(Note.class);
                            note.setDocumentId(documentSnapshot.getId());
                            String documentId = note.getDocumentId();
                            String title = note.getTitle();
                            String description = note.getDescription();
                            int priority = note.getPriority();
                            data += "ID: " + documentId
                                    + "\nTitle: " + title + "\nDescription: " + description
                                    + "\nPriority: " + priority + "\n\n";
                        }
                    }
                    textViewData.setText(data);
                }
            });*//**//*
        }*//*
    }*/
