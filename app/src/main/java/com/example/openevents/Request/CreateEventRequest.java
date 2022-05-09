package com.example.openevents.Request;

public class CreateEventRequest {

    private String name;
    private String image;
    private String location;
    private String description;
    private String eventStart_date;
    private String eventEnd_date;
    private int n_participants;
    private String type;

    public CreateEventRequest(String name, String image, String location, String description, String eventStart_date, String eventEnd_date, int n_participants, String type) {
        this.name = name;
        this.image = image;
        this.location = location;
        this.description = description;
        this.eventStart_date = eventStart_date;
        this.eventEnd_date = eventEnd_date;
        this.n_participants = n_participants;
        this.type = type;
    }
}
