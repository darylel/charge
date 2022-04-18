package edu.neu.madcourse.charge.stretch;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.recyclerview.widget.RecyclerView;

import edu.neu.madcourse.charge.R;

public class StretchHolder extends RecyclerView.ViewHolder {
    protected WebView stretchWebView;

    public StretchHolder(View itemView, final StretchClickListener stretchClickListener) {
        super(itemView);
        stretchWebView = itemView.findViewById(R.id.stretch_imageview);
//        itemView.setOnClickListener(v -> stretchClickListener.onStretchClick(getLayoutPosition()));
        stretchWebView.getSettings().setJavaScriptEnabled(true);
        stretchWebView.setWebChromeClient(new WebChromeClient());
    }

}
