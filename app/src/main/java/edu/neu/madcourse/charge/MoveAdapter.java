package edu.neu.madcourse.charge;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

// Referenced: https://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library
// https://square.github.io/picasso/

public class MoveAdapter extends RecyclerView.Adapter<MoveHolder> {

    private final ArrayList<MoveVideo> moveVideoList;
    private final View view;
    private final String videoId = "videoId";

    public MoveAdapter(ArrayList<MoveVideo> moveVideoList, View view) {
        this.moveVideoList = moveVideoList;
        this.view = view;
    }

    @NonNull
    @Override
    public MoveHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.move_video_layout, parent, false);
        MoveHolder moveHolder = new MoveHolder(view);
        return moveHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoveHolder holder, int position) {
        MoveVideo currentMoveVideo = moveVideoList.get(position);
        holder.stretchTextView.setText(currentMoveVideo.getTitle());
        Picasso.get().load(currentMoveVideo.getLink()).into(holder.stretchImageView);
        holder.stretchImageView.setOnClickListener(v -> {
            MoveVideo moveVideo = moveVideoList.get(position);
            Intent videoIntent = new Intent(view.getContext(), YouTube.class).putExtra(videoId, moveVideo.getVideo());
            view.getContext().startActivity(videoIntent);
        });
    }

    @Override
    public int getItemCount() {
        return moveVideoList.size();
    }
}
