package edu.neu.madcourse.charge.stretch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import edu.neu.madcourse.charge.R;

public class StretchActivity extends AppCompatActivity {

    private List<Stretch> stretchList;
    private RecyclerView recyclerView;
    private StretchRecyclerAdapter stretchRecyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretch_recyclerview);
    }


}