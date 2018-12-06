package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.maddoggo.mydoggoapp.Interface.FavoriteAdoptionClickListener;
import com.maddoggo.mydoggoapp.Model.Adoption;
import com.maddoggo.mydoggoapp.Model.AdoptionFav;
import com.maddoggo.mydoggoapp.ViewHolder.FavoriteAdoptionViewHolder;
import com.squareup.picasso.Picasso;

public class FavoriteAdoption extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerFavoriteAdoption;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseRecyclerOptions<AdoptionFav> options;
    private FirebaseRecyclerAdapter adapter;

    private FirebaseDatabase Db;
    private DatabaseReference adoption;
    private DatabaseReference fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_adoption);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Db = FirebaseDatabase.getInstance();
        adoption = Db.getReference("Adoption");
        fav = Db.getReference("FavAdoption");

        recyclerFavoriteAdoption = findViewById(R.id.recFavoriteAdoptionList);

        loadFavAdopt();

    }

    private void loadFavAdopt() {

        options = new FirebaseRecyclerOptions.Builder<AdoptionFav>()
                .setQuery(fav,AdoptionFav.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<AdoptionFav, FavoriteAdoptionViewHolder>(options) {



            @NonNull
            @Override
            public FavoriteAdoptionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.favorite_adoption_list,viewGroup,false);

                return new FavoriteAdoptionViewHolder(view);

            }

            @Override
            protected void onBindViewHolder(@NonNull FavoriteAdoptionViewHolder holder, int position, @NonNull AdoptionFav model) {
                holder.AdoptDogName.setText(model.getDogName());
                holder.AdoptDogType.setText(model.getDogType());
                holder.AdoptDogAge.setText(model.getDogAge());
                holder.AdoptDogDesc.setText(model.getDogDesc());

                Picasso.with(getBaseContext())
                        .load(model.getDogPict())
                        .into(holder.AdoptDogPict);

                final AdoptionFav clickItem = model;

                holder.FavAdoptionCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(view.getContext(), FavoriteAdoption.class);
                        i.putExtra("AdoptionFavClass", clickItem);
                        startActivity(i);
                    }
                });

                holder.setFavoriteAdoptionClickListener(new FavoriteAdoptionClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                    }
                });

            }

        };
        recyclerFavoriteAdoption.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerFavoriteAdoption.setLayoutManager(layoutManager);


        /*mDogName = findViewById(R.id.AdoptDogName);
        mDogType = findViewById(R.id.AdoptDogType);
        mDogDesc = findViewById(R.id.AdoptDogDesc);
        mDogAge = findViewById(R.id.AdoptDogAge);

        mDogPict = findViewById(R.id.AdoptDogPict);

        fav.child("FavDog1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Adoption item = dataSnapshot.getValue(Adoption.class);

                mDogName.setText(item.getDogName());
                mDogType.setText(item.getDogType());
                mDogDesc.setText(item.getDogDesc());
                mDogAge.setText(item.getDogAge());

                Picasso.with(getBaseContext())
                        .load(item.getDogPict())
                        .into(mDogPict);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(), FavoriteAdoption.class);
        startActivity(i);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
