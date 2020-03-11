package com.example.stockmanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {

    private Button backbutton;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        textViewResult = findViewById(R.id.textViewResult);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.66.24.112/StockManager/server.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StockManagerApi stockManagerApi = retrofit.create(StockManagerApi.class);

        Call<List<Materials>> call = stockManagerApi.getMaterials();

        call.enqueue(new Callback<List<Materials>>() {
            @Override
            public void onResponse(Call<List<Materials>> call, Response<List<Materials>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("code: " + response.code());
                }
                List<Materials> materials = response.body();

                for (Materials material : materials){
                    String data = "";
                    data = data + "id" + material.getId() + "\n";
                    data = data + "name" + material.getName() + "\n";
                    data = data + ("desc" + material.getDesc() + "\n\n");

                    textViewResult.append(data);

                }

            }

            @Override
            public void onFailure(Call<List<Materials>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
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
