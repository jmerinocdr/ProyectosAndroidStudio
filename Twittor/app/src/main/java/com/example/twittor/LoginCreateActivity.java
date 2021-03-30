package com.example.twittor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_create);
    }

    public void gotoCrearCuenta(View view){
        Intent crearCuenta = new Intent(this, CreateAccountActivity.class);
        startActivity(crearCuenta);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }
    public void gotoLogin(View view){
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }
}