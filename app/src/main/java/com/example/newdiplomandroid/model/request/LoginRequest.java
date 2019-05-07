package com.example.newdiplomandroid.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("UserName")
    @Expose
    private String UserName;

    @SerializedName("PasswordHash")
    @Expose
    private String PasswordHash;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }


    public String getPasswordHash() {
        return PasswordHash;
    }

    public void setPasswordHash(String passwordHash) {
        PasswordHash = passwordHash;
    }
}
