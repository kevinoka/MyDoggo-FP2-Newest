package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.maddoggo.mydoggoapp.Interface.BuySellClickListener;
import com.maddoggo.mydoggoapp.Model.SaleDog;
import com.maddoggo.mydoggoapp.Model.User;
import com.maddoggo.mydoggoapp.ViewHolder.BuySellViewHolder;
import com.squareup.picasso.Picasso;

public class BuyOrSellMenu extends AppCompatActivity implements View.OnClickListener {

    private CardView buySellCard;
    private RecyclerView recyclerBuySellMenu;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseRecyclerOptions<SaleDog> options;
    private FirebaseRecyclerAdapter<SaleDog,BuySellViewHolder> adapter;

    private FirebaseDatabase Db;
    private DatabaseReference saleDog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_or_sell_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SellPosting.class);
                startActivity(i);
            }
        });

        Db = FirebaseDatabase.getInstance();
        saleDog = Db.getReference("SaleDogList");

        recyclerBuySellMenu = findViewById(R.id.recBuySellMenuList);


        loadBSM();

        //buySellCard = findViewById(R.id.buy_sell_card1);

        //buySellCard.setOnClickListener(this);

    }

    private void loadBSM() {


            Query query = saleDog;

            options = new FirebaseRecyclerOptions.Builder<SaleDog>()
                    .setQuery(query,SaleDog.class)
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

                final SaleDog clickItem = model;
                holder.setBuySellClickListener(new BuySellClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                    }
                });

            }

            /*@Override
            protected void populateViewHolder(final BuySellViewHolder viewHolder,
                                              SaleDog model, final int position) {*/
               /* if(model.getAvatarUrl()!=null && !TextUtils.isEmpty(model.getAvatarUrl())){
                    Picasso.with(InviteActivity.this)
                            .load(model.getAvatarUrl())
                            .into(viewHolder.JSPhoto);
                }*/

               /* viewHolder.JSCheckBoxBtn.setOnCheckedChangeListener
                        (new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked){
                                    ListUsers.put(adapter.getRef(position).getKey().toString(),"invite");
                                }
                                else{
                                    ListUsers.remove(adapter.getRef(position).getKey().toString());
                                }
                            }
                        });

                //fill rating star
                users.child(adapter.getRef(position).getKey().toString()).child("rating").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            String rating = dataSnapshot.getValue().toString();
                            int ratings = Integer.parseInt(rating);
                            viewHolder.ratingJS.setRating((float) ratings);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
*/

            //}
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
}
