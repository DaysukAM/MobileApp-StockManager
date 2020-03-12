package com.example.stockmanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button list = findViewById(R.id.list);

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });

        Button add = findViewById(R.id.buttonadd);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });

        Button delete = findViewById(R.id.buttondelete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), DeleteActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });
    }
}
