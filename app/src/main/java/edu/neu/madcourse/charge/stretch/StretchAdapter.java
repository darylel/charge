package edu.neu.madcourse.charge.stretch;
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

    private ArrayList<StretchVideo> stretchVideoVideoList;
    private View view;
    private String videoId = "videoId";

    public StretchAdapter() {}

    public StretchAdapter(ArrayList<StretchVideo> stretchVideoVideoList, View view) {
        this.stretchVideoVideoList = stretchVideoVideoList;
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
        StretchVideo currentStretchVideo = stretchVideoVideoList.get(position);
        holder.stretchTextView.setText(currentStretchVideo.getTitle());
        Picasso.get().load(currentStretchVideo.getLink()).into(holder.stretchImageView);
        holder.stretchImageView.setOnClickListener(v -> {
            StretchVideo stretchVideo = stretchVideoVideoList.get(position);
            Intent videoIntent = new Intent(view.getContext(), YouTube.class).putExtra(videoId, stretchVideo.getVideo());
            view.getContext().startActivity(videoIntent);
        });
    }

    @Override
    public int getItemCount() {
        return stretchVideoVideoList.size();
    }
}
