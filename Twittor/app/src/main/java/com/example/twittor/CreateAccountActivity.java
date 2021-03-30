package com.example.twittor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class CreateAccountActivity extends AppCompatActivity {
    private EditText userName, userMail;
    private LottieAnimationView checkusername, checkusermail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        userName = (EditText) findViewById(R.id.inUserMail);
        userMail = (EditText) findViewById(R.id.inUserPass);
        checkusername = (LottieAnimationView) findViewById(R.id.checkuserpassico);
        checkusermail = (LottieAnimationView) findViewById(R.id.checkuserrepassico);

        checkAnimation(checkusername, R.raw.cross, true);
        checkAnimation(checkusermail, R.raw.cross, true);
    }

    public void Back(View view){
        Intent intent=new Intent(this, LoginCreateActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    public void Next(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "twittor", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String username=userName.getText().toString();
        String usermail=userMail.getText().toString();
        if(!userName.getText().toString().isEmpty()&&!userMail.getText().toString().isEmpty()) {
            Cursor fila = BaseDeDatos.rawQuery("select username, mail from usuarios where (username = '"+username+"') or (mail='"+usermail+"')", null);
            if(!fila.moveToFirst()||fila.getCount()<1) {
                Intent setPassword = new Intent(this, SetPasswordActivity.class);
                setPassword.putExtra("userName", userName.getText().toString());
                checkAnimation(checkusername, R.raw.check, true);
                setPassword.putExtra("userMail", userMail.getText().toString());
                checkAnimation(checkusermail, R.raw.check, true);
                startActivity(setPassword);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
                BaseDeDatos.close();
            }
            else {
                checkAnimation(checkusername, R.raw.check, false);
                checkAnimation(checkusermail, R.raw.check, false);
                Toast.makeText(this, "El nombre o el correo está en uso", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            checkAnimation(checkusername, R.raw.check, false);
            checkAnimation(checkusermail, R.raw.check, false);
            Toast.makeText(this, "Comprueba el Nombre y Correo", Toast.LENGTH_SHORT).show();
        }
    }

    public void Check(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "twittor", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String username=userName.getText().toString();
        String usermail=userMail.getText().toString();

        if(!username.isEmpty()&&!usermail.isEmpty()) {
            Cursor fila = BaseDeDatos.rawQuery("select username, mail from usuarios where username = '"+username+"' or mail ='"+usermail+"'", null);

            if(!fila.moveToFirst()||fila.getCount()<1) {
                checkAnimation(checkusername, R.raw.check, true);
                checkAnimation(checkusermail, R.raw.check, true);
            }
            else {
                checkAnimation(checkusername, R.raw.cross, true);
                checkAnimation(checkusermail, R.raw.cross, true);
                Toast.makeText(this, "El nombre o el correo está en uso", Toast.LENGTH_SHORT).show();
            }
            BaseDeDatos.close();
        }
        else {
            checkAnimation(checkusername, R.raw.cross, true);
            checkAnimation(checkusermail, R.raw.cross, true);
            Toast.makeText(this, "Comprueba el Nombre y Correo", Toast.LENGTH_SHORT).show();
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