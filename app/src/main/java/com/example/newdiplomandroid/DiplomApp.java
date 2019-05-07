package com.example.newdiplomandroid;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.example.newdiplomandroid.model.StatementApi;
import com.example.newdiplomandroid.ConnectivityReceiver;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
import efectura.booster_champions.network.api.StatementApi;
import efectura.booster_champions.util.ConnectivityReceiver;
*/

public class DiplomApp extends Application {

    private static DiplomApp INSTANCE;

    private static Context mContext;

    //private static final String BASE_URL = "http://192.168.0.111/WebApplication4/";
    private static final String BASE_URL = "http://192.168.0.111:80/";
   // private static final String BASE_URL = "http://192.168.0.111:51791/";

    private Retrofit retrofit;

    private static StatementApi statementApi;


    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        DiplomApp.mContext = getApplicationContext();
        if (!BuildConfig.DEBUG) {
            Log.d("Version", "Is realise");
          }

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        statementApi = retrofit.create(StatementApi.class);


    }

    public static StatementApi getApi() {
        return statementApi;
    }

    public static synchronized DiplomApp getInstance() {
        return INSTANCE;
    }

    public static Context getAppContext() {
        return DiplomApp.mContext;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
