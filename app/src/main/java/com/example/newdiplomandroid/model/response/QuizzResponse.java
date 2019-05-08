package com.example.newdiplomandroid.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class QuizzResponse {

    @SerializedName("questionID")
    @Expose
    private Integer questionID;

    @SerializedName("questionText")
    @Expose
    private String questionText;

    @SerializedName("questionType")
    @Expose
    private String questionType;

    @SerializedName("anwser")
    @Expose
    private String anwser;

    @SerializedName("choices")
    @Expose
    private List<ChoicesResponse> choices = new ArrayList<ChoicesResponse>();

    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getAnwser() {
        return anwser;
    }

    public void setAnwser(String anwser) {
        this.anwser = anwser;
    }

    public List<ChoicesResponse> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoicesResponse> choices) {
        this.choices = choices;
    }
}
