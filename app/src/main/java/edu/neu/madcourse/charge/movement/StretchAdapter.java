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

public class StretchAdapter extends RecyclerView.Adapter<StretchHolder> {

    private final ArrayList<StretchVideo> stretchVideoList;
    private final View view;
    private final String videoId = "videoId";

    public StretchAdapter(ArrayList<StretchVideo> stretchVideoList, View view) {
        this.stretchVideoList = stretchVideoList;
        this.view = view;
    }

    @NonNull
    @Override
    public StretchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stretch_video_layout, parent, false);
        StretchHolder stretchHolder = new StretchHolder(view);
        return stretchHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StretchHolder holder, int position) {
        StretchVideo currentStretchVideo = stretchVideoList.get(position);
        holder.stretchTextView.setText(currentStretchVideo.getTitle());
        Picasso.get().load(currentStretchVideo.getLink()).into(holder.stretchImageView);
        holder.stretchImageView.setOnClickListener(v -> {
            StretchVideo stretchVideo = stretchVideoList.get(position);
            Intent videoIntent = new Intent(view.getContext(), YouTube.class).putExtra(videoId, stretchVideo.getVideo());
            view.getContext().startActivity(videoIntent);
        });
    }

    @Override
    public int getItemCount() {
        return stretchVideoList.size();
    }
}
