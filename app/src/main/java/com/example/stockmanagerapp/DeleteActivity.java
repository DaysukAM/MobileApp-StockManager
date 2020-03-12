package com.example.stockmanagerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeleteActivity extends AppCompatActivity {

    Button deleteButton;

    private TextView textViewLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMaterial();
            }
        });

    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.66.24.112/StockManager/server.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    StockManagerApi stockManagerApi = retrofit.create(StockManagerApi.class);

    private void deleteMaterial() {

        textViewLog = findViewById(R.id.textViewLog);


        Call<Void> call = stockManagerApi.deleteMaterials(10);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textViewLog.setText("code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textViewLog.setText(t.getMessage());
            }
        });

    }
}