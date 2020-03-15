package com.example.stockmanagerapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stockmanagerapp.Activity.AddActivity;
import com.example.stockmanagerapp.Activity.DeleteActivity;
import com.example.stockmanagerapp.Activity.ListActivity;
import com.example.stockmanagerapp.R;

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

        Button rent = findViewById(R.id.buttonrent);

        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), RentActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent otherActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(otherActivity);
        finish();
    }
}
