package com.example.openevents.API;

import com.example.openevents.Request.LoginRequest;
import com.example.openevents.Request.RegisterRequest;
import com.example.openevents.Response.LoginResponse;
import com.example.openevents.Response.RegisterResponse;
import com.example.openevents.Response.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OpenEventsAPI {

    /**
     * Authentication
     */

    //register
    @POST("users")
    Call<RegisterResponse> register(RegisterRequest registerRequest);

    //login
    @POST("users/login")
    Call<LoginResponse> login(LoginRequest loginRequest);


    /**
     * Users
     */
    @GET("users")
    Call<List<UserResponse>> getUsers();

}
