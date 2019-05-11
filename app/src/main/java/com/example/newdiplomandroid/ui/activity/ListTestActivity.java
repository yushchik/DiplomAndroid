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
import com.example.newdiplomandroid.model.request.ResultQuizzRequest;
import com.example.newdiplomandroid.model.response.QuizzResponse;
import com.example.newdiplomandroid.model.response.ResultQuizzResponse;
import com.example.newdiplomandroid.ui.AdapterInterface;
import com.example.newdiplomandroid.ui.adapter.AllLessonAdapter;
import com.example.newdiplomandroid.ui.adapter.QuizzAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

    @BindView(R.id.tvErrorResult)
    TextView tvErrorResult;
    AdapterInterface adapterInterface;
    List<QuizzResponse> quizzResponse;
    JSONArray list;
    int count;
View view;
    ArrayList<ResultQuizzRequest> listresultQuizzRequests;
    List<ResultQuizzRequest> resultQuizzRequests;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_test);
        ButterKnife.bind(this);
        listresultQuizzRequests = new ArrayList<ResultQuizzRequest>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        Intent mIntent = getIntent();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            lessonId = mIntent.getIntExtra("lessonID", 0);
        }

        btnEndTest.setOnClickListener(v -> {
            resultQuizzRequests =  quizzAdapter.resultQuizz();
            Gson gson = new GsonBuilder().create();
            JsonArray myCustomArray = gson.toJsonTree(resultQuizzRequests).getAsJsonArray();
            rezulTest(resultQuizzRequests);
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
            quizzResponse = quizzResponses;
            quizzAdapter = new QuizzAdapter(quizzResponses, this);
            //  adapter.notifyDataSetChanged();
            mRecyclerView.setAdapter(quizzAdapter);

//            int index = rgAnswer.indexOfChild(findViewById(rgAnswer.getCheckedRadioButtonId()));
//            Log.d("IndexRG", String.valueOf(index));
        }
    }




    public void rezulTest(List<ResultQuizzRequest> resultQuizzRequests2){
        String type = "application/json";
        DiplomApp.getApi().requestQuizzResult(type, resultQuizzRequests2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::checkTestResponse, this::handleTestError);
    }

    private void handleTestError(Throwable throwable) {
        Log.d("ErrorQuizz", throwable.getMessage());
    }

    private void checkTestResponse(List<ResultQuizzResponse> resultQuizzResponses) {
        if(!resultQuizzResponses.isEmpty()){
            List<ResultQuizzResponse> resultQuizzResponses2 = resultQuizzResponses;
            for(int i = 0; i <quizzResponse.size(); i++){
                if(resultQuizzResponses2.get(i).isCorrect() == true){
                    count++;
                }
            }
            int result = count / quizzResponse.size() * 100;
            if(result >= 50){
                int newLesson = lessonId+1;
                Intent intent = new Intent(this, LessonActivity.class);
                intent.putExtra("lessonId", newLesson );
                startActivity(intent);
            }
            else{
                tvErrorResult.setText("Вы не набрали нужного количества баллов. Попробуйте еще раз");
            }

          //  quizzAdapter.TextResult(resultQuizzResponses2 );
        }
    }
}
