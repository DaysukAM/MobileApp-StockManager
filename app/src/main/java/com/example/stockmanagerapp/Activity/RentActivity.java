package com.example.stockmanagerapp.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stockmanagerapp.Class.User;
import com.example.stockmanagerapp.Global;
import com.example.stockmanagerapp.Network.RetrofitClient;
import com.example.stockmanagerapp.R;
import com.example.stockmanagerapp.Response.LoginResponse;
import com.example.stockmanagerapp.Response.RentResponse;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RentActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    int Material_id, User_id;
    String status;

    ZXingScannerView scannerView;
    @Override
    public void onCreate (Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }


    @Override
    public void handleResult(Result result) {

        Material_id = Integer.parseInt(result.getText());
        User_id = Global.id;

        Call<RentResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .rentMaterial(Material_id, User_id);

        call.enqueue(new Callback<RentResponse>() {
            @Override
            public void onResponse(Call<RentResponse> call, Response<RentResponse> response) {
                RentResponse RentResponse = response.body();
                status = RentResponse.getStatus();

                if(status.equals("succes")){


                    Toast.makeText(RentActivity.this, RentResponse.getMessage(), Toast.LENGTH_LONG).show();
                    Intent otherActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(otherActivity);
                    finish();

                }else{
                    Toast.makeText(RentActivity.this, RentResponse.getMessage(), Toast.LENGTH_LONG).show();
                    Intent otherActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(otherActivity);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<RentResponse> call, Throwable t) {

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
