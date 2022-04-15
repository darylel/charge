package edu.neu.madcourse.charge.stretch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import edu.neu.madcourse.charge.R;

public class StretchActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vector<StretchVideo> stretchVideos = new Vector<>();
        setContentView(R.layout.activity_stretch_recyclerview);
        RecyclerView recyclerView = findViewById(R.id.stretch_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        stretchVideos.add(new StretchVideo());

        // Here is where the Firebase request will come in
        stretchVideos.add(new StretchVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/MVLQm9zr_uE\" frameborder=\"0\" allowfullscreen></iframe>"));
        stretchVideos.add(new StretchVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/veLQ2cdyBBo\" frameborder=\"0\" allowfullscreen></iframe>"));
        StretchAdaptor stretchAdaptor = new StretchAdaptor(stretchVideos);
        recyclerView.setAdapter(stretchAdaptor); //
    }
}