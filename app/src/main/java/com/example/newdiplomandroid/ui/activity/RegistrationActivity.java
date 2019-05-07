package com.example.newdiplomandroid.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.newdiplomandroid.DiplomApp;
import com.example.newdiplomandroid.R;
import com.example.newdiplomandroid.model.StatementApi;
import com.example.newdiplomandroid.model.request.LoginRequest;
import com.example.newdiplomandroid.model.request.RegistrationRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.toolbarTextViewHelpTitle)
    TextView toolbarTextViewHelpTitle;
    @BindView(R.id.etEmail)
    TextInputEditText etEmail;
    @BindView(R.id.etPassword)
    TextInputEditText etPassword;
    @BindView(R.id.etPasswordConfirm)
    TextInputEditText etPasswordConfirm;
    @BindView(R.id.etSurName)
    TextInputEditText etSurName;
    @BindView(R.id.etName)
    TextInputEditText etName;
    @BindView(R.id.etCity)
    TextInputEditText etCity;
    @BindView(R.id.tvdayOfBirth)
    TextView tvdayOfBirth;
    @BindView(R.id.tvError)
    TextView tvError;
    @BindView(R.id.btnRegistr)
    Button btnRegistr;
    @BindView(R.id.tvBirthday)
    TextView tvBirthday;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        toolbarTextViewHelpTitle.setText("Регистрация");
        tvdayOfBirth.setOnClickListener(view1 -> {
            chechBirhday();
        });
        btnRegistr.setOnClickListener(view1 -> {
            refistr();
        });
    }

    private void refistr() {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEmail(etEmail.getText().toString());
        registrationRequest.setUserName(etEmail.getText().toString());
        registrationRequest.setSURNAME(etSurName.getText().toString());
        registrationRequest.setNAME(etName.getText().toString());
        registrationRequest.setCITY(etCity.getText().toString());
        registrationRequest.setPasswordHash(etPassword.getText().toString());
        registrationRequest.setBIRTHDAY(getFormat(tvdayOfBirth.getText().toString()));

        String type = "application/json";
        DiplomApp.getApi().requestRegistration(StatementApi.mContentType, registrationRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::checkLoginResponse, this::handleLoginError);
    }

    private void handleLoginError(Throwable throwable) {

        Log.d("Error", throwable.getMessage());
        tvError.setVisibility(View.VISIBLE);
    }

    private void checkLoginResponse(String s) {
        String userID = s;
        Intent intent = new Intent(this, ListTestActivity.class);
        intent.putExtra("userId", userID);
        startActivity(intent);
    }

    private void chechBirhday() {
        if (tvdayOfBirth.getText().length() == 0) {
            SimpleDateFormat currentDate = new SimpleDateFormat("dd.MM.yyyy");
            Date todayDate = new Date();
            String thisDate = currentDate.format(todayDate);
            getFormatDate(thisDate);
        } else {
            getFormatDate(tvdayOfBirth.getText().toString());
        }
        showDialogWithCalendar();
    }

    private void getFormatDate(String dateOfBirth) {
        String[] items = dateOfBirth.split("\\.");

        mDay = Integer.valueOf(items[0]);
        mMonth = Integer.valueOf(items[1]);
        mYear = Integer.valueOf(items[2]);
    }

    private void showDialogWithCalendar() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.date_picker_dialog);

        DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.datePicker);
        datePicker.updateDate(mYear, mMonth - 1, mDay);

        Button dialogButtonSave = (Button) dialog.findViewById(R.id.btnDialogCalendarSave);
        dialogButtonSave.setOnClickListener((View v) -> {
            String newMonth = String.valueOf(datePicker.getMonth() + 1);
            if (0 < (datePicker.getMonth() + 1) && (datePicker.getMonth() + 1) < 10)
                newMonth = "0" + newMonth;
            String newYear = String.valueOf(datePicker.getYear());
            String newDay = String.valueOf(datePicker.getDayOfMonth());
            if (0 < datePicker.getDayOfMonth() && datePicker.getDayOfMonth() < 10)
                newDay = "0" + newDay;
            tvdayOfBirth.setText(newDay + "." + newMonth + "." + newYear);
            tvBirthday.setVisibility(View.VISIBLE);
            dialog.dismiss();
        });
        dialog.show();
    }

    private String getFormat(String dateOfBirth) {
        if (dateOfBirth.length() == 0) {
            return null;
        } else {
            String[] items = dateOfBirth.split("\\.");

            mDay = Integer.valueOf(items[0]);
            mMonth = Integer.valueOf(items[1]);
            mYear = Integer.valueOf(items[2]);

            String birth = mYear + "-" + mMonth + "-" + mDay;
            return birth;
        }
    }
}
