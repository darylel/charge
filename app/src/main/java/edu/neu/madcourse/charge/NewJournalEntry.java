package edu.neu.madcourse.charge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class NewJournalEntry extends AppCompatActivity {
    Button saveButton;
    TextView journalTitle;
    String keyExists = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_journal_entry);

        journalTitle = findViewById(R.id.journal_title);
        saveButton = findViewById(R.id.saveEntry_button);

        //TODO: Create condition to check for journalID
        //TODO: Should I create a separate EditJournalActivity? Feels like it may be easier to have 2 diff activities that focus on one thing
        Intent intent = getIntent();
        ArrayList<Journal> entries = (ArrayList<Journal>) intent.getSerializableExtra("entries_list");

        for (Journal journals : entries){
        }

            //for loop in journalEntries ArrayList and look to see if ID exists
            //If it does find it, store the key in keyExists
            //db.child('journal').child('key').getValue('title')

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: Write to the database
                    //if keyExists == null{
                    //generate a key and initialize setJournalID}

                    //Continue --> set the values of the Journal object

                    //Example : Generate the key and set the values of the Journal object
                    // String newKey = db.child("gratitude").push().getKey();
                    // db.child("gratitude").child(newKey).setValue(new Gratitude(d.get(0)));
                    finish();
                }
            });
    }


    public void onDeleteClick(View view) {
        finish();
    }
}