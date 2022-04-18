package edu.neu.madcourse.charge.stretch;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.neu.madcourse.charge.R;

public class StretchHolder extends RecyclerView.ViewHolder {
//    protected WebView stretchWebView;
    protected TextView stretchTextView;
    protected ImageView stretchImageView;

    public StretchHolder(View itemView) {
        super(itemView);
        stretchTextView = itemView.findViewById(R.id.stretch_textview);
        stretchImageView = itemView.findViewById(R.id.stretch_imageview);
//        stretchWebView = itemView.findViewById(R.id.stretch_imageview);
////        itemView.setOnClickListener(v -> stretchClickListener.onStretchClick(getLayoutPosition()));
//        stretchWebView.getSettings().setJavaScriptEnabled(true);
//        stretchWebView.setWebChromeClient(new WebChromeClient());
    }

}
