package com.example.newdiplomandroid.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultQuizzResponse {

    @SerializedName("questionID")
    @Expose
    private Integer questionID;

    @SerializedName("questionText")
    @Expose
    private String questionText;

    @SerializedName("answerQ")
    @Expose
    private String answerQ;

    @SerializedName("isCorrect")
    @Expose
    private boolean isCorrect;

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

    public String getAnswerQ() {
        return answerQ;
    }

    public void setAnswerQ(String answerQ) {
        this.answerQ = answerQ;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
