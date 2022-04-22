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
        saveButton = findViewById(R.id.saveEntry_button);

        //TODO: Create condition to check for unique ID
            //for loop in journal entries and look for ID
            //If it does find it,
                //db.child('journal').child('key').getValue('title')

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Write to the database
                //TODO: if Key != null vs key == null -->>>> writes to the DB
                    //if key == null{
                        //generate a key}
                    //Continue --> set the values
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