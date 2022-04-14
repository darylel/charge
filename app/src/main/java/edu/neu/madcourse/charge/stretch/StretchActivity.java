package edu.neu.madcourse.charge.stretch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
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
        stretchList = new ArrayList<>();
        setContentView(R.layout.activity_stretch_recyclerview);

        initialize(savedInstanceState);

        ItemTouchHelper stretchVideoTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(StretchActivity.this, "Delete a video", Toast.LENGTH_SHORT).show();
                int videoPosition = viewHolder.getLayoutPosition();
                stretchList.remove(videoPosition);
                stretchRecyclerAdapter.notifyItemRemoved(videoPosition);
            }
        });
        stretchVideoTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle cardView) {
        int size;

        if (stretchList == null) {
            size = 0;
        } else {
            size = stretchList.size();
        }

        for (int i = 0; i < size; i++) {
            cardView.putString(KEY_OF_INSTANCE + i + "0", stretchList.get(i).getTitle());
        }
        super.onSaveInstanceState(cardView);
    }

    private void initialize(Bundle savedInstanceState) {
//        initialStretchData(savedInstanceState);
//        createRecyclerView();
    }



}