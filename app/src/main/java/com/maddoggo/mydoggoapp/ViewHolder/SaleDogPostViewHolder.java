package com.maddoggo.mydoggoapp.ViewHolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.maddoggo.mydoggoapp.Interface.SaleDogPostClickListener;
import com.maddoggo.mydoggoapp.R;

public class SaleDogPostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView BSDogName, BSDogPrice, BSDogPlace;
    public ImageView BSDogImage;
    public CardView BSCard;

    public ImageButton SaleDogPostEditButton, SaleDogPostDeleteButton;

    private SaleDogPostClickListener saleDogPostClickListener;

    public SaleDogPostViewHolder(View itemView){
        super(itemView);

        BSDogName = itemView.findViewById(R.id.BSDogName);
        BSDogPrice = itemView.findViewById(R.id.BSDogPrice);
        BSDogPlace = itemView.findViewById(R.id.BSDogPlace);
        BSDogImage = itemView.findViewById(R.id.BSDogImage);
        BSCard = itemView.findViewById(R.id.BSCard);
        SaleDogPostEditButton = itemView.findViewById(R.id.SaleDogPostEditButton);
        SaleDogPostDeleteButton = itemView.findViewById(R.id.SaleDogPostDeleteButton);

        itemView.setOnClickListener(this);
    }

    public void setSaleDogPostClickListener(SaleDogPostClickListener saleDogPostClickListener){
        this.saleDogPostClickListener = saleDogPostClickListener;
    }

    @Override
    public void onClick(View v) {
        saleDogPostClickListener.onClick(v,getAdapterPosition(),false);
    }
}
