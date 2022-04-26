package edu.neu.madcourse.charge;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;

/**
 * Responsible for displaying items in the Journal RecyclerView
 * Creates the rows and will map the items inside list to the created rows
 */
public class JournalRecyclerAdapter extends RecyclerView.Adapter<JournalRecyclerAdapter.JournalViewHolder> {
    private final ArrayList<Journal> journalEntries;

    public JournalRecyclerAdapter(ArrayList<Journal> journalEntries) {
        this.journalEntries = journalEntries;
    }

    @NonNull
    @Override
    public JournalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //individual row to display items in RecyclerView
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.journal_entry_card, parent, false);

        //Create instance of journalViewHolder class
        return new JournalViewHolder(view);
    }

    //onBindViewHolder called for each row in RecyclerView
    //Map data for each individual row
    @Override
    public void onBindViewHolder(@NonNull JournalViewHolder holder, int position) {

        Journal currentJournalEntry = journalEntries.get(position);
        //TODO: Set textView -- entryTitle from list
        holder.entryTitle.setText(currentJournalEntry.getJournalTitle());
        Log.e("Journal Title is", holder.entryTitle.toString());

        holder.entryDate.setText(currentJournalEntry.getJournalDate());
        Log.e("Journal Date is", holder.entryDate.toString());

    }

    @Override
    public int getItemCount() {
        return journalEntries.size();
    }

    class JournalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView entryTitle, entryDate, entryDescription;

        public JournalViewHolder(@NonNull View itemView) {
            super(itemView);

            //Journal Items
            imageView = (ImageView) itemView.findViewById(R.id.journal_image_view);
            entryTitle = itemView.findViewById(R.id.journal_title);
            entryDate = itemView.findViewById(R.id.journalEntryDate);
            entryDescription = itemView.findViewById(R.id.journalEntryText);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
