package com.example.stockmanagerapp.Response;

public class RentResponse {

    private String status, message;

    public RentResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
