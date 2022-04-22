package edu.neu.madcourse.charge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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

        //Update Journal Entry in DB

        //STEP 1: When FAB is clicked, I need to pass the journalEntries ArrayList to my newJournalEntry Activity
        //STEP 2: In the newJournalEntryActivity I will loop through the list of Journal objects, and see if the journal ID exists
        //STEP 3: In the newJournalEntryActivity, I will generate an ID (if not there), and set the values for each Journal object

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
                //Source: https://www.youtube.com/watch?v=OUZcjZkJrvY
//                Intent intent = new Intent(JournalingActivity.this, NewJournalEntry.class);
//                intent.putParcelableArrayListExtra("entries", (ArrayList<? extends Parcelable>) journalEntries);
//                startActivity(intent);
                startActivity(new Intent(JournalingActivity.this, NewJournalEntry.class));
            }
        });

    }

}