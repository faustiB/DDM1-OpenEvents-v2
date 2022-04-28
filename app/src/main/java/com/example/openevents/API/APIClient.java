package com.example.openevents.API;

import androidx.annotation.NonNull;

import com.example.openevents.Request.EditUserRequest;
import com.example.openevents.Request.LoginRequest;
import com.example.openevents.Request.RegisterRequest;
import com.example.openevents.Response.LoginResponse;
import com.example.openevents.Response.RegisterResponse;
import com.example.openevents.Response.UserResponse;
import com.example.openevents.Response.UserStatisticsResponse;
import com.example.openevents.Response.UsersResponse;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
        this.service = retrofit.create(OpenEventsAPI.class);
    }

    private void addTokenToRequest(String accessToken) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NonNull
            @Override
            public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + accessToken)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        this.retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://puigmal.salle.url.edu/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.service = retrofit.create(OpenEventsAPI.class);
    }


    /**
     *
     * Authentication
     *
     */
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
                addTokenToRequest(accessToken);

                callback.onResponseOpenEvents(call,response);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onFailureOpenEvents();
            }
        });
    }

    /**
     *
     * Users
     *
     */

    public void getUsers(OpenEventsCallback<List<UsersResponse>> callback){
        this.service.getUsers().enqueue(new Callback<List<UsersResponse>>() {
            @Override
            public void onResponse(Call<List<UsersResponse>> call, Response<List<UsersResponse>> response) {
                //TODO: the users are in the response.body() and in there a list could be created
                callback.onResponseOpenEvents(call,response);
            }

            @Override
            public void onFailure(Call<List<UsersResponse>> call, Throwable t) {

            }
        });

    }

    public void getUserById (int id, OpenEventsCallback<UserResponse> callback) {
        this.service.getUserById(id).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                callback.onResponseOpenEvents(call,response);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }

    public void searchUsersByName (String name, OpenEventsCallback<List<UserResponse>> callback) {
        this.service.searchUsersByName(name).enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                callback.onResponseOpenEvents(call,response);
            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {

            }
        });
    }

    public void getUserStatistics (int id, OpenEventsCallback<UserStatisticsResponse> callback) {
        this.service.getUserStatistics(id).enqueue(new Callback<UserStatisticsResponse>() {
            @Override
            public void onResponse(Call<UserStatisticsResponse> call, Response<UserStatisticsResponse> response) {
                callback.onResponseOpenEvents(call,response);
            }

            @Override
            public void onFailure(Call<UserStatisticsResponse> call, Throwable t) {

            }
        });
    }

    public void updateUser (EditUserRequest editUserRequest, OpenEventsCallback<UserResponse> callback) {
        this.service.updateUser(editUserRequest).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                callback.onResponseOpenEvents(call,response);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }
}
