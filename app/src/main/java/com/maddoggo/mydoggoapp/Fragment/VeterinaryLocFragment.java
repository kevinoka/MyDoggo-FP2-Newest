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
import com.maddoggo.mydoggoapp.Interface.VeterinaryLocClickListener;
import com.maddoggo.mydoggoapp.Model.VetLocation;
import com.maddoggo.mydoggoapp.R;
import com.maddoggo.mydoggoapp.ViewHolder.VeterinaryLocViewHolder;

public class VeterinaryLocFragment extends Fragment implements View.OnClickListener {

    View view;
    private RecyclerView recyclerVeterinaryLoc;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseRecyclerOptions<VetLocation> options;
    private FirebaseRecyclerAdapter adapter;

    private FirebaseDatabase Db;
    private DatabaseReference vetLocation;

    public VeterinaryLocFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.veterinary_location_fragment, container, false);
        recyclerVeterinaryLoc = view.findViewById(R.id.recVeterinaryLocationFragment);

        Db = FirebaseDatabase.getInstance();
        vetLocation = Db.getReference("VetLocation");

        loadVetLocation();
        return view;
    }

    private void loadVetLocation() {

        options = new FirebaseRecyclerOptions.Builder<VetLocation>()
                .setQuery(vetLocation,VetLocation.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<VetLocation, VeterinaryLocViewHolder>(options) {

            @NonNull
            @Override
            public VeterinaryLocViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.veterinary_location_item,viewGroup,false);

                return new VeterinaryLocViewHolder(view);

            }

            @Override
            protected void onBindViewHolder(@NonNull VeterinaryLocViewHolder holder, int position, @NonNull final VetLocation model) {
                holder.mVeterinaryNameLocation.setText(model.getVetName());
                holder.mVeterinaryLocLocation.setText(model.getVetLocation());

                holder.mVetLocMapButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri gmmIntentUri = Uri.parse("geo:0,0?q= " + model.getVetMapLocation());
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);
                    }
                });

                final VetLocation clickItem = model;
                holder.setVeterinaryLocClickListener(new VeterinaryLocClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                    }
                });

            }

        };
        recyclerVeterinaryLoc.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerVeterinaryLoc.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View v) {

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
