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

import java.util.Objects;

public class ConsultationResult extends AppCompatActivity {

    private FirebaseDatabase Db;
    private DatabaseReference dogSymptoms;
    private String text,text2,text3;
    private TextView mConsulDogBreed, mConsulDogAge, mConsulDiagResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation_result);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Db = FirebaseDatabase.getInstance();
        dogSymptoms = Db.getReference("DogSymptoms");

        mConsulDogBreed = findViewById(R.id.ConsulDogBreed);
        mConsulDogAge = findViewById(R.id.ConsulDogAge);
        mConsulDiagResult = findViewById(R.id.ConsulDiagResult);

        Intent i = getIntent();

        text =  i.getStringExtra("DogType");
        text2 =  i.getStringExtra("Old");
        text3 =  i.getStringExtra("Symptoms");

        mConsulDogBreed.setText(text);
        mConsulDogAge.setText(text2);

        checkSymptoms(text,text2,text3);

    }

    private void checkSymptoms(final String text, String text2, final String text3) {
        dogSymptoms.child("ListBigDog").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(text).exists()) {
                    //Toast.makeText(getBaseContext(), "Big", Toast.LENGTH_SHORT).show();
                    dogSymptoms.child("BigDog").child(text3).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            //Toast.makeText(getBaseContext(), dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
                            mConsulDiagResult.setText(Objects.requireNonNull(dataSnapshot.getValue()).toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
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
