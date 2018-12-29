package com.maddoggo.mydoggoapp.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.maddoggo.mydoggoapp.BuyPage;
import com.maddoggo.mydoggoapp.Interface.SaleDogPostClickListener;
import com.maddoggo.mydoggoapp.Model.SaleDog;
import com.maddoggo.mydoggoapp.R;
import com.maddoggo.mydoggoapp.SaleDogEdit;
import com.maddoggo.mydoggoapp.ViewHolder.SaleDogPostViewHolder;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class SaleDogPostFragment extends Fragment implements View.OnClickListener {

    View view;
    private CardView BSCard;
    private RecyclerView recyclerSaleDogPost;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseRecyclerOptions<SaleDog> options;
    private FirebaseRecyclerAdapter adapter;

    private FirebaseAuth mAuth;
    private FirebaseDatabase Db;
    private DatabaseReference saleDog;
    private DatabaseReference saleDogByUser;

    public SaleDogPostFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sale_dog_fragment, container,false);
        recyclerSaleDogPost = view.findViewById(R.id.recSaleDogFragment);

        mAuth = FirebaseAuth.getInstance();
        Db = FirebaseDatabase.getInstance();
        saleDog = Db.getReference("SaleDogList");
        saleDogByUser = Db.getReference("SaleDogUserList");

        loadSDPostPerUser();
        return view;
    }

    private void loadSDPostPerUser() {

        Query query = saleDogByUser.child(Objects.requireNonNull(mAuth.getCurrentUser()).getUid().toString());

        options = new FirebaseRecyclerOptions.Builder<SaleDog>()
                .setIndexedQuery(query,saleDog,SaleDog.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<SaleDog, SaleDogPostViewHolder>(options) {

            @NonNull
            @Override
            public SaleDogPostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.sale_dog_item,viewGroup,false);

                return new SaleDogPostViewHolder(view);

            }

            @Override
            protected void onBindViewHolder(@NonNull SaleDogPostViewHolder holder, final int position, @NonNull final SaleDog model) {

                holder.BSDogName.setText(model.getDogName());
                holder.BSDogPrice.setText(model.getPrice());
                holder.BSDogPlace.setText(model.getSellerLocation());

                Picasso.with(getActivity())
                        .load(model.getSellDogImage())
                        .into(holder.BSDogImage);

                holder.BSCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(view.getContext(), BuyPage.class);
                        i.putExtra("SaleDogClass", model);
                        startActivity(i);
                    }
                });

                holder.SaleDogPostEditButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getContext(), SaleDogEdit.class);
                        i.putExtra("SaleDogClass", model);
                        i.putExtra("Key", adapter.getRef(position).getKey());
                        startActivity(i);
                    }
                });

                holder.SaleDogPostDeleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                        builder.setTitle("Delete");
                        builder.setMessage("Are you sure you want to delete it?");

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                saleDog.child(adapter.getRef(position).getKey()).removeValue();
                                builder.setMessage("Post Deleted");
                                dialog.dismiss();
                            }
                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();
                    }

                });

                holder.setSaleDogPostClickListener(new SaleDogPostClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                    }
                });

            }

        };
        recyclerSaleDogPost.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerSaleDogPost.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getContext(), BuyPage.class);
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
