package com.example.stockmanagerapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stockmanagerapp.Class.Materials;
import com.example.stockmanagerapp.R;
import com.example.stockmanagerapp.Network.RetrofitClient;
import com.example.stockmanagerapp.Response.AddResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {

    String name, desc, status;
    EditText editName;
    EditText editDesc;

    Button submitButton;
    Button buttonBack;

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

    private void createMaterial() {

        textViewLog = findViewById(R.id.textViewLog);
        Materials materials = new Materials(name, desc);

        if (name.isEmpty()){
            editName.setError("indiquez un nom !");
            editName.requestFocus();
            return;
        }

        if (desc.isEmpty()){
            editDesc.setError("indiquez une description");
            editDesc.requestFocus();
            return;
        }
        Call<AddResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createMaterial(materials);

        call.enqueue(new Callback<AddResponse>() {
            @Override
            public void onResponse(Call<AddResponse> call, Response<AddResponse> response) {
                AddResponse addResponse = response.body();
                status = addResponse.getStatus();
                if(status.equals("succes")) {

                    Toast.makeText(AddActivity.this, addResponse.getMessage(), Toast.LENGTH_LONG).show();
                    Intent otherActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(otherActivity);
                    finish();
                }

            }

            @Override
            public void onFailure(Call<AddResponse> call, Throwable t) {
                textViewLog.setText(t.getMessage());
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent otherActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(otherActivity);
        finish();
    }
}
