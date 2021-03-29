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

import com.airbnb.lottie.LottieAnimationView;

public class SetPasswordActivity extends AppCompatActivity {

    String username, usermail;
    private EditText inUserPass;
    private EditText inUserRePass;
    private LottieAnimationView checkuserpass, checkuserrepass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        username = getIntent().getStringExtra("userName");
        usermail = getIntent().getStringExtra("userMail");

        inUserPass = (EditText)findViewById(R.id.inUserMail);
        inUserRePass = (EditText)findViewById(R.id.inUserRePass);

        checkuserpass = (LottieAnimationView) findViewById(R.id.checkuserpassico);
        checkuserrepass = (LottieAnimationView) findViewById(R.id.checkuserrepassico);
    }

    public void Back(View view){
        Intent intent=new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void Next(View view){
        String inuserpass = inUserPass.getText().toString();
        String inuserrepass = inUserRePass.getText().toString();
        if(!inuserpass.isEmpty()&&!inuserrepass.isEmpty()){
            if(inuserpass.equals(inuserrepass)){
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "twittor", null, 1);
                SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
                Cursor fila = BaseDeDatos.rawQuery("select username, mail from usuarios where (username = '"+username+"') and (mail='"+usermail+"')", null);
                if(!fila.moveToFirst()||fila.getCount()<1) {
                    checkAnimation(checkuserpass, R.raw.check, true);
                    checkAnimation(checkuserrepass, R.raw.check, true);

                    ContentValues registro = new ContentValues();
                    registro.put("username", username);
                    registro.put("mail", usermail);
                    registro.put("password", inuserpass);
                    BaseDeDatos.insert("usuarios", null, registro);
                    BaseDeDatos.close();
                    Toast.makeText(this, "Usuario "+username+" con "+usermail+" y "+inuserpass+" creado correctamente", Toast.LENGTH_SHORT).show();

                    Intent principal = new Intent(this, PrincipalActivity.class);
                    principal.putExtra("userName", username);
                    principal.putExtra("userMail", usermail);
                    principal.putExtra("userPass", inuserpass);
                    startActivity(principal);

                }
                else {
                    checkAnimation(checkuserpass, R.raw.check, false);
                    checkAnimation(checkuserrepass, R.raw.check, false);
                    Toast.makeText(this, "No se puede crear el usuario "+username+" porque ya existe", Toast.LENGTH_SHORT).show();
                }
            }else{
                checkAnimation(checkuserpass, R.raw.check, false);
                checkAnimation(checkuserrepass, R.raw.check, false);
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }else{
            checkAnimation(checkuserpass, R.raw.check, false);
            checkAnimation(checkuserrepass, R.raw.check, false);
            Toast.makeText(this, "Comprueba las contraseñas", Toast.LENGTH_SHORT).show();
        }
    }

    public void Check(View view){
        String inuserpass = inUserPass.getText().toString();
        String inuserrepass = inUserRePass.getText().toString();
        if(!inuserpass.isEmpty()&&!inuserrepass.isEmpty()){
            if(inuserpass.equals(inuserrepass)){
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "twittor", null, 1);
                SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
                Cursor fila = BaseDeDatos.rawQuery("select username, mail from usuarios where (username = '"+username+"') and (mail='"+usermail+"')", null);
                if(!fila.moveToFirst()||fila.getCount()<1) {
                    checkAnimation(checkuserpass, R.raw.check, true);
                    checkAnimation(checkuserrepass, R.raw.check, true);
                    BaseDeDatos.close();
                }
                else {
                    checkAnimation(checkuserpass, R.raw.check, false);
                    checkAnimation(checkuserrepass, R.raw.check, false);
                    Toast.makeText(this, "No se puede crear el usuario "+username+" porque ya existe", Toast.LENGTH_SHORT).show();
                }
            }else{
                checkAnimation(checkuserpass, R.raw.check, false);
                checkAnimation(checkuserrepass, R.raw.check, false);
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }else{
            checkAnimation(checkuserpass, R.raw.check, false);
            checkAnimation(checkuserrepass, R.raw.check, false);
            Toast.makeText(this, "Comprueba las contraseñas", Toast.LENGTH_SHORT).show();
        }
    }
    private void checkAnimation(LottieAnimationView imageView, int animation, boolean check){
        if(check){
            imageView.setAnimation(animation);
            imageView.playAnimation();
        }
        else {
            imageView.setImageResource(R.drawable.cross);
        }
    }
}