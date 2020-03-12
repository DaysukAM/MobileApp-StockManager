package com.example.stockmanagerapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddActivity extends AppCompatActivity {

    String name;
    String desc;
    EditText editName;
    EditText editDesc;

    Button submitButton;

    private TextView textViewLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editName = (EditText) findViewById(R.id.editName);
        editDesc = (EditText) findViewById(R.id.editDesc);

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editName.getText().toString();
                desc = editDesc.getText().toString();

                createMaterial();
            }
        });

    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.66.24.112/StockManager/server.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    StockManagerApi stockManagerApi = retrofit.create(StockManagerApi.class);

    private void createMaterial() {

        textViewLog = findViewById(R.id.textViewLog);
        Materials materials = new Materials(name, desc);


        Call<Materials> call = stockManagerApi.createMaterial(materials);

        call.enqueue(new Callback<Materials>() {
            @Override
            public void onResponse(Call<Materials> call, Response<Materials> response) {
                if(!response.isSuccessful()){
                    textViewLog.setText("code: " + response.code());
                    return;
                }
                Materials materialsResponse = response.body();
                String data = "";
                data = data + "Code: " + response.code() + "\n";
                data = data + "name: " + materialsResponse.getName() + "\n";
                data = data + ("desc: " + materialsResponse.getDesc() + "\n\n");

                textViewLog.setText(data);
            }

            @Override
            public void onFailure(Call<Materials> call, Throwable t) {
                textViewLog.setText(t.getMessage());
            }
        });
    }

}