package edu.neu.madcourse.charge;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class JournalingActivity extends AppCompatActivity {
    RecyclerView journalRecyclerView;
    private DatabaseReference databaseReference;
    Button deleteEntry;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journaling);

        //RecyclerView and LayoutManager setup
        journalRecyclerView = findViewById(R.id.journalRecyclerView);
        JournalRecyclerAdapter journalRecyclerAdapter = new JournalRecyclerAdapter();
        journalRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        journalRecyclerView.setAdapter(journalRecyclerAdapter);

        //Customize app bar view
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_custom);
        TextView toolbar = findViewById(R.id.custom_toolbar);
        toolbar.setText(R.string.MyJournal);

        //Retrieve user information
        FirebaseAuth auth = FirebaseAuth.getInstance();
        user = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference(user);

        //FAB Button to add new journal entry (new activity)
        FloatingActionButton addEntry = findViewById(R.id.fabAddEntry);


        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JournalingActivity.this, NewJournalEntry.class));
            }
        });
    }

}