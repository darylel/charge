package edu.neu.madcourse.charge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class JournalingActivity extends AppCompatActivity {
    RecyclerView journalRecyclerView;
    private JournalRecyclerAdapter journalRecyclerAdapter;
    private final ArrayList<Journal> journalEntries = new ArrayList<>();
    private DatabaseReference databaseReference;
    String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journaling);

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

        //RecyclerView, Adapter, and LayoutManager setup
        journalRecyclerView = findViewById(R.id.journalRecyclerView);
        journalRecyclerAdapter = new JournalRecyclerAdapter(journalEntries);
        journalRecyclerView.setHasFixedSize(true);
        journalRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        journalRecyclerView.setAdapter(journalRecyclerAdapter);

        //TODO: Update Journal Entry in UI
        //TODO: Update Journal Entry in DB
        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Firebase DB: Retrieve updated data for user's journal entries
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snap : snapshot.child("Journal").getChildren()) {
                            Journal journal = snap.getValue(Journal.class);
                            journalEntries.add(journal);
                        }
                        journalRecyclerAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                startActivity(new Intent(JournalingActivity.this, NewJournalEntry.class));

            }
        });

    }

}