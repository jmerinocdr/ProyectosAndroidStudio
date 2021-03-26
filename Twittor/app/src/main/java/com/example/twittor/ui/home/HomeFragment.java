package com.example.twittor.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twittor.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    List<TwootElement> twoots;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        init(root, getActivity());

        return root;
    }

    private void init(View root, Context context) {
        twoots = new ArrayList<>();
        twoots.add(new TwootElement(
                1,
                "img",
                "User-Name",
                "User-Twoot qqppqpqpqpqpqpqpqpqpqppqpqqpqpq",
                0,
                5));
        twoots.add(new TwootElement(
                1,
                "img",
                "User-Name1",
                "User-Twoot1 qqppqpqpqpqpqpqpqpqpqppqpqqpqpq",
                1,
                4));
        twoots.add(new TwootElement(
                1,
                "img",
                "User-Name2",
                "User-Twoot2 qqppqpqpqpqpqpqpqpqpqppqpqqpqpq",
                2,
                3));
        twoots.add(new TwootElement(
                1,
                "img",
                "User-Name3",
                "User-Twoot3 qqppqpqpqpqpqpqpqpqpqppqpqqpqpq",
                3,
                2));
        twoots.add(new TwootElement(
                1,
                "img",
                "User-Name4",
                "User-Twoot4 qqppqpqpqpqpqpqpqpqpqppqpqqpqpq",
                4,
                1));
        twoots.add(new TwootElement(
                1,
                "img",
                "User-Name5",
                "User-Twoot5 qqppqpqpqpqpqpqpqpqpqppqpqqpqpq",
                4,
                1));
        twoots.add(new TwootElement(
                1,
                "img",
                "User-Name6",
                "User-Twoot6 qqppqpqpqpqpqpqpqpqpqppqpqqpqpq",
                4,
                1));
        twoots.add(new TwootElement(
                1,
                "img",
                "User-Name7",
                "User-Twoot7 qqppqpqpqpqpqpqpqpqpqppqpqqpqpq",
                4,
                1));

        TwootAdapter twootAdapter=new TwootAdapter(twoots, context);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.twootList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(twootAdapter);
    }
}