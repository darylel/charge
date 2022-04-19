package edu.neu.madcourse.charge.stretch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import edu.neu.madcourse.charge.R;

public class StretchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretch);
        RecyclerView stretchRecyclerView = findViewById(R.id.stretch_recycler_view);
        ArrayList<StretchVideo> stretchVideoArrayList = new ArrayList<>();
        StretchAdapter stretchAdapter = new StretchAdapter(stretchVideoArrayList, StretchActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        stretchRecyclerView.setLayoutManager(layoutManager);
        stretchRecyclerView.setAdapter(stretchAdapter);
    }

    private void getVideo() {

    }
}