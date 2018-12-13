package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BreedingResult extends AppCompatActivity {

    private FirebaseDatabase Db;
    private DatabaseReference dogBreedings;
    private Integer text,text2,text3;
    private TextView mBreedDogType, mBreedDogAge, mBreedDogMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeding_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Db = FirebaseDatabase.getInstance();
        dogBreedings = Db.getReference("DogBreeding");

        mBreedDogType = findViewById(R.id.BreedDogType);
        mBreedDogAge = findViewById(R.id.BreedDogAge);
        mBreedDogMatch = findViewById(R.id.BreedDogMatch);

        Intent i = getIntent();

        text = i.getIntExtra("DogBreed",0);
        text2 = i.getIntExtra("Old",0);
        text3 = i.getIntExtra("PrefDog",0);


        mBreedDogType.setText(text.toString());
        mBreedDogAge.setText(text2.toString());

        checkBreeding(text, text2, text3);
    }

    private void checkBreeding(final Integer text, Integer text2, final Integer text3) {
        dogBreedings.child(String.valueOf((text+text3))).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    Toast.makeText(getBaseContext(), dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();

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
