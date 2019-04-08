package com.example.myapplication.moviedetail.movietrailer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

public class YoutubePlayerActivity extends YouTubeBaseActivity {

    private static final String API_KEY = "AIzaSyBjltoG7SvAYBGGq_u-oSg4VQuC-zuMUYw";
    private static final String VIDEO_URL = "videoUrl";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.youtube_player_activity);

        getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_FULLSCREEN | SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        YouTubePlayerView youTubePlayerView = findViewById(R.id.player);

        youTubePlayerView.initialize(API_KEY,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {

                        youTubePlayer.loadVideo(getIntent().getStringExtra(VIDEO_URL));
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
    }

    public static Intent startIntent(String videoUrl, Context context) {
        Intent intent = new Intent(context, YoutubePlayerActivity.class);
        intent.putExtra(VIDEO_URL, videoUrl);
        return intent;
    }
}
