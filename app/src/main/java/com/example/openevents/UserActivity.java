package com.example.openevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.openevents.Response.UserResponse;

public class UserActivity extends AppCompatActivity {
    ImageView imageView;
    TextView user_name, last_name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        imageView = findViewById(R.id.profile_picture);
        user_name = findViewById(R.id.user_name);
        last_name = findViewById(R.id.user_surname);
        email = findViewById(R.id.user_email);

        Intent i = getIntent();
        UserResponse user = (UserResponse) i.getSerializableExtra("user");

        setUserData(user);
    }

    private void setUserData(UserResponse user) {
        user_name.setText(user.getName());
        last_name.setText(user.getLast_name());
        email.setText(user.getEmail());
        Glide.with(getApplicationContext()).load(user.getImage()).into(imageView);
    }
}