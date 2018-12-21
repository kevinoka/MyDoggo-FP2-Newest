package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class BreedingResult extends AppCompatActivity {

    private FirebaseDatabase Db;
    private DatabaseReference dogBreedings;
    private Integer text,text2,text3;
    private String type1,type2;
    private TextView mBreedDogType, mBreedDogAge, mBreedDogMatch;
    private ImageView dogBreedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeding_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Db = FirebaseDatabase.getInstance();
        dogBreedings = Db.getReference("DogBreeding");

        mBreedDogType = findViewById(R.id.BreedDogType);
        mBreedDogAge = findViewById(R.id.BreedDogAge);
        mBreedDogMatch = findViewById(R.id.BreedDogMatch);
        dogBreedImage = findViewById(R.id.DogBreedImage);

        Intent i = getIntent();

        text = i.getIntExtra("DogBreed",0);

        type1 = i.getStringExtra("Type1");
        type2 = i.getStringExtra("Type2");

        text2 = i.getIntExtra("Old",0);
        text3 = i.getIntExtra("PrefDog",0);

        mBreedDogType.setText(type1.toString());
        mBreedDogAge.setText(text2.toString());

        checkBreeding(text);
    }

    private void checkBreeding(final Integer text) {
        dogBreedings.child(String.valueOf(text)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    mBreedDogMatch.setText(Objects.requireNonNull(dataSnapshot.child("Name").getValue()).toString());

                    Picasso.with(getBaseContext())
                            .load(Objects.requireNonNull(dataSnapshot.child("DogPic").getValue()).toString())
                            .into(dogBreedImage);

                    //Toast.makeText(getBaseContext(), dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();

                }
                else{
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
