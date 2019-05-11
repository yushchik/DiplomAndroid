package com.example.newdiplomandroid.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChoicesResponse {

    @SerializedName("choiceID")
    @Expose
    private Integer choiceID;

    @SerializedName("choiceText")
    @Expose
    private String choiceText;

    @SerializedName("isTrue")
    @Expose
    private Integer isTrue;


    public Integer getChoiceID() {
        return choiceID;
    }

    public void setChoiceID(Integer choiceID) {
        this.choiceID = choiceID;
    }

    public String getChoiceText() {
        return choiceText;
    }

    public void setChoiceText(String choiceText) {
        this.choiceText = choiceText;
    }

    public Integer getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(Integer isTrue) {
        this.isTrue = isTrue;
    }
}
