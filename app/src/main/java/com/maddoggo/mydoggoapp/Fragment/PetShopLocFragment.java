package com.maddoggo.mydoggoapp.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.maddoggo.mydoggoapp.Interface.PetShopLocClickListener;
import com.maddoggo.mydoggoapp.Model.PetShopLocation;
import com.maddoggo.mydoggoapp.R;
import com.maddoggo.mydoggoapp.ViewHolder.PetShopLocViewHolder;

public class PetShopLocFragment extends Fragment implements View.OnClickListener {

    View view;
    private RecyclerView recyclerPetShopLoc;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseRecyclerOptions<PetShopLocation> options;
    private FirebaseRecyclerAdapter adapter;

    private FirebaseDatabase Db;
    private DatabaseReference petShopLocation;


    public PetShopLocFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pet_shop_location_fragment, container, false);
        recyclerPetShopLoc = view.findViewById(R.id.recPetShopLocationFragment);

        Db = FirebaseDatabase.getInstance();
        petShopLocation = Db.getReference("PetShopLocation");

        loadPSLocation();
        return view;
    }

    private void loadPSLocation() {

        options = new FirebaseRecyclerOptions.Builder<PetShopLocation>()
                .setQuery(petShopLocation,PetShopLocation.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<PetShopLocation, PetShopLocViewHolder>(options) {



            @NonNull
            @Override
            public PetShopLocViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.pet_shop_location_item,viewGroup,false);

                return new PetShopLocViewHolder(view);

            }

            @Override
            protected void onBindViewHolder(@NonNull PetShopLocViewHolder holder, int position, @NonNull final PetShopLocation model) {
                holder.mPetShopNameLocation.setText(model.getPetShopName());
                holder.mPetShopLocLocation.setText(model.getPetShopLoc());

                holder.mPetShopLocMapButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri gmmIntentUri = Uri.parse("geo:0,0?q= " + model.getPetShopMapLoc());
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);
                    }
                });


                final PetShopLocation clickItem = model;
                holder.setPetShopLocClickListener(new PetShopLocClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                    }
                });

            }

        };
        recyclerPetShopLoc.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerPetShopLoc.setLayoutManager(layoutManager);
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
