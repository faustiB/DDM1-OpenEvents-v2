package com.example.openevents.Request;

public class EditUserRequest {
    private final String name;
    private final String last_name;
    private final String email;
    private String password;
    private final String image;

    public EditUserRequest(String name, String last_name, String email, String password, String image) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public EditUserRequest(String name, String last_name, String email, String image) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.image = image;
    }
}
