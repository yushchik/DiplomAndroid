package com.example.newdiplomandroid.ui.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newdiplomandroid.AppSettingsManager;
import com.example.newdiplomandroid.R;
import com.example.newdiplomandroid.model.response.AllLessonResponse;
import com.example.newdiplomandroid.ui.activity.LessonActivity;

import java.util.List;

public class AllLessonAdapter extends RecyclerView.Adapter<AllLessonAdapter.AllLessonViewHolder> {
    private List<AllLessonResponse> giftList;
    Context context;
    int progress;


    public class AllLessonViewHolder extends RecyclerView.ViewHolder {


        TextView tvIdLesson, tvPoints;
        LinearLayout llPoints;
        CardView cvGifts;

        public AllLessonViewHolder(View view) {
            super(view);
            tvIdLesson = view.findViewById(R.id.tvIdLesson);
            tvPoints = view.findViewById(R.id.tvPoints);
            cvGifts = view.findViewById(R.id.cvGifts);
            llPoints = view.findViewById(R.id.llPoints);
        }
    }

    public AllLessonAdapter(List<AllLessonResponse> giftList, int progress, Context context) {
        this.giftList = giftList;
        this.context = context;
        this.progress = progress;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public AllLessonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lessons, parent, false);

        return new AllLessonViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.O_MR1)
    @Override
    public void onBindViewHolder(AllLessonViewHolder holder, int position) {
        AllLessonResponse allLessonResponse = giftList.get(position);
        holder.tvIdLesson.setText(String.valueOf(allLessonResponse.getiD_LESSON()));
        holder.tvPoints.setText(allLessonResponse.getTitlE_LESSON());
        if(allLessonResponse.getiD_LESSON() > progress + 1)
        {
            holder.llPoints.setBackgroundColor(context.getResources().getColor(R.color.color_grey_text));
            holder.llPoints.setEnabled(false);
        }
        else {
            holder.llPoints.setBackgroundColor(context.getResources().getColor(R.color.background));
            holder.llPoints.setEnabled(true);
        }
        holder.llPoints.setOnClickListener(view ->
        {
            Intent intent = new Intent(context, LessonActivity.class);
            intent.putExtra("lessonId", allLessonResponse.getiD_LESSON());
//            intent.putExtra("lessonInformation", allLessonResponse.getInformation());
//            intent.putExtra("lessonVideo", allLessonResponse.getVideo());
//            intent.putExtra("lessonTitle", allLessonResponse.getTitlE_LESSON());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return giftList.size();
    }
}
