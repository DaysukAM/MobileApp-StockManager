package com.example.stockmanagerapp.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stockmanagerapp.R;

public class RentActivity extends AppCompatActivity {

    String Material_id, User_id;

    @Override
    public void onCreate (Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_rent);
    }
}
