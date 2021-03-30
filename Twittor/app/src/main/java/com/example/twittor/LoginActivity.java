package com.example.twittor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText userMail, userPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userMail = (EditText) findViewById(R.id.inUserMail);
        userPass = (EditText) findViewById(R.id.inUserPass);
    }

    public void Back(View view){
        Intent intent=new Intent(this, LoginCreateActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    public void Next(View view){
        String usermail = userMail.getText().toString();
        String userpass = userPass.getText().toString();

        if (!usermail.isEmpty() && !userpass.isEmpty()) {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "twittor", null, 1);
            SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
            Cursor fila = BaseDeDatos.rawQuery("select username, mail, password from usuarios where (mail='" + usermail + "')", null);
            if (fila.moveToFirst()) {
                if (fila.getString(2).equals(userpass)) {
                    Intent principal = new Intent(this, PrincipalActivity.class);
                    principal.putExtra("userName", fila.getString(0));
                    principal.putExtra("userMail", fila.getString(1));
                    principal.putExtra("userPass", fila.getString(2));
                    BaseDeDatos.close();
                    startActivity(principal);
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                } else {
                    Toast.makeText(this, "La contraseña no coincide", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "El usuario con correo " + usermail + " no existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

}