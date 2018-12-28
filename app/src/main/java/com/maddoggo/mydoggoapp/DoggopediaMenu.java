package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.maddoggo.mydoggoapp.Interface.DoggopediaClickListener;
import com.maddoggo.mydoggoapp.Model.Doggopedia;
import com.maddoggo.mydoggoapp.ViewHolder.DoggopediaViewHolder;
import com.squareup.picasso.Picasso;

public class DoggopediaMenu extends AppCompatActivity implements View.OnClickListener {

    private CardView DPCard;
    private RecyclerView recyclerDoggopedia;
    private LinearLayoutManager layoutManager;

    private FirebaseRecyclerOptions<Doggopedia> options;
    private FirebaseRecyclerAdapter adapter;

    private FirebaseAuth mAuth;
    private FirebaseDatabase Db;
    private DatabaseReference doggopedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doggopedia_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddDoggopedia.class);
                startActivity(i);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        Db = FirebaseDatabase.getInstance();
        doggopedia = Db.getReference("Doggopedia");

        fab.hide();

        if(mAuth.getCurrentUser().getUid().equalsIgnoreCase("F5WxV4kxG2ahkE6Fbw9919dEdF82")){

            fab.show();
        }

        recyclerDoggopedia = findViewById(R.id.recDoggopediaMenuList);

        layoutManager = new LinearLayoutManager(getBaseContext());

        recyclerDoggopedia.setLayoutManager(layoutManager);


        loadDP();
    }


    private void loadDP() {


        options = new FirebaseRecyclerOptions.Builder<Doggopedia>()
                .setQuery(doggopedia,Doggopedia.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Doggopedia, DoggopediaViewHolder>(options) {



            @NonNull
            @Override
            public DoggopediaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.doggopedia_menu_list,viewGroup,false);

                return new DoggopediaViewHolder(view);

            }

            @Override
            protected void onBindViewHolder(@NonNull DoggopediaViewHolder holder, int position, @NonNull Doggopedia model) {
                holder.DPType.setText(model.getDogType());
                holder.DPDesc.setText(model.getDogDesc());

                Picasso.with(getBaseContext())
                        .load(model.getDogPict())
                        .into(holder.DPImage);

                final Doggopedia clickItem = model;
                holder.setDoggopediaClickListener(new DoggopediaClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                    }
                });

            }

        };

        recyclerDoggopedia.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        /*Intent i = new Intent(getApplicationContext(), Home.class);
        startActivity(i);*/
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
