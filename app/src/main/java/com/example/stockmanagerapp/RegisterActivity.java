package com.example.stockmanagerapp;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText editName, editMail, editPassword;
    private Button submitButton;

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
    }

    private void userSignUp(){
        String name = editName.getText().toString().trim();
        String mail = editMail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (mail.isEmpty()){
            editMail.setError("indiquez une adresse e-mail !");
            editMail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
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

        if (name.isEmpty()){
            editName.setError("indiquez un nom");
            editName.requestFocus();
            return;
        }

        /* do user registration */

        
    }
}
