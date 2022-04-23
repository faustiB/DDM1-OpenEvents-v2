package com.example.openevents.Response;

public class UserStatisticsResponse {
    private float avg_score;
    private int num_coments;
    private float percentage_commenters_below;

    //constructor and getters
    public UserStatisticsResponse(float avg_score, int num_coments, float percentage_commenters_below) {
        this.avg_score = avg_score;
        this.num_coments = num_coments;
        this.percentage_commenters_below = percentage_commenters_below;
    }

    public float getAvg_score() {
        return avg_score;
    }

    public int getNum_coments() {
        return num_coments;
    }

    public float getPercentage_commenters_below() {
        return percentage_commenters_below;
    }
}
