package edu.neu.madcourse.charge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewJournalEntry extends AppCompatActivity {
    Button saveButton;
    TextView journalTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_journal_entry);

        journalTitle = findViewById(R.id.journal_title);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Write to the database
                //Add to the list
                //Notify the adapter that data has changed
                finish();
            }
        });
    }


    public void onDeleteClick(View view) {
        finish();
    }
}