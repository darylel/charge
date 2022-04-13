package edu.neu.madcourse.charge.stretch;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull StretchViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
