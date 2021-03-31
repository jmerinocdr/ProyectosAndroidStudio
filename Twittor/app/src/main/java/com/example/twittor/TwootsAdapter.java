package com.example.twittor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TwootsAdapter extends RecyclerView.Adapter<TwootsAdapter.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Twoots> model;
    private View.OnClickListener itemListener;
    private AdapterView.OnItemClickListener itemClickListener;

    public void setOnclickListener(View.OnClickListener listener){ this.itemListener=listener; }

    public TwootsAdapter(Context context, ArrayList<Twoots> model){
        this.inflater = LayoutInflater.from(context);
        this.model=model;
    }

    @Override
    public void onClick(View v) {
        if(itemListener!=null){
            itemListener.onClick(v);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.twoot_element, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String username = model.get(position).getUsername();
        String usermail = model.get(position).getUsermail();
        String twoottext = model.get(position).getTwoottext();
        int retwoots = model.get(position).getRetwoots();
        int likes = model.get(position).getLikes();
        holder.username.setText(username);
        holder.usermail.setText(usermail);
        holder.twoottext.setText(String.valueOf(twoottext));
        holder.retwoots.setText(String.valueOf(retwoots));
        holder.likes.setText(String.valueOf(likes));
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView username, usermail, twoottext, retwoots, likes;
        ImageView retwootImg, likeImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = (TextView)itemView.findViewById(R.id.twootUserName);
            usermail = (TextView)itemView.findViewById(R.id.twootUserMail);
            twoottext = (TextView)itemView.findViewById(R.id.twootUserTwoot);
            retwoots = (TextView)itemView.findViewById(R.id.twootUserRetwoot);
            likes = (TextView)itemView.findViewById(R.id.twootUserLike);
            retwootImg = (ImageView)itemView.findViewById(R.id.retwootImg);
            likeImg = (ImageView)itemView.findViewById(R.id.retwootImg);
        }
    }
}



/*
username = (TextView)itemView.findViewById(R.id.twootUserName);
usermail = (TextView)itemView.findViewById(R.id.twootUserMail);
twoottext = (TextView)itemView.findViewById(R.id.twootUserTwoot);
retwoots = (TextView)itemView.findViewById(R.id.twootUserRetwoot);
likes = (TextView)itemView.findViewById(R.id.twootUserLike);

public void setTwoots(Twoots twoots) {
            username.setText(twoots.getUsername());
            usermail.setText(twoots.getUsermail());
            twoottext.setText(twoots.getTwoottext());
            retwoots.setText(twoots.getRetwoots());
            likes.setText(twoots.getLikes());
        }
*/