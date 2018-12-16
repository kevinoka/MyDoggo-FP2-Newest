package com.maddoggo.mydoggoapp.ViewHolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.maddoggo.mydoggoapp.Interface.LostDogPostClickListener;
import com.maddoggo.mydoggoapp.R;

public class LostDogPostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView LFDogType, LFDogChara, LFDogLast, LFDogName;
    public ImageView LFDogPict;
    public CardView LFCard;
    public ImageButton LostDogPostEditButton, LostDogPostDeleteButton;

    private LostDogPostClickListener lostDogPostClickListener;

    public LostDogPostViewHolder(View itemView){
        super(itemView);

        LFDogName = itemView.findViewById(R.id.LFDogName);
        LFDogType = itemView.findViewById(R.id.LFDogType);
        LFDogChara = itemView.findViewById(R.id.LFDogChara);
        LFDogLast = itemView.findViewById(R.id.LFDogLast);
        LFDogPict = itemView.findViewById(R.id.LFDogPict);
        LFCard = itemView.findViewById(R.id.LFCard);
        LostDogPostEditButton = itemView.findViewById(R.id.LostDogPostEditButton);
        LostDogPostDeleteButton = itemView.findViewById(R.id.LostDogPostDeleteButton);

        itemView.setOnClickListener(this);
    }

    public void setLostDogPostClickListener(LostDogPostClickListener lostDogPostClickListener){
        this.lostDogPostClickListener = lostDogPostClickListener;
    }

    @Override
    public void onClick(View v){
        lostDogPostClickListener.onClick(v,getAdapterPosition(), false);
    }
}