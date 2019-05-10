package com.example.newdiplomandroid.ui.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.newdiplomandroid.R;
import com.example.newdiplomandroid.model.request.ResultQuizzRequest;
import com.example.newdiplomandroid.model.response.ChoicesResponse;
import com.example.newdiplomandroid.model.response.QuizzResponse;
import com.example.newdiplomandroid.ui.AdapterInterface;


import java.util.ArrayList;
import java.util.List;

public class QuizzAdapter extends RecyclerView.Adapter<QuizzAdapter.AllLessonViewHolder> implements AdapterInterface  {
private List<QuizzResponse> giftList;
        Context context;
    int index;
    View view;
    ResultQuizzRequest resultQuizzRequest;
 ArrayList<ResultQuizzRequest> listresultQuizzRequests;
public class AllLessonViewHolder extends RecyclerView.ViewHolder {


    TextView tvIdLesson, tvPoints;
    RadioGroup rgAnswer;
    CardView cvGifts;

    public AllLessonViewHolder(View view) {
        super(view);
        tvIdLesson = view.findViewById(R.id.tvQuestionTitle);
       // tvPoints = view.findViewById(R.id.tvPoints);
        cvGifts = view.findViewById(R.id.cvQuizz);
        rgAnswer = view.findViewById(R.id.rgAnswer);
       // llPoints = view.findViewById(R.id.llPoints);
    }
}

    public QuizzAdapter(List<QuizzResponse> giftList, Context context) {
        this.giftList = giftList;
        this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public QuizzAdapter.AllLessonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz, parent, false);

        return new QuizzAdapter.AllLessonViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.O_MR1)
    @Override
    public void onBindViewHolder(QuizzAdapter.AllLessonViewHolder holder, int position) {
        QuizzResponse allLessonResponse = giftList.get(position);
        holder.tvIdLesson.setText(allLessonResponse.getQuestionText());
        int count = allLessonResponse.getChoices().size();

        listresultQuizzRequests = new ArrayList<ResultQuizzRequest>();
        for(int i = 1; i <= count; i++){
            List<ChoicesResponse> feeTaskList = allLessonResponse.getChoices();
            ChoicesResponse choicesResponse = feeTaskList.get(i-1);
            RadioButton rbn = new RadioButton(context);
            rbn.setId(choicesResponse.getChoiceID());
            rbn.setText(choicesResponse.getChoiceText());
            holder.rgAnswer.addView(rbn);
        }
        holder.rgAnswer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                index = radioGroup.indexOfChild(radioButton);
                RadioButton radioButton2 = (RadioButton) radioButton.findViewById(i);
                String selectedRadioButtonText = (String) radioButton2.getText();
                resultQuizzRequest = new ResultQuizzRequest();
                resultQuizzRequest.setAnswerQ(selectedRadioButtonText);
                resultQuizzRequest.setQuestionID(allLessonResponse.getQuestionID());
                resultQuizzRequest.setQuestionText(allLessonResponse.getQuestionText());
                listresultQuizzRequests.add(resultQuizzRequest);
                Log.d("IndexRG", String.valueOf(listresultQuizzRequests));
            }
        });


            //holder.tvPoints.setText(allLessonResponse.getTitlE_LESSON());

    }

    @Override
    public int getItemCount() {
        return giftList.size();
    }

    @Override
    public void resultQuizz(){
        Log.d("IndexRG", String.valueOf(listresultQuizzRequests));
    }
}