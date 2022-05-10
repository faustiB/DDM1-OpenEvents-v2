package com.example.openevents.Response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserStatisticsResponse implements Serializable {
    @SerializedName("avg_score")
    private float avg_score;
    @SerializedName("num_comments")
    private int num_comments;
    @SerializedName("percentage_commenters_below")
    private float percentage_commenters_below;

    public UserStatisticsResponse(float avg_score, int num_coments, float percentage_commenters_below) {
        this.avg_score = avg_score;
        this.num_comments = num_coments;
        this.percentage_commenters_below = percentage_commenters_below;
    }

    public float getAvg_score() {
        return avg_score;
    }

    public int getNum_comments() {
        return num_comments;
    }

    public float getPercentage_commenters_below() {
        return percentage_commenters_below;
    }
}
