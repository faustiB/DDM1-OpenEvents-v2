package com.example.openevents.API;

import com.example.openevents.Request.LoginRequest;
import com.example.openevents.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OpenEventsAPI {

    /**
     *
     * Authentication
     *
     */
    @POST("login")
    Call<LoginResponse> login(LoginRequest loginRequest);

    //@POST("register")
    //Call<RegisterResponse> register();

}
