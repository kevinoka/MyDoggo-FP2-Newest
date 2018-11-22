package com.maddoggo.mydoggoapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.maddoggo.mydoggoapp.Model.Adoption;
import com.maddoggo.mydoggoapp.Model.User;
import com.squareup.picasso.Picasso;

public class FavoriteAdoption extends AppCompatActivity {

    private ImageView mDogPict;
    private TextView mDogName, mDogType, mDogDesc, mDogAge;

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

        mDogName = findViewById(R.id.AdoptDogName);
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
        });


    }

}
