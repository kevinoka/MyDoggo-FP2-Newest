package com.maddoggo.mydoggoapp.ViewHolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.maddoggo.mydoggoapp.Interface.FavoriteAdoptionClickListener;
import com.maddoggo.mydoggoapp.R;

public class FavoriteAdoptionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public CardView FavAdoptionCard;
    public TextView AdoptDogName, AdoptDogType, AdoptDogAge, AdoptDogDesc;
    public ImageView AdoptDogPict;

    public Button adoptNowButton;

    private FavoriteAdoptionClickListener favoriteAdoptionClickListener;

    public FavoriteAdoptionViewHolder(View itemView) {
        super(itemView);

        FavAdoptionCard = itemView.findViewById(R.id.FavAdoptionCard);
        AdoptDogName = itemView.findViewById(R.id.AdoptDogName);
        AdoptDogType = itemView.findViewById(R.id.AdoptDogType);
        AdoptDogAge = itemView.findViewById(R.id.AdoptDogAge);
        AdoptDogDesc = itemView.findViewById(R.id.AdoptDogDesc);
        AdoptDogPict = itemView.findViewById(R.id.AdoptDogPict);

        adoptNowButton = itemView.findViewById(R.id.AdoptNowButton);

        itemView.setOnClickListener(this);

    }

    public void setFavoriteAdoptionClickListener(FavoriteAdoptionClickListener favoriteAdoptionClickListener){
        this.favoriteAdoptionClickListener = favoriteAdoptionClickListener;
    }

    @Override
    public void onClick(View v) {
        favoriteAdoptionClickListener.onClick(v,getAdapterPosition(),false);
    }
}
