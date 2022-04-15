package edu.neu.madcourse.charge;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Responsible for displaying items in the Journal RecyclerView
 * Creates the rows and will map the items inside list to the created rows
 */
public class JournalRecyclerAdapter extends RecyclerView.Adapter<JournalRecyclerAdapter.JournalViewHolder> {

    @NonNull
    @Override
    public JournalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull JournalViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        //Number of rows in RecyclerView
        return 0;
    }

    class JournalViewHolder extends RecyclerView.ViewHolder{
        public JournalViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
