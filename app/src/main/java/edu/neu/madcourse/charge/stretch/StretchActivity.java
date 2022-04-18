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

//        Vector<StretchVideo> stretchVideos = new Vector<>();
//        setContentView(R.layout.activity_stretch);
//        RecyclerView recyclerView = findViewById(R.id.stretch_recycler_view);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        stretchVideos.add(new StretchVideo());
//
//        // Here is where the Firebase request will come in
//        stretchVideos.add(new StretchVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/g_tea8ZNk5A\" frameborder=\"0\" allowfullscreen></iframe>"));
//        stretchVideos.add(new StretchVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/UIRTPXj1Q1U\" frameborder=\"0\" allowfullscreen></iframe>"));
//        stretchVideos.add(new StretchVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/qULTwquOuT4\" frameborder=\"0\" allowfullscreen></iframe>"));
//        StretchAdaptor stretchAdaptor = new StretchAdaptor(stretchVideos);
//        recyclerView.setAdapter(stretchAdaptor); //
    }
}