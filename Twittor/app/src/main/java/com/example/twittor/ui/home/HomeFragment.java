package com.example.twittor.ui.home;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twittor.AdminSQLiteOpenHelper;
import com.example.twittor.R;
import com.example.twittor.Twoots;
import com.example.twittor.TwootsAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    TwootsAdapter twootsAdapter;
    RecyclerView recyclerViewTwoots;

    ArrayList<Twoots> twootsList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        //Establecemos las cosas para mostrar la lista de twoots
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewTwoots = view.findViewById(R.id.twootsRecyclerView);
        twootsList = getTwoots();

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("");

        //for(int i=0; twootsList.size()>i; i++){
            //Toast.makeText(getActivity(), "Twoot"+i+" es de "+twootsList.get(i).getUsername(), Toast.LENGTH_SHORT).show();
        //}

        recyclerViewTwoots.setLayoutManager(new LinearLayoutManager(getContext()));
        twootsAdapter=new TwootsAdapter(getContext(), twootsList);
        recyclerViewTwoots.setAdapter(twootsAdapter);

        //OnCLickListener que funciona correctamente con el twoot
        twootsAdapter.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = twootsList.get(recyclerViewTwoots.getChildAdapterPosition(v)).getId();
                String nombre = twootsList.get(recyclerViewTwoots.getChildAdapterPosition(v)).getUsername();
                Toast.makeText(getActivity(), "Click en twoot "+id+" del usuario "+nombre, Toast.LENGTH_SHORT).show();
            }
        });

        /*
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
                Toast.makeText(getActivity(), "XD", Toast.LENGTH_SHORT).show();
            }
        });
         */

        return view;
    }

    private void mostrarData() {
        recyclerViewTwoots.setLayoutManager(new LinearLayoutManager(getContext()));
        twootsAdapter=new TwootsAdapter(getContext(), twootsList);
        recyclerViewTwoots.setAdapter(twootsAdapter);
    }

    //Devuelve la lista de Twoots
    public ArrayList<Twoots> getTwoots(){

        ArrayList<Twoots> twootsListTMP = new ArrayList<>();

        //Toast.makeText(getActivity(), "Establecemos la lista de twoots", Toast.LENGTH_SHORT).show();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity(), "twittor", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor fila = BaseDeDatos.rawQuery("select t.twootid, t.twoottext, t.retwoots, t.likes, t.username, u.uphoto, u.mail from twoots t, usuarios u where t.username = u.username group by t.twootid order by t.twootid desc", null);
        int ntwoots=0;
        while(fila.moveToNext()){
            int id = fila.getInt(0);
            String twoottext = fila.getString(1);
            int retwoots = fila.getInt(2);
            int likes = fila.getInt(3);
            String username = fila.getString(4);
            String userimage = fila.getString(5);
            String usermail = fila.getString(6);
            Twoots twoots = new Twoots(id, username, userimage, usermail, twoottext, retwoots, likes);
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
}