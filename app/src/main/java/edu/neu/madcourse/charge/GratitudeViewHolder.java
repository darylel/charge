package edu.neu.madcourse.charge;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class GratitudeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final OnGratitudeClickListener onGratitudeClickListener;
    protected TextView gratitudeItem;

    public GratitudeViewHolder(@NonNull View itemView, OnGratitudeClickListener onGratitudeClickListener) {
        super(itemView);
        this.onGratitudeClickListener = onGratitudeClickListener;

        gratitudeItem = itemView.findViewById(R.id.textViewGratitudeItem);
    }

    @Override
    public void onClick(View view) {
        onGratitudeClickListener.onGratitudeClick(getAdapterPosition());
    }
}
