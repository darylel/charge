package edu.neu.madcourse.charge.movement;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.neu.madcourse.charge.R;

public class MovementAdapter extends RecyclerView.Adapter<MovementHolder> {

    private final ArrayList<MovementVideo> movementVideoList;
    private final View view;
    private final String videoId = "videoId";

    public MovementAdapter(ArrayList<MovementVideo> movementVideoList, View view) {
        this.movementVideoList = movementVideoList;
        this.view = view;
    }

    @NonNull
    @Override
    public MovementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stretch_video_layout, parent, false);
        MovementHolder movementHolder = new MovementHolder(view);
        return movementHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovementHolder holder, int position) {
        MovementVideo currentMovementVideo = movementVideoList.get(position);
        holder.stretchTextView.setText(currentMovementVideo.getTitle());
        Picasso.get().load(currentMovementVideo.getLink()).into(holder.stretchImageView);
        holder.stretchImageView.setOnClickListener(v -> {
            MovementVideo movementVideo = movementVideoList.get(position);
            Intent videoIntent = new Intent(view.getContext(), YouTube.class).putExtra(videoId, movementVideo.getVideo());
            view.getContext().startActivity(videoIntent);
        });
    }

    @Override
    public int getItemCount() {
        return movementVideoList.size();
    }
}
