package com.example.newdiplomandroid.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationRequest {

    @SerializedName("UserName")
    @Expose
    private String UserName;

    @SerializedName("PasswordHash")
    @Expose
    private String PasswordHash;

    @SerializedName("Email")
    @Expose
    private String Email;

    @SerializedName("SURNAME")
    @Expose
    private String SURNAME;

    @SerializedName("NAME")
    @Expose
    private String NAME;

    @SerializedName("CITY")
    @Expose
    private String CITY;

    @SerializedName("BIRTHDAY")
    @Expose
    private String BIRTHDAY;


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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSURNAME() {
        return SURNAME;
    }

    public void setSURNAME(String SURNAME) {
        this.SURNAME = SURNAME;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getBIRTHDAY() {
        return BIRTHDAY;
    }

    public void setBIRTHDAY(String BIRTHDAY) {
        this.BIRTHDAY = BIRTHDAY;
    }
}
