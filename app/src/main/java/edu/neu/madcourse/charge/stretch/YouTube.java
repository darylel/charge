package edu.neu.madcourse.charge.stretch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import edu.neu.madcourse.charge.R;

public class YouTube extends YouTubeBaseActivity implements YouTubePlayer.PlaybackEventListener,
        YouTubePlayer.OnInitializedListener, YouTubePlayer.PlayerStateChangeListener {
    YouTubePlayerView youTubePlayerView;
    Intent videoIntent;
    String videoId = "videoId";
    String API_KEY = "AIzaSyCgyiPkLYH0jQI0wrZlhzZscbMCPnJmSt4"; // will need store and retrieve  from firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube);
        youTubePlayerView = findViewById(R.id.youtube_player);
        videoIntent = getIntent();
        youTubePlayerView.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(this);
        youTubePlayer.setPlaybackEventListener(this);

        if (b) {
            return;
        }
        youTubePlayer.cueVideo(videoIntent.getStringExtra(videoId));
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoaded(String s) {

    }

    @Override
    public void onAdStarted() {

    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onVideoEnded() {

    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {
        Toast.makeText(this, errorReason.toString(), Toast.LENGTH_SHORT).show();
    }
}