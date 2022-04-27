package edu.neu.madcourse.charge.movement;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.neu.madcourse.charge.R;

public class MovementHolder extends RecyclerView.ViewHolder {
    protected TextView stretchTextView;
    protected ImageView stretchImageView;

    public MovementHolder(View itemView) {
        super(itemView);
        stretchTextView = itemView.findViewById(R.id.stretch_textview);
        stretchImageView = itemView.findViewById(R.id.stretch_imageview);
    }

}
