package com.example.openevents.Response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EventResponse implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("owner_id")
    private int owner_id;

    @SerializedName("date")
    private String date;

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

    @SerializedName("slug")
    private String slug;

    @SerializedName("type")
    private String type;


    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getImage() {
        return image;
    }

    public String getEventStart_date() {
        return eventStart_date;
    }

    public String getDescription() {
        return description;
    }

    public String getEventEnd_date() {
        return eventEnd_date;
    }

    public int getId() {
        return id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public int getN_participators() {
        return n_participators;
    }
}