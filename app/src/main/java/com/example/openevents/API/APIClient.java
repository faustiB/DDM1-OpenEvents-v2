package com.example.openevents.API;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

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

    private final Retrofit retrofit;
    private final OpenEventsAPI service;

    private String accessToken;

    public static APIClient getInstance(Context applicationContext) {
        if (shared == null) {
            shared = new APIClient(applicationContext);
        }
        return shared;
    }

    private APIClient(Context applicationContext) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NonNull
            @Override
            public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("token", MODE_PRIVATE);
                accessToken = sharedPreferences.getString("token", null);

                if (accessToken != null) {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " + accessToken)
                            .build();
                    return chain.proceed(newRequest);
                } else {
                    return chain.proceed(chain.request());
                }
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
     * Authentication
     */
    public void register(RegisterRequest registerRequest, OpenEventsCallback<RegisterResponse> callback) {
        this.service.register(registerRequest).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                callback.onResponseOpenEvents(call, response);
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                callback.onFailureOpenEvents();
                System.out.println(t.getMessage());
            }
        });


    }

    public void login(Context context, LoginRequest loginRequest, OpenEventsCallback<LoginResponse> callback) {
        this.service.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    accessToken = response.body().getAccessToken();

                    //Save token to shared preferences
                    SharedPreferences sharedPref = context.getSharedPreferences("token", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("token", accessToken);
                    editor.apply();
                }

                callback.onResponseOpenEvents(call, response);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onFailureOpenEvents();
                System.out.println(t.getMessage());
            }
        });
    }

    /**
     * Users
     */

    public void getUsers(OpenEventsCallback<List<UsersResponse>> callback) {
        this.service.getUsers().enqueue(new Callback<List<UsersResponse>>() {
            @Override
            public void onResponse(Call<List<UsersResponse>> call, Response<List<UsersResponse>> response) {
                callback.onResponseOpenEvents(call, response);
            }

            @Override
            public void onFailure(Call<List<UsersResponse>> call, Throwable t) {
                callback.onFailureOpenEvents();
                System.out.println(t.getMessage());
            }
        });

    }

    public void getUserById(int id, OpenEventsCallback<UserResponse> callback) {
        this.service.getUserById(id).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                callback.onResponseOpenEvents(call, response);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                callback.onFailureOpenEvents();
                System.out.println(t.getMessage());
            }
        });
    }

    public void searchUsersByString(String name, OpenEventsCallback<List<UserResponse>> callback) {
        this.service.searchUsersByString(name).enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                callback.onResponseOpenEvents(call, response);
            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void getUserStatistics(int id, OpenEventsCallback<UserStatisticsResponse> callback) {
        this.service.getUserStatistics(id).enqueue(new Callback<UserStatisticsResponse>() {
            @Override
            public void onResponse(Call<UserStatisticsResponse> call, Response<UserStatisticsResponse> response) {
                callback.onResponseOpenEvents(call, response);
            }

            @Override
            public void onFailure(Call<UserStatisticsResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void updateUser(EditUserRequest editUserRequest, OpenEventsCallback<UserResponse> callback) {
        this.service.updateUser(editUserRequest).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                callback.onResponseOpenEvents(call, response);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                callback.onFailureOpenEvents();
                System.out.println(t.getMessage());
            }
        });
    }

    public void createEvent(CreateEventRequest createEventRequest, OpenEventsCallback<CreateEventResponse> callback) {
        this.service.createEvent(createEventRequest).enqueue(new Callback<CreateEventResponse>() {
            @Override
            public void onResponse(Call<CreateEventResponse> call, Response<CreateEventResponse> response) {
                callback.onResponseOpenEvents(call, response);
            }

            @Override
            public void onFailure(Call<CreateEventResponse> call, Throwable t) {
                callback.onFailureOpenEvents();
            }
        });
    }

    public void getEvents(OpenEventsCallback<List<EventResponse>> callback) {
        this.service.getEvents().enqueue(new Callback<List<EventResponse>>() {
            @Override
            public void onResponse(Call<List<EventResponse>> call, Response<List<EventResponse>> response) {
                callback.onResponseOpenEvents(call, response);
            }

            @Override
            public void onFailure(Call<List<EventResponse>> call, Throwable t) {
                callback.onFailureOpenEvents();
            }
        });

    }

    public void getBestEvents(OpenEventsCallback<List<EventResponse>> callback) {
        this.service.getBestEvents().enqueue(new Callback<List<EventResponse>>() {
            @Override
            public void onResponse(Call<List<EventResponse>> call, Response<List<EventResponse>> response) {
                callback.onResponseOpenEvents(call, response);
            }

            @Override
            public void onFailure(Call<List<EventResponse>> call, Throwable t) {
                callback.onFailureOpenEvents();
            }
        });

    }

    public void searchEventsByString(String event, OpenEventsCallback<List<EventResponse>> callback) {
        this.service.searchEventsByString(event).enqueue(new Callback<List<EventResponse>>() {
            @Override
            public void onResponse(Call<List<EventResponse>> call, Response<List<EventResponse>> response) {
                callback.onResponseOpenEvents(call, response);
            }

            @Override
            public void onFailure(Call<List<EventResponse>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void editEvent(int id, CreateEventRequest createEventRequest, OpenEventsCallback<CreateEventResponse> callback) {
        this.service.editEvent(id, createEventRequest).enqueue(new Callback<CreateEventResponse>() {
            @Override
            public void onResponse(Call<CreateEventResponse> call, Response<CreateEventResponse> response) {
                callback.onResponseOpenEvents(call, response);
                System.out.println(response.body().getAllData());
                System.out.println(response.code());
            }

            @Override
            public void onFailure(Call<CreateEventResponse> call, Throwable t) {
                callback.onFailureOpenEvents();
                System.out.println(t.getMessage());
            }
        });
    }

    public void deleteEvent(int id, OpenEventsCallback callback) {
        this.service.deleteEvent(id).enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                callback.onResponseOpenEvents(call, response);
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                callback.onFailureOpenEvents();
            }
        });
    }

    public void assistEvent(int id, OpenEventsCallback callback) {
        this.service.assistEvent(id).enqueue(new Callback<AssistEventResponse>() {
            @Override
            public void onResponse(Call<AssistEventResponse> call, Response<AssistEventResponse> response) {
                callback.onResponseOpenEvents(call, response);
            }

            @Override
            public void onFailure(Call<AssistEventResponse> call, Throwable t) {
                callback.onFailureOpenEvents();
            }
        });
    }

    public void notAssistEvent(int id, OpenEventsCallback callback) {
        this.service.notAssistEvent(id).enqueue(new Callback<AssistEventResponse>() {
            @Override
            public void onResponse(Call<AssistEventResponse> call, Response<AssistEventResponse> response) {
                callback.onResponseOpenEvents(call, response);
            }

            @Override
            public void onFailure(Call<AssistEventResponse> call, Throwable t) {
                callback.onFailureOpenEvents();
            }
        });
    }

    public void getUserAssistances(int id, OpenEventsCallback callback) {
        this.service.getUserAssistances(id).enqueue(new Callback<List<EventResponse>>() {
            @Override
            public void onResponse(Call<List<EventResponse>> call, Response<List<EventResponse>> response) {
                callback.onResponseOpenEvents(call, response);
            }

            @Override
            public void onFailure(Call<List<EventResponse>> call, Throwable t) {
                callback.onFailureOpenEvents();
            }
        });

    }

}
