package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.intent.SegundoActivity;

public class MainActivity extends AppCompatActivity {
    private EditText int1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int1 = (EditText)findViewById(R.id.InputText);
    }

    public void Siguiente(View view){

        Intent siguiente = new Intent(this, SegundoActivity.class);
        siguiente.putExtra("data", int1.getText().toString());
        siguiente.putExtra("pepe", "pepo");
        startActivity(siguiente);
    }
}