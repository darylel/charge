package edu.neu.madcourse.charge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class EditJournalActivity extends AppCompatActivity {
    Button saveButton;
    TextView journalTitle, entryDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_journal);

        //Items (Save Button, Title, Description)
        journalTitle = findViewById(R.id.journal_title);
        saveButton = findViewById(R.id.saveEntry_button);
        entryDescription = findViewById(R.id.journalEntryText);

        //TODO: Set the TextViews to the values of the current Journal object


        //TODO: Create condition to check for journalID
        //TODO: for loop in journalEntries ArrayList and look to see if ID exists
        ////db.child('journal').child('key').getValue('title')

//        Intent intent = getIntent();
//        ArrayList<Journal> entries = (ArrayList<Journal>) intent.getSerializableExtra("entries_list");
//
//        for (Journal journals : entries) {
//        }
    }


    //JournalTitle
}