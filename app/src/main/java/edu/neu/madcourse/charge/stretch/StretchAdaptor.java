package edu.neu.madcourse.charge.stretch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.neu.madcourse.charge.R;

public class StretchAdaptor extends RecyclerView.Adapter<StretchHolder> {

    private List<StretchVideo> stretchVideoVideoList;
    private Context context;
    private StretchClickListener stretchClickListener;

    public StretchAdaptor() {}

    public StretchAdaptor(List<StretchVideo> stretchVideoVideoList, Context context) {
        this.stretchVideoVideoList = stretchVideoVideoList;
        this.context = context;
    }


//    public void setStretchClickListener(StretchClickListener stretchClickListener) {
//        this.stretchClickListener = stretchClickListener;
//    }

    @NonNull
    @Override
    public StretchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stretch_video_layout, parent, false);
//        return new StretchHolder(view, stretchClickListener);
        StretchHolder stretchHolder = new StretchHolder(view);
        return stretchHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StretchHolder holder, int position) {
        StretchVideo currentStretchVideo = stretchVideoVideoList.get(position);
        holder.textView.setText(currentStretchVideo.getTitle());
        Picasso.get().load(currentStretchVideo.getLink()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return stretchVideoVideoList.size();
    }
}
