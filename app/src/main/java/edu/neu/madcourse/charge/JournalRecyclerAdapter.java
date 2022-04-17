package edu.neu.madcourse.charge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        //individual row to display items in RecyclerView
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.journal_entry_card, parent, false);

        //Create instance of journalViewHolder class
        return new JournalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JournalViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        //Number of rows in RecyclerView
        //TODO: reset back to 0 --> set to 1 to test aesthetics
        return 1;
    }


    //TODO: Display current date and time with Calendar instance https://www.youtube.com/watch?v=Le47R9H3qow
    //TODO: Display Journal title from saved entry
    class JournalViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView entryTitle, entryDate;

        public JournalViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.journal_image_view);
            entryTitle = itemView.findViewById(R.id.journal_title);
            entryDate = itemView.findViewById(R.id.journalEntryDate);
        }
    }
}
