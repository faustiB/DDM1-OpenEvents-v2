package com.example.openevents.Request;

public class RegisterRequest {

    private String name;
    private String last_name;
    private String email;
    private String password;
    private String image;

    public RegisterRequest(String name, String last_name, String email, String password, String image) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.image = image;
    }
}
