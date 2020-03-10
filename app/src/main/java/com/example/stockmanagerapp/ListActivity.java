package com.example.stockmanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class ListActivity extends AppCompatActivity {

    private TextView count;
    private Button buttoncount;
    private Button backbutton;
    private int clicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        String URL="http://stockmanager.test:8080/materials";

        RequestQueue requestQueue= Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest=new JsonObjectRequest(
        Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void  onResponse(JSONObject response) {
                        Log.e("Rest Response", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Response", error.toString());
                    }
                }
        );

        requestQueue.add(objectRequest);

        this.count = (TextView) findViewById(R.id.count);
        this.buttoncount = (Button) findViewById(R.id.buttoncount);

        buttoncount.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clicks++;
            count.setText("clicks: " + clicks);
        }
    });
        this.backbutton = (Button) findViewById(R.id.backbutton);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });
}
}
