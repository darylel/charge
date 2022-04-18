package edu.neu.madcourse.charge.stretch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.neu.madcourse.charge.R;

public class StretchAdaptor extends RecyclerView.Adapter<StretchHolder> {

    private List<StretchVideo> stretchVideoVideoList;
    private StretchClickListener stretchClickListener;

    public StretchAdaptor() {}

    public StretchAdaptor(List<StretchVideo> stretchVideoVideoList) {
        this.stretchVideoVideoList = stretchVideoVideoList;
    }


    public void setStretchClickListener(StretchClickListener stretchClickListener) {
        this.stretchClickListener = stretchClickListener;
    }

    @NonNull
    @Override
    public StretchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stretch_video_layout, parent, false);
        return new StretchHolder(view, stretchClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StretchHolder holder, int position) {
        StretchVideo currentStretchVideo = stretchVideoVideoList.get(position);
        holder.stretchWebView.loadData(currentStretchVideo.getUrl(), "text/html", "utf-8");
    }

    @Override
    public int getItemCount() {
        return stretchVideoVideoList.size();
    }
}
