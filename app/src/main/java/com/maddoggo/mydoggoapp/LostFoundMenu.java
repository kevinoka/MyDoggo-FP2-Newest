package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.maddoggo.mydoggoapp.Interface.LostFoundClickListener;
import com.maddoggo.mydoggoapp.Model.LostFoundDog;
import com.maddoggo.mydoggoapp.ViewHolder.LostFoundViewHolder;
import com.squareup.picasso.Picasso;

public class LostFoundMenu extends AppCompatActivity {

    private CardView LFCard;
    private RecyclerView recyclerLostFoundMenu;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseRecyclerOptions<LostFoundDog> options;
    private FirebaseRecyclerAdapter adapter;

    private FirebaseDatabase Db;
    private DatabaseReference lostFoundDog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_found_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LastFoundPosting.class);
                startActivity(i);
            }
        });

        Db = FirebaseDatabase.getInstance();
        lostFoundDog = Db.getReference("LostFoundList");

        recyclerLostFoundMenu = findViewById(R.id.recLostFoundMenuList);

        loadLFM();



    }

    private void loadLFM() {

        options = new FirebaseRecyclerOptions.Builder<LostFoundDog>()
                .setQuery(lostFoundDog,LostFoundDog.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<LostFoundDog, LostFoundViewHolder>(options) {



            @NonNull
            @Override
            public LostFoundViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.lost_found_menu_list,viewGroup,false);

                return new LostFoundViewHolder(view);

            }

            @Override
            protected void onBindViewHolder(@NonNull LostFoundViewHolder holder, int position, @NonNull LostFoundDog model) {
                holder.LFDogName.setText(model.getDogName());
                holder.LFDogType.setText(model.getDogType());
                holder.LFDogLast.setText(model.getDogLastSeen());
                holder.LFDogChara.setText(model.getDogChara());

                Picasso.with(getBaseContext())
                        .load(model.getDogPict())
                        .into(holder.LFDogPict);

                final LostFoundDog clickItem = model;
                holder.setLostFoundClickListener(new LostFoundClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Intent i = new Intent(getApplicationContext(), BuyPage.class);
                        //startActivity(i);
                        //finish();
                        Toast.makeText(LostFoundMenu.this, "Niceeee", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        };
        recyclerLostFoundMenu.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerLostFoundMenu.setLayoutManager(layoutManager);
    }

    public void onClick(View v) {
        //Intent i = new Intent(getApplicationContext(), )
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
