package com.example.newdiplomandroid.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.newdiplomandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.Url;

public class LessonActivity extends AppCompatActivity {

    Integer lessonId;
    String lessonInformation, lessonVideo, lessonTitle;

    @BindView(R.id.vv)
    VideoView vv;
    @BindView(R.id.tvLessonTitle)
    TextView tvLessonTitle;
    @BindView(R.id.tvLessonInformation)
    TextView tvLessonInformation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        ButterKnife.bind(this);
        Intent mIntent = getIntent();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            lessonId = mIntent.getIntExtra("lessonId", 0);
            lessonInformation = mIntent.getStringExtra("lessonInformation");
            lessonVideo = mIntent.getStringExtra("lessonVideo");
            lessonTitle = mIntent.getStringExtra("lessonTitle");
        }
        tvLessonTitle.setText(lessonTitle);
        tvLessonInformation.setText( Html.fromHtml(lessonInformation));
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(vv);
        Uri uri = Uri.parse("rtsp://r3---sn-4g5ednsz.googlevideo.com/Cj0LENy73wIaNAlaJx7q_AX-5hMYDSANFC3n7NFcMOCoAUIASARgx5-w-tHKgoVcigELWWRMeFI1cWR0aTgM/E57238AFFE169D45D584715CAB24E12E1DD187A6.16A221A16E4C7D3364B9D47897EC9CEA8EAAE7AD/yt8/1/video.3gp");
        vv.setMediaController(mediaController);
        vv.setVideoURI(uri);
        vv.requestFocus();
        vv.start();

    }
}
