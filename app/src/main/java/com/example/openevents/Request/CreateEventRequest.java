package com.example.openevents.Request;

public class CreateEventRequest {

    private final String name;
    private final String image;
    private final String location;
    private final String description;
    private final String eventStart_date;
    private final String eventEnd_date;
    private final int n_participators;
    private final String type;

    public CreateEventRequest(String name, String image, String location, String description, String eventStart_date, String eventEnd_date, int n_participators, String type) {
        this.name = name;
        this.image = image;
        this.location = location;
        this.description = description;
        this.eventStart_date = eventStart_date;
        this.eventEnd_date = eventEnd_date;
        this.n_participators = n_participators;
        this.type = type;
    }
}
