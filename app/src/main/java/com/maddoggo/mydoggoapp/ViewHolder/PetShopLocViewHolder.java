package com.maddoggo.mydoggoapp.ViewHolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.maddoggo.mydoggoapp.Interface.PetShopLocClickListener;
import com.maddoggo.mydoggoapp.R;

public class PetShopLocViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public CardView mPetShopLocCard;
    public TextView mPetShopNameLocation, mPetShopLocLocation;
    public ImageButton mPetShopLocMapButton;

    private PetShopLocClickListener petShopLocClickListener;

    public PetShopLocViewHolder(View itemView) {
        super(itemView);

        mPetShopLocCard = itemView.findViewById(R.id.PetShopLocCard);
        mPetShopNameLocation = itemView.findViewById(R.id.PetShopNameLocation);
        mPetShopLocLocation = itemView.findViewById(R.id.PetShopLocLocation);
        mPetShopLocMapButton = itemView.findViewById(R.id.PetShopLocMapButton);

        itemView.setOnClickListener(this);
    }

    public void setPetShopLocClickListener(PetShopLocClickListener petShopLocClickListener) {
        this.petShopLocClickListener = petShopLocClickListener;
    }

    @Override
    public void onClick(View v) {
        petShopLocClickListener.onClick(v,getAdapterPosition(), false);
    }
}
