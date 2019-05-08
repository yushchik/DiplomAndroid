package com.example.newdiplomandroid.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newdiplomandroid.AppSettingsManager;
import com.example.newdiplomandroid.DiplomApp;
import com.example.newdiplomandroid.R;
import com.example.newdiplomandroid.model.response.AllLessonResponse;
import com.example.newdiplomandroid.ui.adapter.AllLessonAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AllLessonActivity extends AppCompatActivity {

    @BindView(R.id.gift_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tvErrorText)
    TextView tvErrorText;
    @BindView(R.id.toolbarTextViewHelpTitle)
    TextView toolbarTextViewHelpTitle;
    AllLessonAdapter adapter;
    String userName;
    List<AllLessonResponse> allLessonRespons;
    private AppSettingsManager settingsManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lesson);
        ButterKnife.bind(this);
        toolbarTextViewHelpTitle.setText("Уроки");
        settingsManager = AppSettingsManager.getInstance(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        Intent mIntent = getIntent();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userName = mIntent.getStringExtra("userId");
        }
        initializeData();
    }

    private void initializeData() {

        String type = "application/json";
        DiplomApp.getApi().requestAllLesson(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::checkGiftResponse, this::handleGiftError);
    }

    private void checkGiftResponse(List<AllLessonResponse> allLessonResponses) {
            if (!allLessonResponses.isEmpty()) {
                allLessonRespons = allLessonResponses;
                initializeProgress();

            } else {
                tvErrorText.setVisibility(View.VISIBLE);
            }
    }

    private void handleGiftError(Throwable throwable) {
        tvErrorText.setVisibility(View.VISIBLE);
        Log.e("Observer", "" + throwable.toString());
    }

    private void initializeProgress(){
        String userNAme = settingsManager.getEmail();
        String type = "application/json";
        DiplomApp.getApi().requestProgress(type, userNAme)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::checkProgressResponse, this::handleProgrssError);
    }

    private void handleProgrssError(Throwable throwable) {
    }

    private void checkProgressResponse(String s) {
        int integer = Integer.parseInt(s);
        adapter = new AllLessonAdapter(allLessonRespons, integer, this);
        //  adapter.notifyDataSetChanged();

        mRecyclerView.setAdapter(adapter);
    }
}
