package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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
import com.maddoggo.mydoggoapp.Interface.BuySellClickListener;
import com.maddoggo.mydoggoapp.Model.SaleDog;
import com.maddoggo.mydoggoapp.ViewHolder.BuySellViewHolder;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class UserSellPostList extends AppCompatActivity implements View.OnClickListener {
    private CardView BSCard;
    private RecyclerView recyclerBuySellMenu;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseRecyclerOptions<SaleDog> options;
    private FirebaseRecyclerAdapter adapter;

    private FirebaseAuth mAuth;
    private FirebaseDatabase Db;
    private DatabaseReference saleDog;
    private DatabaseReference saleDogByUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_or_sell_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        Db = FirebaseDatabase.getInstance();
        saleDog = Db.getReference("SaleDogList");
        saleDogByUser = Db.getReference("SaleDogUserList");

        recyclerBuySellMenu = findViewById(R.id.recBuySellMenuList);


        loadBSMPerUser();

    }

    private void loadBSMPerUser() {

        //saleDogUserList --> nama database refrence kyk saleDog tapi arahnya ke table saleDogUserList di database
        Query query = saleDogByUser.child(Objects.requireNonNull(mAuth.getCurrentUser()).getUid().toString());

        //ininya juga beda, cuma berubah jd setIndexedQuery
        options = new FirebaseRecyclerOptions.Builder<SaleDog>()
                .setIndexedQuery(query,saleDog,SaleDog.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<SaleDog, BuySellViewHolder>(options) {



            @NonNull
            @Override
            public BuySellViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.buy_sell_menu_list,viewGroup,false);

                return new BuySellViewHolder(view);

            }

            @Override
            protected void onBindViewHolder(@NonNull BuySellViewHolder holder, int position, @NonNull SaleDog model) {
                holder.BSDogName.setText(model.getDogName());
                holder.BSDogPrice.setText(model.getPrice());
                holder.BSDogPlace.setText(model.getSellerLocation());

                Picasso.with(getBaseContext())
                        .load(model.getSellDogImage())
                        .into(holder.BSDogImage);

                final SaleDog clickItem = model;

                holder.BSCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(view.getContext(), BuyPage.class);
                        i.putExtra("SaleDogClass", clickItem);
                        startActivity(i);
                    }
                });

                holder.setBuySellClickListener(new BuySellClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                    }
                });

            }

        };
        recyclerBuySellMenu.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerBuySellMenu.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(), BuyPage.class);
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

