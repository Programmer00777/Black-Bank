package com.blackbank.app.models;

import lombok.Data;

import java.util.Date;

@Data
public class Client {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date regDate;
    private Status status;
    private Role role;
    // how to add an ability to upload photos to profile
}
