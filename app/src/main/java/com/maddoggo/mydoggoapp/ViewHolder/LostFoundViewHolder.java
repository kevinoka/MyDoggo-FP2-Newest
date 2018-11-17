package com.maddoggo.mydoggoapp.ViewHolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maddoggo.mydoggoapp.Interface.LostFoundClickListener;
import com.maddoggo.mydoggoapp.R;

public class LostFoundViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView LFDogType, LFDogChara, LFDogLast, LFDogName;
    public ImageView LFDogPict;
    public CardView LFCard;

    private LostFoundClickListener lostFoundClickListener;

    public LostFoundViewHolder(View itemView){
        super(itemView);

        LFDogName = itemView.findViewById(R.id.LFDogName);
        LFDogType = itemView.findViewById(R.id.LFDogType);
        LFDogChara = itemView.findViewById(R.id.LFDogChara);
        LFDogLast = itemView.findViewById(R.id.LFDogLast);
        LFDogPict = itemView.findViewById(R.id.LFDogPict);
        LFCard = itemView.findViewById(R.id.LFCard);

        itemView.setOnClickListener(this);
    }

    public void setLostFoundClickListener(LostFoundClickListener lostFoundClickListener){
        this.lostFoundClickListener = lostFoundClickListener;
    }

    @Override
    public void onClick(View v){
        lostFoundClickListener.onClick(v,getAdapterPosition(), false);
    }
}
