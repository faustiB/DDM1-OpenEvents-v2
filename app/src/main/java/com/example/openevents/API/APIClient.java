package com.example.openevents.API;

import com.example.openevents.Request.LoginRequest;
import com.example.openevents.Request.RegisterRequest;
import com.example.openevents.Response.LoginResponse;
import com.example.openevents.Response.RegisterResponse;
import com.example.openevents.Response.UserResponse;

import java.util.List;

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
                .baseUrl("http://puigmal.salle.url.edu/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    /**
     *
     * Authentication
     *
     */
    //TODO: Put in register activity
    public void register(RegisterRequest registerRequest, OpenEventsCallback<RegisterResponse> callback){
        this.service.register(registerRequest).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                callback.onResponseOpenEvents(call,response);
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                callback.onFailureOpenEvents();
            }
        });


    }


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

    /**
     *
     * Users
     *
     */

    public void getUsers(OpenEventsCallback<List<UserResponse>> callback){

        this.service.getUsers().enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                //TODO: the users are in the response.body() and in there a list could be created
                callback.onResponseOpenEvents(call,response);
            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {

            }
        });

    }


}
