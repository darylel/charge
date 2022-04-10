package edu.neu.madcourse.charge;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class GratitudeActivity extends AppCompatActivity implements OnGratitudeClickListener {
    private GratitudeRecyclerAdapter gratitudeRecyclerAdapter;
    private final ArrayList<Gratitude> gratitudeList = new ArrayList<>();
    private String user;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gratitude);

        // Set the custom app bar view
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_custom);

        TextView toolbar = findViewById(R.id.custom_toolbar);
        toolbar.setText(R.string.gratitude_list);

        if(ContextCompat.checkSelfPermission(GratitudeActivity.this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(GratitudeActivity.this, new String[] {
                        Manifest.permission.RECORD_AUDIO
                    }, 100);
                }

        FirebaseAuth auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();
        user = Objects.requireNonNull(auth.getCurrentUser()).getUid();

        FloatingActionButton addItem = findViewById(R.id.fabAddItem);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData()!=null) {
                        ArrayList<String> d=result.getData().getStringArrayListExtra(
                                RecognizerIntent.EXTRA_RESULTS);
                        String newKey = db.child("gratitude").child("user").push().getKey();
                        db.child("gratitude").child(user).child(newKey).setValue(new Gratitude(d.get(0)));
                        Gratitude gratitude = new Gratitude(d.get(0));
                        gratitude.setKey(newKey);
                        gratitudeList.add(gratitude);
                        gratitudeRecyclerAdapter.notifyItemInserted(gratitudeList.size()-1);
                    }
                });

        addItem.setOnClickListener(view -> {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say what you're grateful for");
            activityResultLauncher.launch(intent);
        });

        RecyclerView gratitudeRecyclerView = findViewById(R.id.recyclerViewGratitude);
        gratitudeRecyclerAdapter = new GratitudeRecyclerAdapter(gratitudeList, this);
        gratitudeRecyclerView.setHasFixedSize(true);

        gratitudeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(gratitudeRecyclerView);
        gratitudeRecyclerView.setAdapter(gratitudeRecyclerAdapter);

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.child("gratitude").child(user).getChildren()) {
                    Gratitude gratitude = new Gratitude(Objects.requireNonNull(snap.child("item")
                            .getValue()).toString());
                    gratitude.setKey(snap.getKey());
                    gratitudeList.add(gratitude);
                }
                gratitudeRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onGratitudeClick(int position) {

    }

    ItemTouchHelper.SimpleCallback itemTouchHelper = new ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END,
            ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int startMove = viewHolder.getBindingAdapterPosition();
            int endMove = target.getBindingAdapterPosition();
            Gratitude first = gratitudeList.get(startMove);
            Gratitude second = gratitudeList.get(endMove);

            Log.i("INFO/Swap 1", String.valueOf(startMove));
            Log.i("INFO/Swap 1", String.valueOf(endMove));
            // FIX THIS SWAP LOGIC FOR THE DATABASE
            //db.child("gratitude").child(user).child(first.getKeyGratitude()).child("item").setValue(second.getItem());
            //db.child("gratitude").child(user).child(second.getKeyGratitude()).child("item").setValue(first.getItem());

            Collections.swap(gratitudeList, startMove, endMove);

            Objects.requireNonNull(recyclerView.getAdapter()).notifyItemMoved(startMove, endMove);

            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getBindingAdapterPosition();
            String btnMessage = "Undo";
            String itemDelete = "Item deleted";
            Gratitude deletedItem = gratitudeList.get(position);

            if (direction == ItemTouchHelper.LEFT) {
                gratitudeRecyclerAdapter.notifyDataSetChanged();
                // FIX THIS with a re-record option
                // editDialog(position);
            }

            if (direction == ItemTouchHelper.RIGHT) {
                Gratitude deletedGratitude = gratitudeList.get(position);
                Log.i("INFO/delete", deletedGratitude.getItem());
                gratitudeList.remove(position);
                gratitudeRecyclerAdapter.notifyItemRemoved(position);
                db.child("gratitude").child(user).child(deletedGratitude.getKeyGratitude()).removeValue();

                Snackbar undoDelete = Snackbar.make(findViewById(R.id.recyclerViewGratitude),
                        itemDelete, Snackbar.LENGTH_SHORT);
                undoDelete.setAction(btnMessage, view -> {
                    gratitudeList.add(position, deletedItem);
                    gratitudeRecyclerAdapter.notifyItemInserted(position);
                    db.child("gratitude").child(user).child(deletedGratitude.getKeyGratitude())
                            .child("item").setValue(deletedGratitude.getItem());
                });
                undoDelete.show();
            }
        }
    };
}