package com.example.twittor.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twittor.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TwootAdapter extends RecyclerView.Adapter<TwootAdapter.ViewHolder> {
    private List<TwootElement> mData;
    private LayoutInflater mInflater;
    private Context context;

    public TwootAdapter(List<TwootElement> twootList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = twootList;
    }

    @Override
    public int getItemCount(){return mData.size();}

    @Override
    public TwootAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.twoot_element, null);
        return new TwootAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TwootAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setTwoots(List<TwootElement> twoots){ mData=twoots; }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView twootUserImg;
        TextView twootUserName, twootUserTwoot, twootUserRetwoot, twootUserLike;
        ViewHolder(View twootView){
            super(twootView);
            twootUserImg = (CircleImageView) twootView.findViewById(R.id.twootUserImg);
            twootUserName = (TextView) twootView.findViewById(R.id.twootUserName);
            twootUserTwoot = (TextView) twootView.findViewById(R.id.twootUserTwoot);
            twootUserRetwoot = (TextView) twootView.findViewById(R.id.twootUserRetwoot);
            twootUserLike = (TextView) twootView.findViewById(R.id.twootUserLike);
        }

        void bindData(final TwootElement twoot){
            twootUserName.setText(twoot.getTwootUserName());
            twootUserTwoot.setText(twoot.getTwootUserTwoot());
            twootUserRetwoot.setText(String.valueOf(twoot.getTwootRetwoot()));
            twootUserLike.setText(String.valueOf(twoot.getTwootLike()));
        }
    }
}
