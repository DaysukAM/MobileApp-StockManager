package com.example.stockmanagerapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stockmanagerapp.Class.User;
import com.example.stockmanagerapp.Global;
import com.example.stockmanagerapp.Network.RetrofitClient;
import com.example.stockmanagerapp.R;
import com.example.stockmanagerapp.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private String status;
    private EditText editMail, editPassword;
    private Button submitButton, buttonBack;
    private TextView textViewLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editMail = (EditText) findViewById(R.id.editMail);
        editPassword= (EditText) findViewById(R.id.editPassword);

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });

    }

    private  void userLogin(){

        textViewLog = findViewById(R.id.textViewLog);
        String email = editMail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();


        if (email.isEmpty()){
            editMail.setError("indiquez une adresse e-mail !");
            editMail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editMail.setError("indiquez une adresse e-mail valide !");
            editMail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editPassword.setError("indiquez un mot de passe");
            editPassword.requestFocus();
            return;
        }

        Call<LoginResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .userLogin(email, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                status = loginResponse.getStatus();


                if(status.equals("succes")){

                    User user = loginResponse.getUser();
                    int userId = user.getId();
                    Global.id = userId;
                    Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                    Intent otherActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(otherActivity);
                    finish();

                }else{
                    Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent otherActivity = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(otherActivity);
        finish();
    }

}
