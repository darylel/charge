package edu.neu.madcourse.charge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class JournalingActivity extends AppCompatActivity {
    RecyclerView journalRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journaling);

        journalRecyclerView = findViewById(R.id.journalRecyclerView);
        JournalRecyclerAdapter journalRecyclerAdapter = new JournalRecyclerAdapter();
        journalRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        journalRecyclerView.setAdapter(journalRecyclerAdapter);


    }
}