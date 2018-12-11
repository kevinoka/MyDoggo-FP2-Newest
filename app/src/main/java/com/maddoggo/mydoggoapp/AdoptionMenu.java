package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.maddoggo.mydoggoapp.Model.Adoption;
import com.maddoggo.mydoggoapp.Model.User;

import java.util.ArrayList;
import java.util.List;

public class AdoptionMenu extends AppCompatActivity {
    private Adoption cards_data[];
    private arrayAdapter arrayAdapter;
    private int i;

    ListView listView;
    List<Adoption> rowItems;
    ImageView image_adoption;

    private FirebaseDatabase Db;
    private DatabaseReference adoption;
    private DatabaseReference fav;

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

        Db = FirebaseDatabase.getInstance();
        adoption = Db.getReference("Adoption");
        fav = Db.getReference("FavAdoption");


        //add the view via xml or programmatically
        rowItems = new ArrayList<Adoption>();

        arrayAdapter = new arrayAdapter(this, R.layout.item, rowItems );
        //String url = "https://firebasestorage.googleapis.com/v0/b/mydoggo-project.appspot.com/o/dog1.jpg?alt=media&token=b18c2264-ba92-415e-a80a-c1f7b82c9d1d";
        //image_adoption.setImageResource();
        //Glide.with(getApplicationContext()).load(url).into(image_adoption);

        adoption.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Adoption item  = new Adoption();
                //dataSnapshot.getChildren()
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    item.setDogName(ds.child("dogName").getValue().toString());
                    item.setDogDesc(ds.child("dogDesc").getValue().toString());
                    item.setDogPict(ds.child("dogPict").getValue().toString());
                    item.setDogType(ds.child("dogType").getValue().toString());
                    item.setDogAge(ds.child("dogAge").getValue().toString());

                    rowItems.add(item);
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Adoption item = new Adoption(image_adoption,"Dog 1");
        // Adoption item2 = new Adoption(image_adoption,"Dog 2");
        //Adoption item3 = new Adoption(image_adoption,"Dog 3");
        //Adoption item4 = new Adoption(image_adoption,"Dog 4");
        // Adoption item5 = new Adoption(image_adoption,"Dog 5");
        //rowItems.add(item);
        //rowItems.add(item2);
        //rowItems.add(item3);
        // rowItems.add(item4);
        //rowItems.add(item5);
        //arrayAdapter.notifyDataSetChanged();


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
                Toast.makeText(AdoptionMenu.this, "Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(final Object dataObject) {
                //Toast.makeText(AdoptionMenu.this, "Right", Toast.LENGTH_SHORT).show();
                final Adoption obj = (Adoption) dataObject;


                fav.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        long no = dataSnapshot.getChildrenCount();
                        fav.child("FavDog"+no).setValue(obj);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
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