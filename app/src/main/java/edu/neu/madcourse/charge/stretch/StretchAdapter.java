package edu.neu.madcourse.charge.stretch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import edu.neu.madcourse.charge.R;

public class StretchAdapter extends RecyclerView.Adapter<StretchHolder> {

    private ArrayList<StretchVideo> stretchVideoVideoList;
    private Context context;

    public StretchAdapter() {}

    public StretchAdapter(ArrayList<StretchVideo> stretchVideoVideoList, Context context) {
        this.stretchVideoVideoList = stretchVideoVideoList;
        this.context = context;
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
    }

    @Override
    public int getItemCount() {
        return stretchVideoVideoList.size();
    }
}
