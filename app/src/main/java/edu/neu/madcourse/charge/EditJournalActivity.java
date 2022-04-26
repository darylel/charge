package edu.neu.madcourse.charge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This class represents a populated entry that is to be edited by the user
 */
public class EditJournalActivity extends AppCompatActivity {
    Button saveButton;
    TextView journalTitle, entryDescription;

    private static final String TAG = "EditJournalActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_journal);

        //Items (Save Button, Title, Description)
        journalTitle = findViewById(R.id.journal_title);
        saveButton = findViewById(R.id.overrideSave_button);
        entryDescription = findViewById(R.id.journalEntryText);

        //Valid entry has been selected
        if (getIntent().hasExtra("selectedEntry")) {
            Journal editJournalEntry = getIntent().getParcelableExtra("selectedEntry");
            journalTitle.setText(editJournalEntry.getJournalTitle());
            entryDescription.setText(editJournalEntry.getJournalDescription());
            Log.d(TAG,"onCreate: " + editJournalEntry.toString());
        }



        //TODO: Set the TextViews to the values of the current Journal object

        //TODO: for loop in journalEntries ArrayList and look to see if ID exists


    }

}