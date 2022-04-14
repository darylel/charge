package edu.neu.madcourse.charge.stretch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.neu.madcourse.charge.R;

public class StretchRecyclerAdapter extends RecyclerView.Adapter<StretchViewHolder> {

    private final ArrayList<Stretch> stretchList;
    private StretchClickListener stretchClickListener;

    public StretchRecyclerAdapter(ArrayList<Stretch> stretchList) {
        this.stretchList = stretchList;
    }

    public void setStretchClickListener(StretchClickListener stretchClickListener) {
        this.stretchClickListener = stretchClickListener;
    }

    @NonNull
    @Override
    public StretchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stretch_layout, parent, false);
        return new StretchViewHolder(view, stretchClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StretchViewHolder holder, int position) {
        Stretch currentStretch = stretchList.get(position);
        holder.videoTitle.setText(currentStretch.getTitle());
    }

    @Override
    public int getItemCount() {
        return stretchList.size();
    }
}
