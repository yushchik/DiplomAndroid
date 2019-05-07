package com.example.newdiplomandroid.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllLessonResponse {
    @SerializedName("iD_LESSON")
    @Expose
    private Integer iD_LESSON;

    @SerializedName("titlE_LESSON")
    @Expose
    private String titlE_LESSON;

    @SerializedName("iD_SECTION")
    @Expose
    private Integer iD_SECTION;

    @SerializedName("information")
    @Expose
    private String information;

    @SerializedName("video")
    @Expose
    private String video;

    public Integer getiD_LESSON() {
        return iD_LESSON;
    }

    public void setiD_LESSON(int iD_LESSON) {
        this.iD_LESSON = iD_LESSON;
    }

    public String getTitlE_LESSON() {
        return titlE_LESSON;
    }

    public void setTitlE_LESSON(String titlE_LESSON) {
        this.titlE_LESSON = titlE_LESSON;
    }

    public Integer getiD_SECTION() {
        return iD_SECTION;
    }

    public void setiD_SECTION(int iD_SECTION) {
        this.iD_SECTION = iD_SECTION;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
