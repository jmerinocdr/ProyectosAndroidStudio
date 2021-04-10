package com.example.twittor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewTwootActivity extends AppCompatActivity {
    String twootText, username, usermail;
    EditText editTextNewTwoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_twoot);

        editTextNewTwoot = (EditText)findViewById(R.id.editTextTwoot);
        username = getIntent().getStringExtra("userName");
        usermail = getIntent().getStringExtra("userMail");
    }
    public void goBack(View view){
        Intent principalActivity = new Intent(this, PrincipalActivity.class);
        principalActivity.putExtra("userName", username);
        principalActivity.putExtra("userMail", usermail);
        startActivity(principalActivity);
    }
    public void addNewTwoot(View view){
        twootText = editTextNewTwoot.getText().toString();
        if(username!=null&&twootText!=null){
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "twittor", null, 1);
            SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("twoottext", twootText);
            registro.put("retwoots", 0);
            registro.put("likes", 0);
            registro.put("username", username);
            BaseDeDatos.insert("twoots", null, registro);
            Toast.makeText(this, "Escrito "+twootText+" a "+username, Toast.LENGTH_SHORT).show();
            BaseDeDatos.close();
            Intent principalActivity = new Intent(this, PrincipalActivity.class);
            principalActivity.putExtra("userName", username);
            principalActivity.putExtra("userMail", usermail);
            startActivity(principalActivity);
        }
    }
}