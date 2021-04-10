package com.example.twittor.ui.slideshow;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    String username, usermail;

    TwootsAdapter twootsAdapter;
    RecyclerView recyclerViewTwoots;

    ArrayList<Twoots> twootsList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_slideshow, container, false);

        //recyclerViewTwoots = view.findViewById(R.id.misTwootsRecyclerView);

        //username = getActivity().getIntent().getStringExtra("userName");
        //usermail = getActivity().getIntent().getStringExtra("userMail");



        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("");

        //recyclerViewTwoots.setLayoutManager(new LinearLayoutManager(getContext()));
        //twootsAdapter=new TwootsAdapter(getContext(), twootsList);
        //recyclerViewTwoots.setAdapter(twootsAdapter);

        /*
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

         */
        return view;
    }

    public ArrayList<Twoots> getTwoots(){

        ArrayList<Twoots> twootsListTMP = new ArrayList<>();

        //Toast.makeText(getActivity(), "Establecemos la lista de twoots", Toast.LENGTH_SHORT).show();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity(), "twittor", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor fila = BaseDeDatos.rawQuery("select t.twootid, t.twoottext, t.retwoots, t.likes, t.username, u.uphoto, u.mail from twoots t, usuarios u where t.username = u.username and t.username='"+username+"' group by t.twootid order by t.twootid desc", null);
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