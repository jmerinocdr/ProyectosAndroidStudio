package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText cod, desc, precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cod = (EditText) findViewById(R.id.cod);
        desc = (EditText) findViewById(R.id.desc);
        precio = (EditText) findViewById(R.id.precio);
    }

    //Método para registrar un producto
    public void Register(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionDB", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String codstr = cod.getText().toString();
        String descstr = desc.getText().toString();
        String preciostr = precio.getText().toString();

        if(!codstr.isEmpty() && !descstr.isEmpty() && !preciostr.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("codigo", codstr);
            registro.put("description", descstr);
            registro.put("precio", preciostr);

            BaseDeDatos.insert("articulos", null, registro);
            BaseDeDatos.close();
            cod.setText("");
            desc.setText("");
            precio.setText("");

            Toast.makeText(this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Rellena correctamente los datos", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo para consultar un artículo o producto
    public void Search(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionDB", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String codstr = cod.getText().toString();
        if(!codstr.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery("select description, precio from articulos where codigo ="+codstr, null);

            if(fila.moveToFirst()){
                desc.setText(fila.getString(0));
                precio.setText(fila.getString(1));
                BaseDeDatos.close();
            }else {
                Toast.makeText(this, "No se encontró el producto", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        }
        else{
            Toast.makeText(this, "Introduce un código de producto", Toast.LENGTH_SHORT).show();
            BaseDeDatos.close();
        }
    }

    //Método para buscar un artículo o producto
    public void Delete(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionDB", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codstr = cod.getText().toString();

        if(!codstr.isEmpty()){

            int cantidad = BaseDeDatos.delete("articulos", "codigo="+codstr, null);
            BaseDeDatos.close();

            cod.setText("");
            desc.setText("");
            precio.setText("");

            if(cantidad>=1){
                Toast.makeText(this, "Se eliminaron "+cantidad+" productos", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No se elininó ningún producto", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Introduce un código de producto", Toast.LENGTH_SHORT).show();
            BaseDeDatos.close();
        }
    }

    //Método para registrar un producto
    public void Modify(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracionDB", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String codstr = cod.getText().toString();
        String descstr = desc.getText().toString();
        String preciostr = precio.getText().toString();

        if(!codstr.isEmpty() && !descstr.isEmpty() && !preciostr.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("codigo", codstr);
            registro.put("description", descstr);
            registro.put("precio", preciostr);

            int cantidad = BaseDeDatos.update("articulos", registro, "codigo = "+codstr, null);
            BaseDeDatos.close();

            cod.setText("");
            desc.setText("");
            precio.setText("");

            if(cantidad>=1){
                Toast.makeText(this, "Se modificaron "+cantidad+" productos", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No se modificó ningún producto", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Rellena correctamente los datos", Toast.LENGTH_SHORT).show();
        }
    }
}