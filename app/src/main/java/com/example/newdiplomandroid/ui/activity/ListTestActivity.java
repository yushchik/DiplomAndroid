package com.example.newdiplomandroid.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.newdiplomandroid.DiplomApp;
import com.example.newdiplomandroid.R;
import com.example.newdiplomandroid.model.response.QuizzResponse;
import com.example.newdiplomandroid.ui.AdapterInterface;
import com.example.newdiplomandroid.ui.adapter.AllLessonAdapter;
import com.example.newdiplomandroid.ui.adapter.QuizzAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ListTestActivity extends AppCompatActivity {
    Integer lessonId;
    QuizzAdapter quizzAdapter;
    @BindView(R.id.rvQuizz)
    RecyclerView mRecyclerView;
    @BindView(R.id.btnEndTest)
    Button btnEndTest;
    AdapterInterface adapterInterface;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_test);
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        Intent mIntent = getIntent();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            lessonId = mIntent.getIntExtra("lessonID", 0);
        }

        btnEndTest.setOnClickListener(v -> {
            adapterInterface.resultQuizz();
        });
        initialize(lessonId);
    }

    public void initialize(int lessonID) {
        String type = "application/json";
        DiplomApp.getApi().requestQuizz(type, lessonID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::checkHistoryResponse, this::handleHistoryError);
    }

    private void handleHistoryError(Throwable throwable) {
        Log.d("ErrorQuizz", throwable.getMessage());
    }

    private void checkHistoryResponse(List<QuizzResponse> quizzResponses) {
        if (!quizzResponses.isEmpty()) {
            quizzAdapter = new QuizzAdapter(quizzResponses, this);
            //  adapter.notifyDataSetChanged();
            mRecyclerView.setAdapter(quizzAdapter);
//            int index = rgAnswer.indexOfChild(findViewById(rgAnswer.getCheckedRadioButtonId()));
//            Log.d("IndexRG", String.valueOf(index));
        }
    }
}
