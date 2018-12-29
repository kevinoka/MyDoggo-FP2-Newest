package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.maddoggo.mydoggoapp.Interface.FavoriteAdoptionClickListener;
import com.maddoggo.mydoggoapp.Model.Adoption;
import com.maddoggo.mydoggoapp.ViewHolder.FavoriteAdoptionViewHolder;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class FavoriteAdoption extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerFavoriteAdoption;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseRecyclerOptions<Adoption> options;
    private FirebaseRecyclerAdapter adapter;

    private FirebaseAuth mAuth;
    private FirebaseDatabase Db;
    private DatabaseReference adoption;
    private DatabaseReference favAdoptionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_adoption);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        Db = FirebaseDatabase.getInstance();
        adoption = Db.getReference("Adoption");
        favAdoptionList = Db.getReference("FavAdoptionUserList");

        recyclerFavoriteAdoption = findViewById(R.id.recFavoriteAdoptionList);

        loadFavAdopt();

    }

    private void loadFavAdopt() {

        Query query = favAdoptionList.child(Objects.requireNonNull(mAuth.getCurrentUser()).getUid().toString());

        options = new FirebaseRecyclerOptions.Builder<Adoption>()
                .setIndexedQuery(query,adoption,Adoption.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Adoption, FavoriteAdoptionViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull FavoriteAdoptionViewHolder holder, int position, @NonNull Adoption model) {
                holder.AdoptDogName.setText(model.getDogName());
                holder.AdoptDogType.setText(model.getDogType());
                holder.AdoptDogAge.setText(model.getDogAge());
                holder.AdoptDogDesc.setText(model.getDogDesc());

                Picasso.with(getBaseContext())
                        .load(model.getDogPict())
                        .into(holder.AdoptDogPict);

                final Adoption clickItem = model;

                holder.FavAdoptionCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                holder.adoptNowButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(view.getContext(), AdoptNow.class);
                        i.putExtra("AdoptionClass", clickItem);
                        startActivity(i);
                    }
                });

                holder.setFavoriteAdoptionClickListener(new FavoriteAdoptionClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                    }
                });
            }

            @NonNull
            @Override
            public FavoriteAdoptionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.favorite_adoption_list,viewGroup,false);

                return new FavoriteAdoptionViewHolder(view);

            }

        };
        recyclerFavoriteAdoption.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerFavoriteAdoption.setLayoutManager(layoutManager);


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
