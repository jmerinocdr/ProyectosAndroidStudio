package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText)findViewById(R.id.input);

        SharedPreferences preferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        input.setText(preferences.getString("txt", "Introduce Texto"));
    }

    public void Guardar(View view){
        SharedPreferences preferences2 = getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences2.edit();
        editor.putString("txt", input.getText().toString());
        editor.commit();
        finish();
    }
}