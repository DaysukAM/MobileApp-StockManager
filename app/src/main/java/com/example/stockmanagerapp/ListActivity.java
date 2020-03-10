package com.example.stockmanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {

    private TextView count;
    private Button buttoncount;
    private int clicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        this.count = (TextView) findViewById(R.id.count);
        this.buttoncount = (Button) findViewById(R.id.buttoncount);

        buttoncount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicks++;
                count.setText("clicks: " + clicks);
            }
        });
    }
}
