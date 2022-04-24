package com.example.openevents.API;

import com.example.openevents.Request.EditUserRequest;
import com.example.openevents.Request.LoginRequest;
import com.example.openevents.Request.RegisterRequest;
import com.example.openevents.Response.LoginResponse;
import com.example.openevents.Response.RegisterResponse;
import com.example.openevents.Response.UserResponse;
import com.example.openevents.Response.UserStatisticsResponse;
import com.example.openevents.Response.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface OpenEventsAPI {

    /**
     * Authentication
     */

    //register
    @POST("/users")
    Call<RegisterResponse> register(RegisterRequest registerRequest);

    //login
    @POST("/users/login")
    Call<LoginResponse> login(LoginRequest loginRequest);


    /**
     * Users
     */
    @GET("users")
    Call<List<UsersResponse>> getUsers();

    @GET("users/{id}")
    Call<UserResponse> getUserById(int id);

    @GET("users/search/s={name}")
    Call<List<UserResponse>> searchUsersByName(String name);

    @GET("users/{id}/statistics")
    Call<UserStatisticsResponse> getUserStatistics(int id);

    @PUT("users")
    Call<UserResponse> updateUser(EditUserRequest editUserRequest);
}
