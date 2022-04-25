package edu.neu.madcourse.charge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class JournalingActivity extends AppCompatActivity implements Serializable {
    RecyclerView journalRecyclerView;
    private JournalRecyclerAdapter journalRecyclerAdapter;
    public final ArrayList<Journal> journalEntries = new ArrayList<>();
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

        //Create new Journal Entry and save to DB
        addEntry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Firebase DB: Retrieve updated data for user's journal entries

                //db.child('journal').child(new_key).setValue...
                //...setValue(new Journal(title, entry, id))
                //-key
                //--- journal title
                //       --- journal entry
                //       --- journal id
                //journal id = new_key


                //TITLE: ENTRY 1 --> LIST = ENTRY 1
                //TITLE: ENTRY 2  --> LIST = ENTRY 1, ENTRY 1, ENTRY 2

                createNewEntry();

                //DB and adapter are updated with the new entry
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        journalEntries.clear();
                        Log.e("Size of journal entries", String.valueOf(journalEntries.size()));
                        for (DataSnapshot snap : snapshot.child("Journal").getChildren()) {
                            Journal journal = snap.getValue(Journal.class);
                            journalEntries.add(journal);
                        }
                        Log.e("Updated size of journal entries", String.valueOf(journalEntries.size()));
                        journalRecyclerAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        //TODO: Actions -- Edit or Delete Entry -- PT 2
        //If Swipe Left,
//        editEntry();

        //if Swipe Right
            //Delete entry

    }

    /**
     * Opens a new Journal Entry
     */
    public void createNewEntry() {
        startActivity(new Intent(JournalingActivity.this, NewJournalEntry.class));
    }

    /**
     * Opens an already existing Journal Entry
     */
    public void editEntry() {
        //Source: https://www.youtube.com/watch?v=OUZcjZkJrvY
        Intent intent = new Intent(JournalingActivity.this, EditJournalActivity.class);
        intent.putExtra("entries_list", (Serializable) journalEntries);
        startActivity(intent);
    }

}