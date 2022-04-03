package edu.neu.madcourse.charge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class GratitudeActivity extends AppCompatActivity implements OnGratitudeClickListener {
    private RecyclerView gratitudeRecyclerView;
    private GratitudeRecyclerAdapter gratitudeRecyclerAdapter;
    private ArrayList<Gratitude> gratitudeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gratitude);

        // Set the custom app bar view
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_custom);

        TextView toolbar = findViewById(R.id.custom_toolbar);
        toolbar.setText("Gratitude List");

        // Test data to be deleted
        gratitudeList.add(new Gratitude("you"));
        gratitudeList.add(new Gratitude("family"));

        gratitudeRecyclerView = findViewById(R.id.recyclerViewGratitude);
        gratitudeRecyclerAdapter = new GratitudeRecyclerAdapter(gratitudeList, this);
        gratitudeRecyclerView.setHasFixedSize(true);

        gratitudeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(gratitudeRecyclerView);
        gratitudeRecyclerView.setAdapter(gratitudeRecyclerAdapter);
    }

    @Override
    public void onGratitudeClick(int position) {

    }

    ItemTouchHelper.SimpleCallback itemTouchHelper = new ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END,
            ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int startMove = viewHolder.getAdapterPosition();
            int endMove = target.getAdapterPosition();

            Collections.swap(gratitudeList, startMove, endMove);

            Objects.requireNonNull(recyclerView.getAdapter()).notifyItemMoved(startMove, endMove);

            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            String btnMessage = "Undo";
            String itemDelete = "Item deleted";
            Gratitude deletedItem = gratitudeList.get(position);

            if (direction == ItemTouchHelper.LEFT) {
                gratitudeRecyclerAdapter.notifyDataSetChanged();
                // FIX THIS with a re-record option
                // editDialog(position);
            }

            if (direction == ItemTouchHelper.RIGHT) {
                gratitudeList.remove(position);
                gratitudeRecyclerAdapter.notifyItemRemoved(position);

                Snackbar undoDelete = Snackbar.make(findViewById(R.id.recyclerViewGratitude), itemDelete, Snackbar.LENGTH_SHORT);
                undoDelete.setAction(btnMessage, view -> {
                    gratitudeList.add(position, deletedItem);
                    gratitudeRecyclerAdapter.notifyItemInserted(position);
                });
                undoDelete.show();
            }
        }
    };
}