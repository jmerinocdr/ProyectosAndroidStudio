package com.example.twittor;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.twittor.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PrincipalActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    String username, usermail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar); 
        setSupportActionBar(toolbar);


        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
         */
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        setUserData();
        View headerView = navigationView.getHeaderView(0);
        TextView navUserName = (TextView)headerView.findViewById(R.id.navUserName);
        TextView navUserMail = (TextView)headerView.findViewById(R.id.navUserMail);
        navUserName.setText(username);
        navUserMail.setText(usermail);

        Bundle data = new Bundle();
        data.putString("userName", username);
        data.putString("userMail", usermail);
        Fragment slideshow=new Fragment(R.layout.fragment_slideshow);
        slideshow.setArguments(data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void setUserData(){
        username = getIntent().getStringExtra("userName");
        usermail = getIntent().getStringExtra("userMail");
    }
    public void newTwoot (View view){
        //Toast.makeText(this, "NuevoTwoot", Toast.LENGTH_SHORT).show();
        Intent newTwoot = new Intent (this, NewTwootActivity.class);
        newTwoot.putExtra("userName",username);
        newTwoot.putExtra("userMail", usermail);
        startActivity(newTwoot);
    }
    public void refreshTwoots(View view){
        Toast.makeText(this, "RefreshTwoots", Toast.LENGTH_SHORT).show();
    }
    public void gotoUser(View view){
        Toast.makeText(this, "Usuario", Toast.LENGTH_SHORT).show();
        Intent user = new Intent (this, UserActivity.class);
        user.putExtra("userName",username);
        user.putExtra("userMail", usermail);
        startActivity(user);
    }

    public void closeSession(MenuItem item){
        Intent createLogin=new Intent(this, LoginCreateActivity.class);
        startActivity(createLogin);
    }

    public String getUserName(){
        return username;
    }
    public String getUserMail(){
        return usermail;
    }
}