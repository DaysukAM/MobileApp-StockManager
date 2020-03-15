package com.example.stockmanagerapp.Class;

public class Materials {

    private int id;
    private String name;
    private String desc;

    public Materials(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }


@Override
    public String toString(){
        return name;
}
}