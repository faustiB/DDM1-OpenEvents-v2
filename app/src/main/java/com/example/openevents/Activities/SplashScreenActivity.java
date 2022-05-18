package com.example.openevents.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.openevents.R;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("token", MODE_PRIVATE);
                String accessToken = sharedPreferences.getString("token", null);
                Intent i;

                if (accessToken != null) {
                    sharedPreferences = getApplicationContext().getSharedPreferences("email", MODE_PRIVATE);
                    String email = sharedPreferences.getString("email", null);
                    i = new Intent(SplashScreenActivity.this, EventsFragmentManagerActivity.class);
                    i.putExtra("email", email);
                } else {
                    i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                }
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}