package com.example.openevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.openevents.API.APIClient;
import com.example.openevents.API.OpenEventsCallback;
import com.example.openevents.Response.UserResponse;
import com.example.openevents.Response.UserStatisticsResponse;

import retrofit2.Call;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    ImageView imageView;
    TextView user_name, last_name, email, avg_score, num_comments, percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setViews();

        Intent i = getIntent();
        UserResponse user = (UserResponse) i.getSerializableExtra("user");

        setUserData(user);
        executeApiCall(user.getId());
    }

    private void setViews() {
        imageView = findViewById(R.id.profile_picture);
        user_name = findViewById(R.id.user_name);
        last_name = findViewById(R.id.user_surname);
        email = findViewById(R.id.user_email);
        avg_score = findViewById(R.id.user_average_score);
        num_comments = findViewById(R.id.user_number_comments);
        percentage = findViewById(R.id.user_percentage_of_comments);
    }

    private void setUserData(UserResponse user) {
        user_name.setText(user.getName());
        last_name.setText(user.getLast_name());
        email.setText(user.getEmail());
        Glide.with(getApplicationContext())
                .load(user.getImage())
                .placeholder(R.drawable.profile)
                .into(imageView);
    }

    private void setStatisticsData(UserStatisticsResponse statistics) {
        avg_score.setText(String.valueOf(statistics.getAvg_score()));
        num_comments.setText(String.valueOf(statistics.getNum_comments()));
        percentage.setText(statistics.getPercentage_commenters_below() + "%");
    }

    private void executeApiCall(int userId) {
        System.out.println(userId);
        APIClient apiClient = APIClient.getInstance(getApplicationContext());
        apiClient.getUserStatistics(userId, new OpenEventsCallback<UserStatisticsResponse>() {
            @Override
            public void onResponseOpenEvents(Call<UserStatisticsResponse> call, Response<UserStatisticsResponse> response) {
                if (response.isSuccessful()) {
                    UserStatisticsResponse statistics = response.body();
                    setStatisticsData(statistics);
                }
            }

            @Override
            public void onFailureOpenEvents() {
                System.out.println("failure");
            }
        });
    }
}