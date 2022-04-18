package edu.neu.madcourse.charge.stretch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Vector;

import edu.neu.madcourse.charge.R;

public class StretchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretch);
        recyclerView = findViewById(R.id.stretch_recycler_view);
    }
}