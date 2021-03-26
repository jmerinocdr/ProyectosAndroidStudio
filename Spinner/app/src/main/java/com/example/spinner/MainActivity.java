package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText num1, num2;
    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = (EditText) findViewById(R.id.intNum1);
        num2 = (EditText) findViewById(R.id.intNum2);
        output = (TextView) findViewById(R.id.Output);
        spinner = (Spinner) findViewById(R.id.spinner);

        String[] opciones = {"sumar", "restar", "multiplicar", "dividir"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spinner.setAdapter(adapter);
    }

    //Método del botón
    public void Calcular(View view){
        String num1str = num1.getText().toString();
        String num2str = num2.getText().toString();

        int num1int = Integer.parseInt(num1str);
        int num2int = Integer.parseInt(num2str);

        String seleccion = spinner.getSelectedItem().toString();
        if(seleccion.equals("sumar")){
            int suma = num1int + num2int;
            String resultado = String.valueOf(suma);
            output.setText(resultado);
        }
        else if(seleccion.equals("restar")){
            int suma = num1int - num2int;
            String resultado = String.valueOf(suma);
            output.setText(resultado);
        }
        else if(seleccion.equals("multiplicar")){
            int suma = num1int * num2int;
            String resultado = String.valueOf(suma);
            output.setText(resultado);
        }
        else if(seleccion.equals("dividir")){
            if(num1int==0 || num2int==0){
                Toast.makeText(this, "No se puede dividir con cero", Toast.LENGTH_LONG).show();
            }
            else {
                int suma = num1int / num2int;
                String resultado = String.valueOf(suma);
                output.setText(resultado);
            }
        }
    }
}