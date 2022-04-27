package edu.neu.madcourse.charge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class represents a populated entry that is to be edited by the user
 */
public class EditJournalActivity extends AppCompatActivity {
    Button saveButton;
    TextView journalTitle, entryDescription;
    DatabaseReference databaseReference;
    /*
    INITIALIZE AUTH TO GET USER
    FirebaseAuth auth = FirebaseAuth.getInstance();
     */

    private static final String TAG = "EditJournalActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_journal);

        //Items (Save Button, Title, Description)
        journalTitle = findViewById(R.id.editEntryTitleInput);
        saveButton = findViewById(R.id.overrideSave_button);
        entryDescription = findViewById(R.id.editJournalEntryText);

        /*
        GET THE USER
        String user = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        INITIALIZE THE DATABASE TO REFERENCE THE USER
        databaseReference = FirebaseDatabase.getInstance().getReference(user);
        */

        //Valid entry has been selected
        if (getIntent().hasExtra("selectedEntry")) {

            Journal editJournalEntry = getIntent().getParcelableExtra("selectedEntry");
            journalTitle.setText(editJournalEntry.getJournalTitle());
            entryDescription.setText(editJournalEntry.getJournalDescription());

            //Save provided information
            saveButton.setOnClickListener(view -> {
                //Get Date
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date today = new Date(System.currentTimeMillis());

                //Create, set, and store EACH STRING AND THEN I CAN PUT INTO MY JOURNAL OBJECT
                //Get the values
                String title = journalTitle.getText().toString();
                Log.e("UPDATED journal title: ", title);
                String description = entryDescription.getText().toString();
                Log.e("UPDATED journal description: ", description);
                String uniqueID = editJournalEntry.getJournalID();

                //Set the Object values
                editJournalEntry.setJournalTitle(title);
                editJournalEntry.setJournalDescription(description);
                //editedJournalEntry.setJournalID(uniqueID);
                editJournalEntry.setJournalDate(formatter.format(today));

                Log.e("SAVED BUTTON -- OBJECT TITLE", editJournalEntry.getJournalTitle());
                Log.e("SAVED BUTTON -- OBJECT DESCRIP", editJournalEntry.getJournalDescription());
                Log.e("SAVED BUTTON -- OBJECT DATE", editJournalEntry.getJournalDate());

                Log.e("SAVED BUTTON -- OBJECT ID", editJournalEntry.getJournalID());

                //Set values in DB
                //TODO: DB is not updating, but all of the values are correctly updated before this line
                databaseReference.child("Journal").child(uniqueID).setValue(editJournalEntry);

                //Close Activity
                finish();

            });
        }
    }

    public void onEditDeleteClick(View view) {
        finish();
    }

}