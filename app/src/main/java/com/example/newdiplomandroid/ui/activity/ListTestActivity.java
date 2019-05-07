package com.example.newdiplomandroid.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.newdiplomandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListTestActivity extends AppCompatActivity {
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_test);
        ButterKnife.bind(this);
        Intent mIntent = getIntent();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = mIntent.getStringExtra("userId");
        }
    }
}
