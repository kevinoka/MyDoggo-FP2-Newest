package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

public class AdoptionMenu extends AppCompatActivity {
    private AdoptionCards cards_data[];
    private arrayAdapter arrayAdapter;
    private int i;

    ListView listView;
    List<AdoptionCards> rowItems;
    ImageView image_adoption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FavoriteAdoption.class);
                startActivity(i);
            }
        });



        //add the view via xml or programmatically
        rowItems = new ArrayList<AdoptionCards>();

        arrayAdapter = new arrayAdapter(this, R.layout.item, rowItems );
        String url = "https://firebasestorage.googleapis.com/v0/b/mydoggo-project.appspot.com/o/dog1.jpg?alt=media&token=b18c2264-ba92-415e-a80a-c1f7b82c9d1d";
        //image_adoption.setImageResource();
        //Glide.with(getApplicationContext()).load(url).into(image_adoption);

        AdoptionCards item = new AdoptionCards(image_adoption,"nnnaaaa");
        AdoptionCards item2 = new AdoptionCards(image_adoption,"jgcjkg");
        rowItems.add(item);
        rowItems.add(item2);
        arrayAdapter.notifyDataSetChanged();


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
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(AdoptionMenu.this, "Right", Toast.LENGTH_SHORT).show();
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