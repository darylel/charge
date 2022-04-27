package edu.neu.madcourse.charge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
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

public class JournalingActivity extends AppCompatActivity implements JournalRecyclerAdapter.OnJournalListener {
    RecyclerView journalRecyclerView;
    private JournalRecyclerAdapter journalRecyclerAdapter;
    public final ArrayList<Journal> journalEntries = new ArrayList<>();
    private DatabaseReference databaseReference;
    FloatingActionButton addEntry;
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
        addEntry = findViewById(R.id.fabAddEntry);

        //RecyclerView, Adapter, and LayoutManager setup
        journalRecyclerView = findViewById(R.id.journalRecyclerView);
        journalRecyclerAdapter = new JournalRecyclerAdapter(journalEntries, this);
        journalRecyclerView.setHasFixedSize(true);
        journalRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(journalRecyclerView);
        journalRecyclerView.setAdapter(journalRecyclerAdapter);

        //Initialize list of journal entries
        initializeEventListener();

        //Create new Journal Entry and save to DB
        addEntry.setOnClickListener(view -> createNewEntry());

        //Update the UI with newly added journal entry
        updateUI();

    }

    /**
     * Initializes the list of journal entries that exist in DB
     */
    private void initializeEventListener() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
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

    /**
     * Updates list after new Journal entry is added
     */
    private void updateUI() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                journalEntries.clear();
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
    }

    //Swipe Left to delete Journal Entry
    ItemTouchHelper.SimpleCallback itemTouchHelper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getBindingAdapterPosition();

            Journal deletedEntry = journalEntries.get(position);
            Log.e("Entry is at position", String.valueOf(position));
            Log.e("List size BEFORE deletion", String.valueOf(journalEntries.size()));
            databaseReference.child("Journal").child(deletedEntry.getJournalID()).removeValue();
            journalEntries.remove(position);
            journalRecyclerAdapter.notifyItemRemoved(position);
            Log.e("Recycler position is", String.valueOf(position));
            Log.e("List size AFTER deletion", String.valueOf(journalEntries.size()));


        }
    };

    /**
     * Opens a new Journal Entry
     */
    public void createNewEntry() {
        startActivity(new Intent(this, NewJournalEntry.class));
    }

    /**
     * Opens an already existing Journal Entry
     */
    @Override
    public void onJournalClick(int position) {
        journalEntries.get(position);
        Log.e("Value of object", journalEntries.get(position).toString());
        Log.e("JournalingActivity: Journal Title is ", journalEntries.get(position).getJournalTitle());
        Log.e("JournalingActivity: Journal Descrip is ", journalEntries.get(position).getJournalDescription());
        Intent intent = new Intent(JournalingActivity.this, EditJournalActivity.class);
        intent.putExtra("selectedEntry", journalEntries.get(position));
        startActivity(intent);
    }
}