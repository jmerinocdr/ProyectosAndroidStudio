package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Num1;
    private EditText Num2;
    private TextView Resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Num1 = (EditText) findViewById(R.id.Num1);
        Num2 = (EditText) findViewById(R.id.Num2);
        Resultado = (TextView) findViewById(R.id.resultado);
    }

    //Método para realizar la suma
    public void Sumar(View vista){
        String sn1=Num1.getText().toString();
        String sn2= Num2.getText().toString();
        int n1 = Integer.parseInt(sn1);
        int n2 = Integer.parseInt(sn2);
        int output = n1+n2;
        String soutput = String.valueOf(output);
        Resultado.setText(soutput);
    }
}