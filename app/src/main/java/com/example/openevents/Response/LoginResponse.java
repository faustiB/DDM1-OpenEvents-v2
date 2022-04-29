package com.example.openevents.Response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    @SerializedName("accessToken")
    private String accesToken;

    public String getAccesToken() {
        return accesToken;
    }
}
