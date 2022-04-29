package edu.neu.madcourse.charge;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.neu.madcourse.charge.R;

public class MoveHolder extends RecyclerView.ViewHolder {
    protected TextView stretchTextView;
    protected ImageView stretchImageView;

    public MoveHolder(View itemView) {
        super(itemView);
        stretchTextView = itemView.findViewById(R.id.move_textview);
        stretchImageView = itemView.findViewById(R.id.move_imageview);
    }

}
