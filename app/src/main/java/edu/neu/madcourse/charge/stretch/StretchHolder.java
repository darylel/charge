package edu.neu.madcourse.charge.stretch;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.neu.madcourse.charge.R;

public class StretchHolder extends RecyclerView.ViewHolder {
//    protected WebView stretchWebView;
    protected TextView textView;
    protected ImageView imageView;

    public StretchHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.stretch_textview);
        imageView = itemView.findViewById(R.id.stretch_imageview);
//        stretchWebView = itemView.findViewById(R.id.stretch_imageview);
////        itemView.setOnClickListener(v -> stretchClickListener.onStretchClick(getLayoutPosition()));
//        stretchWebView.getSettings().setJavaScriptEnabled(true);
//        stretchWebView.setWebChromeClient(new WebChromeClient());
    }

}
