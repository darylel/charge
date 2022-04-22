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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_journal_entry);

        journalTitle = findViewById(R.id.journal_title);
        saveButton = findViewById(R.id.saveEntry_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Write to the database
                //STEP 1: Generate  new key and store
                //STEP 2: setValue for Title, Description, and Date

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