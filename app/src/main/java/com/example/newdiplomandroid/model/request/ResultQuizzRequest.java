package com.example.newdiplomandroid.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultQuizzRequest {

    @SerializedName("QuestionID")
    @Expose
    private Integer QuestionID;

    @SerializedName("QuestionText")
    @Expose
    private String QuestionText;

    @SerializedName("AnswerQ")
    @Expose
    private String AnswerQ;

    @SerializedName("isCorrect")
    @Expose
    private boolean isCorrect;

    public Integer getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(Integer questionID) {
        QuestionID = questionID;
    }

    public String getQuestionText() {
        return QuestionText;
    }

    public void setQuestionText(String questionText) {
        QuestionText = questionText;
    }

    public String getAnswerQ() {
        return AnswerQ;
    }

    public void setAnswerQ(String answerQ) {
        AnswerQ = answerQ;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
