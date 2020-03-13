package com.example.stockmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private EditText editName, editMail, editPassword;
    private Button submitButton;
    private Button buttonLoginLink;
    private TextView textViewLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editName = (EditText) findViewById(R.id.editName);
        editMail = (EditText) findViewById(R.id.editMail);
        editPassword= (EditText) findViewById(R.id.editPassword);

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignUp();
            }
        });

        buttonLoginLink = (Button) findViewById(R.id.buttonLoginLink);
        buttonLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });
    }
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.66.24.112/StockManager/server.php/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();


    StockManagerApi stockManagerApi = retrofit.create(StockManagerApi.class);


    private void userSignUp(){
        textViewLog = findViewById(R.id.textViewLog);
        String name = editName.getText().toString().trim();
        String email = editMail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (name.isEmpty()){
            editName.setError("indiquez un nom");
            editName.requestFocus();
            return;
        }

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

        if (password.length() < 6){
            editPassword.setError("indiquez un mot de passe de plus de 5 caractères ");
            editPassword.requestFocus();
            return;
        }



        Call<ResponseBody> call = stockManagerApi.createUser(name, email, password);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    textViewLog.setText("Création de l'utilisateur " + name + " réussi");
                if(!response.isSuccessful()) {
                    textViewLog.setText("code: " + response.code());
                    return;
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                textViewLog.setText(t.getMessage());
            }
        });

    }
}
