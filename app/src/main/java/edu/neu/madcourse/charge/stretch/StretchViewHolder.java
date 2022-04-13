package edu.neu.madcourse.charge.stretch;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.neu.madcourse.charge.R;

public class StretchViewHolder extends RecyclerView.ViewHolder {
    protected TextView videoTitle;
    protected TextView videoDescription;

    public StretchViewHolder(View itemView, final StretchClickListener stretchClickListener) {
        super(itemView);
        videoTitle = itemView.findViewById(R.id.video_title);
//        videoDescription = itemView.findViewById(R.id.video_description);
        itemView.setOnClickListener(v -> stretchClickListener.onStretchClick(getLayoutPosition()));
    }

}
