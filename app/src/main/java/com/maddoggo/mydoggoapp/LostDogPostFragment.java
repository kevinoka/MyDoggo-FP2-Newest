package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.maddoggo.mydoggoapp.Interface.LostDogPostClickListener;
import com.maddoggo.mydoggoapp.Interface.LostFoundClickListener;
import com.maddoggo.mydoggoapp.Model.LostFoundDog;
import com.maddoggo.mydoggoapp.Model.SaleDog;
import com.maddoggo.mydoggoapp.ViewHolder.LostDogPostViewHolder;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class LostDogPostFragment extends Fragment implements View.OnClickListener {

    View view;
    private CardView LFCard;
    private RecyclerView recyclerLostDogPost;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseRecyclerOptions<LostFoundDog> options;
    private FirebaseRecyclerAdapter adapter;

    private FirebaseAuth mAuth;
    private FirebaseDatabase Db;
    private DatabaseReference lostFoundDog;
    private DatabaseReference lostFoundDogByUser;

    public LostDogPostFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lost_dog_fragment, container,false);
        recyclerLostDogPost = view.findViewById(R.id.recLostDogFragment);

        mAuth = FirebaseAuth.getInstance();
        Db = FirebaseDatabase.getInstance();
        lostFoundDog = Db.getReference("LostFoundList");
        lostFoundDogByUser = Db.getReference("LostFoundUserList");

        loadLDPostPerUser();
        return view;
    }

    private void loadLDPostPerUser() {

        //saleDogUserList --> nama database refrence kyk saleDog tapi arahnya ke table saleDogUserList di database
        Query query = lostFoundDogByUser.child(Objects.requireNonNull(mAuth.getCurrentUser()).getUid().toString());

        //ininya juga beda, cuma berubah jd setIndexedQuery
        options = new FirebaseRecyclerOptions.Builder<LostFoundDog>()
                .setIndexedQuery(query,lostFoundDog,LostFoundDog.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<LostFoundDog, LostDogPostViewHolder>(options) {


            @NonNull
            @Override
            public LostDogPostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.lost_dog_item,viewGroup,false);

                return new LostDogPostViewHolder(view);

            }

            @Override
            protected void onBindViewHolder(@NonNull LostDogPostViewHolder holder, int position, @NonNull LostFoundDog model) {
                holder.LFDogName.setText(model.getDogName());
                holder.LFDogType.setText(model.getDogType());
                holder.LFDogLast.setText(model.getDogLastSeen());
                holder.LFDogChara.setText(model.getDogChara());

                Picasso.with(getActivity())
                        .load(model.getDogPict())
                        .into(holder.LFDogPict);

                final LostFoundDog clickItem = model;

                holder.LFCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(view.getContext(), LostPage.class);
                        i.putExtra("LostFoundDogClass", clickItem);
                        startActivity(i);
                        //Toast.makeText(BuyOrSellMenu.this, "Niceeee", Toast.LENGTH_SHORT).show();
                    }
                });

                holder.setLostDogPostClickListener(new LostDogPostClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                    }
                });

            }

        };
        recyclerLostDogPost.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerLostDogPost.setLayoutManager(layoutManager);
    }

    public void onClick(View v) {
        Intent i = new Intent(getContext(), LostPage.class);
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