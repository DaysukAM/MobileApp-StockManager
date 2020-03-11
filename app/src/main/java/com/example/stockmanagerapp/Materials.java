package com.example.stockmanagerapp;

import com.google.gson.annotations.SerializedName;

public class Materials {

    private int id;
    private String name;

    @SerializedName("body")
    private String desc;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
