package com.example.stockmanagerapp.Response;

import com.example.stockmanagerapp.Class.User;

public class LoginResponse {

    private String status, message;
    private User user;

    public LoginResponse(String status, String message, User user) {
        this.status = status;
        this.message = message;
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
