package edu.neu.madcourse.charge.stretch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayerView;

import edu.neu.madcourse.charge.R;

public class YouTube extends AppCompatActivity {
    YouTubePlayerView youTubePlayerView;
    Intent videoIntent;
    String video;
    String videoId = "videoId";
    String API_KEY = "AIzaSyCgyiPkLYH0jQI0wrZlhzZscbMCPnJmSt4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube);
        youTubePlayerView = findViewById(R.id.youtube_player);
        videoIntent = getIntent();
        video = videoIntent.getStringExtra(videoId);
    }
}