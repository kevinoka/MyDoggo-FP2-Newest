package com.maddoggo.mydoggoapp.ViewHolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maddoggo.mydoggoapp.Interface.BuySellClickListener;
import com.maddoggo.mydoggoapp.R;

public class BuySellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView BSDogName, BSDogPrice, BSDogPlace;
    public ImageView BSDogImage;
    public CardView BSCard;

    private BuySellClickListener buySellClickListener;

    public BuySellViewHolder(View itemView){
        super(itemView);

        BSDogName = itemView.findViewById(R.id.BSDogName);
        BSDogPrice = itemView.findViewById(R.id.BSDogPrice);
        BSDogPlace = itemView.findViewById(R.id.BSDogPlace);
        BSDogImage = itemView.findViewById(R.id.BSDogImage);
        BSCard = itemView.findViewById(R.id.BSCard);

        itemView.setOnClickListener(this);
    }

    public void setBuySellClickListener(BuySellClickListener buySellClickListener){
        this.buySellClickListener = buySellClickListener;
    }

    @Override
    public void onClick(View v) {
        buySellClickListener.onClick(v,getAdapterPosition(),false);
    }
}
