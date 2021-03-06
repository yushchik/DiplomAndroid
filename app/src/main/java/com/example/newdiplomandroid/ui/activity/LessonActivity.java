package com.example.newdiplomandroid.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.newdiplomandroid.DiplomApp;
import com.example.newdiplomandroid.R;
import com.example.newdiplomandroid.model.response.AllLessonResponse;
import com.example.newdiplomandroid.ui.adapter.AllLessonAdapter;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayer.*;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Url;

public class LessonActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    String videoId;
    Integer lessonId;
    String lessonInformation, lessonVideo, lessonTitle;
    public static final String KEY = "AIzaSyDXyRkQbDkUy2n0RFQOqKzQyazKb_TKdLg";
    @BindView(R.id.ytpv)
    YouTubePlayerView vv;
    @BindView(R.id.tvLessonTitle)
    TextView tvLessonTitle;
    @BindView(R.id.tvLessonInformation)
    TextView tvLessonInformation;
    @BindView(R.id.btnStartTest)
    Button btnStartTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        ButterKnife.bind(this);
        Intent mIntent = getIntent();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            lessonId = mIntent.getIntExtra("lessonId", 0);
        }
        initializeProgress();



        btnStartTest.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListTestActivity.class);
            intent.putExtra("lessonID", lessonId);
            startActivity(intent);
        });

    }
    private void initializeProgress(){

        String type = "application/json";
        DiplomApp.getApi().requestOneLesson(type, lessonId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::checkProgressResponse, this::handleProgrssError);
    }

    private void checkProgressResponse(AllLessonResponse allLessonResponse) {
        String[] items = allLessonResponse.getVideo().split("embed/");
        videoId = items[1];
        tvLessonTitle.setText(allLessonResponse.getTitlE_LESSON());
        tvLessonInformation.setText(Html.fromHtml(allLessonResponse.getInformation()));
        vv.initialize(KEY, this);
    }

    private void handleProgrssError(Throwable throwable) {
    }


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);

        youTubePlayer.cueVideo(videoId);

    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
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
    };
    YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
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

        }
    };

}
