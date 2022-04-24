package edu.neu.madcourse.charge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class NewJournalEntry extends AppCompatActivity {
    Button saveButton;
    TextView journalTitle, journalDescrip;
    private DatabaseReference databaseReference;
    String user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_journal_entry);

        ////USE THE RIGHT ID'S!!!
        journalTitle = findViewById(R.id.entryTitleInput);
        journalDescrip = findViewById(R.id.journalEntryText);
        saveButton = findViewById(R.id.saveEntry_button);

        //Retrieve user information
        FirebaseAuth auth = FirebaseAuth.getInstance();
        user = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference(user);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //STORE EACH STRING AND THEN I CAN PUT INTO MY JOURNAL OBJECT
                // Journal journal = new Journal();
                String text = journalTitle.getText().toString();

                //TODO: Write to the database
                //STEP 1: Generate  new key and store
                String newKey = databaseReference.child("Journal").push().getKey();
                //STEP 2: setValue for Title, Description, and Date

                //TODO:Add new journal object
                //databaseReference.child("Journal").child(newKey).setValue(new Journal());
                finish();
            }
        });
    }


    public void onDeleteClick(View view) {
        finish();
    }
}