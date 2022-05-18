package com.example.openevents.API;

import retrofit2.Call;
import retrofit2.Response;

public interface OpenEventsCallback<T> {


    void onResponseOpenEvents(Call<T> call, Response<T> response);


    void onFailureOpenEvents();


}


