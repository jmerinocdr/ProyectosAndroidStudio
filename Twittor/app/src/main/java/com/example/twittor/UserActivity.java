package com.example.twittor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.twittor.ui.home.HomeViewModel;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    private HomeViewModel homeViewModel;
    TwootsAdapter twootsAdapter;
    RecyclerView recyclerViewTwoots;
    ArrayList<Twoots> twoots = new ArrayList<>();
    Dialog mydialog;

    TextView userName, userMail, userTwootsCount;
    LottieAnimationView carga;
    String username, usermail, usertwootcount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        username = getIntent().getStringExtra("userName");
        usermail = getIntent().getStringExtra("userMail");

        userName = (TextView)findViewById(R.id.userName);
        userMail = (TextView)findViewById(R.id.userMail);
        userTwootsCount = (TextView)findViewById(R.id.userTwootsCount);




        userName.setText(username);
        userMail.setText(usermail);

        mydialog = new Dialog(this);


        recyclerViewTwoots = findViewById(R.id.twootsRecyclerView);


        twoots=getTwoots(username);

        userTwootsCount.setText("En total este usuario tiene "+twoots.size()+" twoots");

        recyclerViewTwoots.setLayoutManager(new LinearLayoutManager(this));
        twootsAdapter=new TwootsAdapter(this, twoots);
        recyclerViewTwoots.setAdapter(twootsAdapter);


    }



    public ArrayList<Twoots> getTwoots(String username){

        ArrayList<Twoots> twootsListTMP = new ArrayList<>();

        //Toast.makeText(getActivity(), "Establecemos la lista de twoots", Toast.LENGTH_SHORT).show();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "twittor", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor fila = BaseDeDatos.rawQuery("select t.twootid, t.twoottext, t.retwoots, t.likes, t.username, u.uphoto, u.mail from twoots t, usuarios u where t.username = u.username and t.username='"+username+"' group by t.twootid order by t.twootid desc", null);
        int ntwoots=0;
        while(fila.moveToNext()){
            int id = fila.getInt(0);
            String twoottext = fila.getString(1);
            int retwoots = fila.getInt(2);
            int likes = fila.getInt(3);
            String itemusername = fila.getString(4);
            String userimage = fila.getString(5);
            String usermail = fila.getString(6);
            Twoots twoots = new Twoots(id, itemusername, userimage, usermail, twoottext, retwoots, likes);
            twootsListTMP.add(twoots);
            ntwoots++;
            //Toast.makeText(getActivity(), "Hay "+ntwoots+" twoots -->"+id+" "+twoottext+" "+retwoots+" "+likes+" "+username, Toast.LENGTH_SHORT).show();
        }

         /*
        for (int i=0; 10>i; i++){
            twootsListTMP.add(new Twoots(i, "Username"+i, "userimage"+i, "usermail"+i, "twoottext"+i, 0, 0));
        }

          */
        return twootsListTMP;
    }

    public void setUser(View view){
        Intent setUsert = new Intent (this, SetUserActivity.class);
        setUsert.putExtra("userName",username);
        setUsert.putExtra("userMail", usermail);
        startActivity(setUsert);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }
    public void delUser(View view){
        mydialog.setContentView(R.layout.confirm_delete);
        mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mydialog.show();
    }
    public void hideDelUser(View view){
        mydialog.hide();
    }

    public void Back(View view){
        Intent newTwoot = new Intent (this, PrincipalActivity.class);
        newTwoot.putExtra("userName",username);
        newTwoot.putExtra("userMail", usermail);
        startActivity(newTwoot);
    }
    public void confirmDelUser(View view){
        Toast.makeText(this, "Eliminaría usuario "+username, Toast.LENGTH_SHORT).show();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "twittor", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        int cantidadtwoots = BaseDeDatos.delete("twoots", "username='"+username+"'", null);
        int cantidad = BaseDeDatos.delete("usuarios", "username='"+username+"'", null);
        BaseDeDatos.close();
        Intent inicio = new Intent(this, LoginCreateActivity.class);
        startActivity(inicio);
    }
}