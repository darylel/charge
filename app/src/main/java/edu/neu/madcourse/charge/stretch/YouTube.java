package edu.neu.madcourse.charge.stretch;

import android.os.Bundle;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.neu.madcourse.charge.R;

public class YouTube extends YouTubeBaseActivity implements YouTubePlayer.PlaybackEventListener,
        YouTubePlayer.OnInitializedListener, YouTubePlayer.PlayerStateChangeListener {
    private YouTubePlayerView youTubePlayerView;
    private DatabaseReference databaseReference;
    private String API_KEY;
    private String videoId;
    private String firebaseAPIKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube);
        youTubePlayerView = findViewById(R.id.youtube_player);
        videoId = "videoId";
        firebaseAPIKey = "YouTubeAPI";
        databaseReference = FirebaseDatabase.getInstance().getReference().child(firebaseAPIKey);
        API_KEY = String.valueOf(databaseReference);
        youTubePlayerView.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(this);
        youTubePlayer.setPlaybackEventListener(this);

        if (b) {
            return;
        }
        youTubePlayer.cueVideo(getIntent().getStringExtra(videoId));
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        // Not implemented
    }

    @Override
    public void onPlaying() {
        // Not implemented
    }

    @Override
    public void onPaused() {
        // Not implemented
    }

    @Override
    public void onStopped() {
        // Not implemented
    }

    @Override
    public void onBuffering(boolean b) {
        // Not implemented
    }

    @Override
    public void onSeekTo(int i) {
        // Not implemented
    }

    @Override
    public void onLoading() {
        // Not implemented
    }

    @Override
    public void onLoaded(String s) {
        // Not implemented
    }

    @Override
    public void onAdStarted() {
        // Not implemented
    }

    @Override
    public void onVideoStarted() {
        // Not implemented
    }

    @Override
    public void onVideoEnded() {
        // Not implemented
    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {
        Toast.makeText(this, errorReason.toString(), Toast.LENGTH_SHORT).show();
    }
}