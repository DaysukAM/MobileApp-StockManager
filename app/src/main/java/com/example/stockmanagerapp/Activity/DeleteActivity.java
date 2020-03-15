package com.example.stockmanagerapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stockmanagerapp.Class.Materials;
import com.example.stockmanagerapp.R;
import com.example.stockmanagerapp.Network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteActivity extends AppCompatActivity {

    Button deleteButton;
    Button buttonBack;
    Spinner spinnerDelete;

    private TextView textViewLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        spinnerDelete = findViewById(R.id.spinnerDelete);

        Call<List<Materials>> call = RetrofitClient
                .getInstance()
                .getApi()
                .getMaterials();
        List<Materials> MaterialList = new ArrayList<>();
        call.enqueue(new Callback<List<Materials>>() {
            @Override
            public void onResponse(Call<List<Materials>> call, Response<List<Materials>> response) {
                List<Materials> materials = response.body();
                for (Materials material : materials){

                    MaterialList.add(material);
                }

            }

            @Override
            public void onFailure(Call<List<Materials>> call, Throwable t) {
            }
        });

        ArrayAdapter<Materials> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, MaterialList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerDelete.setAdapter(adapter);

        deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Materials material = (Materials) spinnerDelete.getSelectedItem();
                deleteMaterial(material);
            }
        });

        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });
    }


    private void deleteMaterial(Materials material) {

        System.out.println(material);
        int id = material.getId();
        String name = material.getName();
        textViewLog = findViewById(R.id.textViewLog);

        Call<Void> call = RetrofitClient
                .getInstance()
                .getApi()
                .deleteMaterials(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(DeleteActivity.this, name + "correctement supprimé", Toast.LENGTH_LONG).show();
                Intent otherActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(otherActivity);
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textViewLog.setText(t.getMessage());
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
