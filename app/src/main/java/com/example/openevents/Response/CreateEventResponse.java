package com.example.openevents.Response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CreateEventResponse implements Serializable {
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;
    @SerializedName("location")
    private String location;
    @SerializedName("description")
    private String description;
    @SerializedName("eventStart_date")
    private String eventStart_date;
    @SerializedName("eventEnd_date")
    private String eventEnd_date;
    @SerializedName("n_participators")
    private int n_participators;
    @SerializedName("type")
    private String type;
    @SerializedName("owner_id")
    private int owner_id;
    @SerializedName("date")
    private String date;


    public String getName() {
        return name;
    }

    public String getAllData() {
        return "Name: " + name + "\n" +
                "Image: " + image + "\n" +
                "Location: " + location + "\n" +
                "Description: " + description + "\n" +
                "EventStart_date: " + eventStart_date + "\n" +
                "EventEnd_date: " + eventEnd_date + "\n" +
                "N_participators: " + n_participators + "\n" +
                "Type: " + type + "\n" +
                "Owner_id: " + owner_id + "\n" +
                "Date: " + date;
    }
}


