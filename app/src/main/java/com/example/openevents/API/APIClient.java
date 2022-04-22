package com.example.openevents.API;

import com.example.openevents.Request.LoginRequest;
import com.example.openevents.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIClient {

    private static APIClient shared;

    private Retrofit retrofit;
    private OpenEventsAPI service;

    private String accessToken;

    public static APIClient getInstance(){
        if (shared == null){
            shared = new APIClient();
        }
        return shared;
    }

    private APIClient(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    /**
     *
     * Authentication
     *
     */
    public void login(LoginRequest loginRequest, OpenEventsCallback<LoginResponse> callback){
        this.service.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                accessToken = response.body().getAccesToken();

                callback.onResponseOpenEvents(call,response);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }


}
