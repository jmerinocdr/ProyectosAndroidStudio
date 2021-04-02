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

public class SetUserActivity extends AppCompatActivity {

    EditText modUserName, modUserMail;
    String username, usermail;
    private LottieAnimationView checkUserName, checkUserMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user);

        username = getIntent().getStringExtra("userName");
        usermail = getIntent().getStringExtra("userMail");


        modUserName = (EditText) findViewById(R.id.modUserName);
        modUserMail = (EditText) findViewById(R.id.modUserMail);

        checkUserName = (LottieAnimationView) findViewById(R.id.checkModUser);
        checkUserMail = (LottieAnimationView) findViewById(R.id.checkModMail);

        modUserName.setText(username);
        modUserMail.setText(usermail);

    }

    public void checkUserData(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "twittor", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String username = modUserName.getText().toString();
        String usermail = modUserMail.getText().toString();
        if((this.username.equals(username) && this.usermail.equals(usermail)) || checkData(BaseDeDatos, username, usermail)) {
            checkAnimation(checkUserName, R.raw.cross, false);
            checkAnimation(checkUserMail, R.raw.cross, false);
            checkAnimation(checkUserName, R.raw.check, true);
            checkAnimation(checkUserMail, R.raw.check, true);
        }

        BaseDeDatos.close();
    }

    public void setUser(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "twittor", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String username = modUserName.getText().toString();
        String usermail = modUserMail.getText().toString();
        if((this.username.equals(username) && this.usermail.equals(usermail)) || checkData(BaseDeDatos, username, usermail)){
            ContentValues registro = new ContentValues();

            registro.put("username", username);
            registro.put("mail", usermail);

            int cantidad = BaseDeDatos.update("usuarios", registro, "username = '"+this.username+"'", null);
            BaseDeDatos.close();

            Intent user = new Intent(this, UserActivity.class);
            user.putExtra("userName", username);
            user.putExtra("userMail", usermail);
            startActivity(user);
            overridePendingTransition(R.anim.right_in, R.anim.right_out);
        }
        else{
            BaseDeDatos.close();
        }

    }



    private boolean checkData(SQLiteDatabase BaseDeDatos, String username, String usermail) {
        Cursor fila = BaseDeDatos.rawQuery("select username, mail from usuarios where (username = '"+username+"') or (mail='"+usermail+"')", null);
        if(!username.isEmpty()&&!usermail.isEmpty()) {
            if (!fila.moveToFirst() || fila.getCount() < 1) {
                checkAnimation(checkUserName, R.raw.cross, false);
                checkAnimation(checkUserMail, R.raw.cross, false);
                checkAnimation(checkUserName, R.raw.check, true);
                checkAnimation(checkUserMail, R.raw.check, true);
                return true;
            } else {
                checkAnimation(checkUserName, R.raw.check, false);
                checkAnimation(checkUserMail, R.raw.check, false);
                checkAnimation(checkUserName, R.raw.cross, true);
                checkAnimation(checkUserMail, R.raw.cross, true);
                Toast.makeText(this, "El nombre o el correo está en uso", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        else {
            checkAnimation(checkUserName, R.raw.check, false);
            checkAnimation(checkUserMail, R.raw.check, false);
            checkAnimation(checkUserName, R.raw.cross, true);
            checkAnimation(checkUserMail, R.raw.cross, true);
            Toast.makeText(this, "Comprueba el nombre o correo", Toast.LENGTH_SHORT).show();
            return false;
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


    public void Back(View view){
        Intent user = new Intent (this, UserActivity.class);
        user.putExtra("userName",username);
        user.putExtra("userMail", usermail);
        startActivity(user);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }
}