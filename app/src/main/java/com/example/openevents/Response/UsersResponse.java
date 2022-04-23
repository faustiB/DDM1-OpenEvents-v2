package com.example.openevents.Response;

public class UsersResponse {

    private int id;
    private String name;
    private String last_name;
    private String email;
    private String image;

    public UsersResponse(int id, String name, String last_name, String image) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }
}