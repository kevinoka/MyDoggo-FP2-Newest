package com.maddoggo.mydoggoapp.ViewHolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maddoggo.mydoggoapp.Interface.DoggopediaClickListener;
import com.maddoggo.mydoggoapp.R;

public class DoggopediaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView DPType, DPDesc;
    public ImageView DPImage;
    public CardView DPCard;

    private DoggopediaClickListener doggopediaClickListener;

    public DoggopediaViewHolder(View itemView){
        super(itemView);

        DPType = itemView.findViewById(R.id.DPType);
        DPDesc = itemView.findViewById(R.id.DPDesc);
        DPImage = itemView.findViewById(R.id.DPImage);
        DPCard = itemView.findViewById(R.id.DPCard);

        itemView.setOnClickListener(this);
    }

    public void setDoggopediaClickListener(DoggopediaClickListener doggopediaClickListener) {
        this.doggopediaClickListener = doggopediaClickListener;
    }

    @Override
    public void onClick(View v){
        doggopediaClickListener.onClick(v,getAdapterPosition(),false);
    }
}
