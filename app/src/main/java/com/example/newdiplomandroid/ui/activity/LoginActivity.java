package com.example.newdiplomandroid.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.widget.TextView;


import com.example.newdiplomandroid.AppSettingsManager;
import com.example.newdiplomandroid.ConnectivityReceiver;
import com.example.newdiplomandroid.DiplomApp;
import com.example.newdiplomandroid.R;
import com.example.newdiplomandroid.model.StatementApi;
import com.example.newdiplomandroid.model.request.LoginRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;




public class LoginActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    private AppSettingsManager settingsManager;
    Snackbar snackbarInternet;
    @BindView(R.id.btnlogin)
    Button btnlogin;
    @BindView(R.id.etName)
    TextInputEditText etName;
    @BindView(R.id.etPassword)
    TextInputEditText etPassword;
    @BindView(R.id.coordLayoutLoginFragment)
    ConstraintLayout coordLayoutLoginFragment;
    @BindView(R.id.toolbarTextViewHelpTitle)
    TextView toolbarTextViewHelpTitle;
    @BindView(R.id.tvError)
    TextView tvError;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        settingsManager = AppSettingsManager.getInstance(this);
        toolbarTextViewHelpTitle.setText("Авторизация");
        btnlogin.setOnClickListener(view1 -> {
            singIn();
        });
    }

    public void singIn() {


        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserName(etName.getText().toString());
        loginRequest.setPasswordHash(etPassword.getText().toString());


        String type = "application/json";
        DiplomApp.getApi().requestLogin(StatementApi.mContentType, loginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::checkLoginResponse, this::handleLoginError);
    }

    private void handleLoginError(Throwable throwable) {

        Log.d("Error", throwable.getMessage());
        tvError.setVisibility(View.VISIBLE);
    }

    private void checkLoginResponse(String s) {
        settingsManager.setSuccessLogin(true);
        if (s!= null) {
            String token = s;
            settingsManager.setToken(token);
            settingsManager.setEmail(etName.getText().toString());
            Intent intent = new Intent(this, AllLessonActivity.class);
            //intent.putExtra("userId", userID);
            startActivity(intent);
        }
    }
    public void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    private void showSnack(boolean isConnected) {
        if (isConnected) {
        } else {
            snackbarInternet = Snackbar.make(coordLayoutLoginFragment, "ERROR", Snackbar.LENGTH_INDEFINITE)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            checkConnection();
                            if (ConnectivityReceiver.isConnected()) snackbarInternet.dismiss();
                        }
                    });
            snackbarInternet.show();
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
        if (snackbarInternet != null && ConnectivityReceiver.isConnected())
            snackbarInternet.dismiss();
    }

}
