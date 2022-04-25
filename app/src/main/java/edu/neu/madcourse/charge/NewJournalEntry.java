package edu.neu.madcourse.charge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class NewJournalEntry extends AppCompatActivity {
    Button saveButton;
    TextView journalTitle, journalDescrip, journalEntryDate;
    private DatabaseReference databaseReference;
    String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_journal_entry);

        //Set IDs
        journalTitle = findViewById(R.id.entryTitleInput);
        journalDescrip = findViewById(R.id.journalEntryText);
        journalEntryDate = findViewById(R.id.journalEntryDate);
        saveButton = findViewById(R.id.saveEntry_button);

        //Retrieve user information
        FirebaseAuth auth = FirebaseAuth.getInstance();
        user = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference(user);

        //Save provided information
        saveButton.setOnClickListener(view -> {
            //Get Date
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date today = new Date(System.currentTimeMillis());

            //Create, set, and store EACH STRING AND THEN I CAN PUT INTO MY JOURNAL OBJECT
            Journal addedJournalEntry = new Journal();

            //Get the values
            String title = journalTitle.getText().toString();
            String description = journalDescrip.getText().toString();
            String uniqueID = databaseReference.child("Journal").push().getKey();

            //Set the values
            addedJournalEntry.setJournalTitle(title);
            addedJournalEntry.setJournalDescription(description);
            addedJournalEntry.setJournalID(uniqueID);
            addedJournalEntry.setJournalDate(formatter.format(today));

            //TODO: Write to the database
            //setValues with Journal object for Title, Description, and Date
            databaseReference.child("Journal").child(uniqueID).setValue(addedJournalEntry);

            finish();
        });
    }


    public void onDeleteClick(View view) {
        finish();
    }
}