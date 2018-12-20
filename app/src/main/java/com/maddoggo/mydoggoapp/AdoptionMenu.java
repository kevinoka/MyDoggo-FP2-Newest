package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.maddoggo.mydoggoapp.Model.Adoption;

import java.util.ArrayList;
import java.util.List;

public class AdoptionMenu extends AppCompatActivity {
    private Adoption cards_data[];
    private arrayAdapter arrayAdapter;
    private int i;

    ListView listView;
    List<Adoption> rowItems;
    ImageView image_adoption;

    private FirebaseAuth mAuth;
    private FirebaseDatabase Db;
    private DatabaseReference adoption;
    private DatabaseReference fav;
    private DatabaseReference favAdoptionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FavoriteAdoption.class);
                startActivity(i);
            }
        });


        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddAdoption.class);
                startActivity(i);
            }
        });
        mAuth = FirebaseAuth.getInstance();
        fab2.hide();

        if(mAuth.getCurrentUser().getUid().equalsIgnoreCase("tk3uoqdHIwgPQruwgqYyWvx0pZj2")){

            fab2.show();
        }


        Db = FirebaseDatabase.getInstance();
        adoption = Db.getReference("Adoption");
        //fav = Db.getReference("FavAdoption");
        favAdoptionList = Db.getReference("FavAdoptionUserList");


        //add the view via xml or programmatically
        rowItems = new ArrayList<Adoption>();

        arrayAdapter = new arrayAdapter(this, R.layout.item, rowItems );
        //String url = "https://firebasestorage.googleapis.com/v0/b/mydoggo-project.appspot.com/o/dog1.jpg?alt=media&token=b18c2264-ba92-415e-a80a-c1f7b82c9d1d";
        //image_adoption.setImageResource();
        //Glide.with(getApplicationContext()).load(url).into(image_adoption);

        adoption.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Adoption item  = new Adoption();
                DataSnapshot ds = dataSnapshot;
                //dataSnapshot.getChildren()
                //for(DataSnapshot ds : dataSnapshot.getChildren()) {
                item.setDogName(ds.child("dogName").getValue().toString());
                item.setDogDesc(ds.child("dogDesc").getValue().toString());
                item.setDogPict(ds.child("dogPict").getValue().toString());
                item.setDogType(ds.child("dogType").getValue().toString());
                item.setDogAge(ds.child("dogAge").getValue().toString());
                item.setDogId(ds.child("dogId").getValue().toString());

                rowItems.add(item);
                arrayAdapter.notifyDataSetChanged();
                //}
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        SwipeFlingAdapterView flingContainer = findViewById(R.id.frame);

        flingContainer.setAdapter(arrayAdapter);

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                rowItems.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(AdoptionMenu.this, "Nope", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(final Object dataObject) {
                //Toast.makeText(AdoptionMenu.this, "Right", Toast.LENGTH_SHORT).show();
                final Adoption obj = (Adoption) dataObject;

                favAdoptionList.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        favAdoptionList.child(mAuth.getCurrentUser().getUid().toString()).child(obj.getDogId()).setValue(true);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
/*
                fav.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        long no = dataSnapshot.getChildrenCount();
                        //fav.child("FavDog"+no).setValue(obj);
                        favAdoptionList.child("FavDog"+no).setValue(true);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(AdoptionMenu.this, "Item Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }
}