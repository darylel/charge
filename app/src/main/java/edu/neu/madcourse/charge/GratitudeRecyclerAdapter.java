package edu.neu.madcourse.charge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GratitudeRecyclerAdapter extends RecyclerView.Adapter<GratitudeViewHolder> {
    private final ArrayList<Gratitude> gratitudeItems;
    private final OnGratitudeClickListener onGratitudeClickListener;

    public GratitudeRecyclerAdapter(ArrayList<Gratitude> gratitudeItems, OnGratitudeClickListener onGratitudeClickListener) {
        this.gratitudeItems = gratitudeItems;
        this.onGratitudeClickListener = onGratitudeClickListener;
    }

    @NonNull
    @Override
    public GratitudeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.gratitude_card, parent, false);

        return new GratitudeViewHolder(view, onGratitudeClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GratitudeViewHolder holder, int position) {
        Gratitude currentGratitude = gratitudeItems.get(position);

        // Set list values to the view
        holder.gratitudeItem.setText(currentGratitude.getItem());
    }

    @Override
    public int getItemCount() {
        return gratitudeItems.size();
    }
}
