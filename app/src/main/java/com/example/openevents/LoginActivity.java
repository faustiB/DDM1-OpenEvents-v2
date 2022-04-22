package com.example.openevents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.openevents.API.APIClient;
import com.example.openevents.API.OpenEventsCallback;
import com.example.openevents.Request.LoginRequest;
import com.example.openevents.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private APIClient apiClient = APIClient.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //TODO: Put the values of the ui.
        LoginRequest loginRequest = new LoginRequest("","");

        apiClient.login(loginRequest, new OpenEventsCallback<LoginResponse>() {
            @Override
            public void onResponseOpenEvents(Call<LoginResponse> call, Response<LoginResponse> response) {
                //TODO: Intent to the next screen. --> startActivity
            }

            @Override
            public void onFailureOpenEvents() {

            }

        });


    }
}