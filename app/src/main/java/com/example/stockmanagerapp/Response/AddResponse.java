package com.example.stockmanagerapp.Response;

import com.example.stockmanagerapp.Class.Materials;

public class AddResponse {

    private String status, message;
    private Materials material;

    public AddResponse(String status, String message, Materials user) {
        this.status = status;
        this.message = message;
        this.material = user;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Materials getUser() {
        return material;
    }
}


