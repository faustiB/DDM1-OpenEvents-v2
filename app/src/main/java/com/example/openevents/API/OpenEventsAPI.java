package com.example.openevents.API;

import com.example.openevents.Request.CreateEventRequest;
import com.example.openevents.Request.EditUserRequest;
import com.example.openevents.Request.LoginRequest;
import com.example.openevents.Request.RegisterRequest;
import com.example.openevents.Response.AssistEventResponse;
import com.example.openevents.Response.CreateEventResponse;
import com.example.openevents.Response.EventResponse;
import com.example.openevents.Response.LoginResponse;
import com.example.openevents.Response.MessageResponse;
import com.example.openevents.Response.RegisterResponse;
import com.example.openevents.Response.UserResponse;
import com.example.openevents.Response.UserStatisticsResponse;
import com.example.openevents.Response.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OpenEventsAPI {

    /**
     * Authentication
     */

    //register
    @POST("users")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);

    //login
    @POST("users/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);


    /**
     * Users
     */
    @GET("users")
    Call<List<UsersResponse>> getUsers();

    @GET("users/{id}")
    Call<UserResponse> getUserById(int id);

    @GET("users/search/")
    Call<List<UserResponse>> searchUsersByString(@Query("s") String name);

    @GET("users/{id}/assistances")
    Call<List<EventResponse>> getUserAssistances(@Path("id") int id);

    @GET("users/{id}/statistics")
    Call<UserStatisticsResponse> getUserStatistics(@Path("id") int id);

    @PUT("users")
    Call<UserResponse> updateUser(@Body EditUserRequest editUserRequest);

    /**
     * Events
     */
    @POST("events")
    Call<CreateEventResponse> createEvent(@Body CreateEventRequest createEventRequest);

    @GET("events")
    Call<List<EventResponse>> getEvents();

    @GET("events/best")
    Call<List<EventResponse>> getBestEvents();

    @GET("events/search/")
    Call<List<EventResponse>> searchEventsByString(@Query("keyword") String event);

    @PUT("events/{id}")
    Call<CreateEventResponse> editEvent(@Path("id") int id, @Body CreateEventRequest createEventRequest);

    @DELETE("events/{id}/")
    Call<MessageResponse> deleteEvent(@Path("id") int id);

    @POST("events/{id}/assistances")
    Call<AssistEventResponse> assistEvent(@Path("id") int id);

    @DELETE("events/{id}/assistances")
    Call<AssistEventResponse> notAssistEvent(@Path("id") int id);
}
