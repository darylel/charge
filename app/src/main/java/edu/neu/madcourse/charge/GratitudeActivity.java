package edu.neu.madcourse.charge;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class GratitudeActivity extends AppCompatActivity implements OnGratitudeClickListener {
    private RecyclerView gratitudeRecyclerView;
    private GratitudeRecyclerAdapter gratitudeRecyclerAdapter;
    private FloatingActionButton addItem;
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

        addItem = findViewById(R.id.fabAddItem);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK && result.getData()!=null) {
                            ArrayList<String> d=result.getData().getStringArrayListExtra(
                                    RecognizerIntent.EXTRA_RESULTS);
                            gratitudeList.add(new Gratitude(d.get(0)));
                            gratitudeRecyclerAdapter.notifyItemInserted(gratitudeList.size()-1);
                        }
                    }
                });

        addItem.setOnClickListener(view -> {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say what you're grateful for");
            activityResultLauncher.launch(intent);
        });

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