package com.maddoggo.mydoggoapp.ViewHolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.maddoggo.mydoggoapp.Interface.VeterinaryLocClickListener;
import com.maddoggo.mydoggoapp.R;

public class VeterinaryLocViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public CardView mVetLocCard;
    public TextView mVeterinaryNameLocation, mVeterinaryLocLocation;
    public ImageButton mVetLocMapButton;

    private VeterinaryLocClickListener veterinaryLocClickListener;

    public VeterinaryLocViewHolder(View itemView) {
        super(itemView);

        mVetLocCard = itemView.findViewById(R.id.VetLocCard);
        mVeterinaryNameLocation = itemView.findViewById(R.id.VeterinaryNameLocation);
        mVeterinaryLocLocation = itemView.findViewById(R.id.VeterinaryLocLocation);
        mVetLocMapButton = itemView.findViewById(R.id.VetLocMapButton);

        itemView.setOnClickListener(this);
    }

    public void setVeterinaryLocClickListener(VeterinaryLocClickListener veterinaryLocClickListener) {
        this.veterinaryLocClickListener = veterinaryLocClickListener;
    }

    @Override
    public void onClick(View v) {
        veterinaryLocClickListener.onClick(v,getAdapterPosition(), false);
    }
}
