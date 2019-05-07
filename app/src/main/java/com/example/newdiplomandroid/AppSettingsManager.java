package com.example.newdiplomandroid;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by User on 10/11/2017.
 */

public class AppSettingsManager {

    private static final String APP_SETTINGS_PREFERENCES = "efectura.booster.settings";

    private static final String KEY_TOKEN = "efectura.booster.settings.KEY_TOKEN";
    private static final String KEY_EMAIL = "efectura.booster.settings.KEY_EMAIL";
    private static final String KEY_PASSWORD = "efectura.booster.settings.KEY_PASSWORD";
    private static final String KEY_SUCCESS_LOGIN = "efectura.booster.settings.KEY_SUCCESS_LOGIN";
    private static final String KEY_USER_BALANCE = "efectura.booster.settings.KEY_USER_BALANCE";
    private static final String KEY_USER_LANGUAGE_ID = "efectura.booster.settings.KEY_USER_LANGUAGE_ID";
    private static final String KEY_USER_PHONE_NUMBER = "efectura.booster.settings.KEY_USER_PHONE_NUMBER";
    private static final String IS_FIRST_TIME_LAUNCH = "efectura.booster.settings.IsFirstTimeLaunch";
    private static final String SIZE_ALL_TASK = "efectura.booster.settings.SIZE_ALL_TASK";
    private static final String SIZE_PERSONAL_TASK = "efectura.booster.settings.SIZE_PERSONAL_TASK";
    private static final String LANGUAGE_APP = "LANGUAGE_APP";

    private SharedPreferences sharedPreferences;
    private static AppSettingsManager INSTANCE;
    private Context mContext;

    public AppSettingsManager(Context context) {
        this.mContext = context;
        sharedPreferences =
                mContext.getSharedPreferences(APP_SETTINGS_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static AppSettingsManager getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new AppSettingsManager(context);
        }
        return INSTANCE;
    }

    public String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, "");
    }

    public void setToken(String authToken) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TOKEN, authToken);
        editor.apply();
    }

    public String getEmail() {
        return sharedPreferences.getString(KEY_EMAIL, "");
    }

    public void setEmail(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public String getPassword() {
        return sharedPreferences.getString(KEY_PASSWORD, "0");
    }

    public void setPassword(String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public void setSuccessLogin(boolean successLogin) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_SUCCESS_LOGIN, successLogin);
        editor.apply();
    }

    public boolean isSuccessLogin() {
        return sharedPreferences.getBoolean(KEY_SUCCESS_LOGIN, false);
    }


    public String getUserBalance() {
        return sharedPreferences.getString(KEY_USER_BALANCE, "0");
    }

    public void setUserBalance(String balance) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_BALANCE, balance);
        editor.apply();
    }

    public String getUserLanguageValue() {
        return sharedPreferences.getString(KEY_USER_LANGUAGE_ID, "en");
    }

    public void setUserLanguageValue(String languageValue) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_LANGUAGE_ID, languageValue);
        editor.apply();
    }

    public String getUserPhoneNumber() {
        return sharedPreferences.getString(KEY_USER_PHONE_NUMBER,"");
    }

    public void setUserPhoneNumber(String phoneNumber) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_PHONE_NUMBER, phoneNumber);
        editor.apply();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.apply();
    }

    public boolean isFirstTimeLaunch() {
        return sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setSizeAllTask(int sizeAllTask) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SIZE_ALL_TASK, sizeAllTask);
        editor.apply();
    }

    public int getSizeAllTask() {
        return sharedPreferences.getInt(SIZE_ALL_TASK, 0);
    }

    public void setSizePersonalTask(int personalTask) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SIZE_PERSONAL_TASK, personalTask);
        editor.apply();
    }

    public int getSizePersonal() {
        return sharedPreferences.getInt(SIZE_PERSONAL_TASK, 0);
    }

    public String getLanguageApp(){return sharedPreferences.getString(LANGUAGE_APP,"en");}
    public void setLanguageApp(String lang){
        sharedPreferences.edit().putString(LANGUAGE_APP,lang).apply();
    }

    public void clearSharedPreference(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
