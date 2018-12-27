package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class BreedingMenu extends AppCompatActivity {

    private FirebaseDatabase Db;
    private DatabaseReference dogBreedings;

    private Integer text, text2, text3 = 0;
    private String type1, type2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeding_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Db = FirebaseDatabase.getInstance();
        dogBreedings = Db.getReference("DogBreeding");

        Spinner breedingSpinner1 = (Spinner) findViewById(R.id.spinnerBreeding1);

        ArrayAdapter<String> breedingAdapter1 = new ArrayAdapter<>(BreedingMenu.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.consul1));
        breedingAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breedingSpinner1.setAdapter(breedingAdapter1);

        //final String[] text = {breedingSpinner1.getSelectedItem().toString()};


        Spinner breedingSpinner2 = (Spinner) findViewById(R.id.spinnerBreeding2);

        ArrayAdapter<String> breedingAdapter2 = new ArrayAdapter<>(BreedingMenu.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.consul2));
        breedingAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breedingSpinner2.setAdapter(breedingAdapter2);

        //final String[] text2 = {breedingSpinner2.getSelectedItem().toString()};


        Spinner breedingSpinner3 = (Spinner) findViewById(R.id.spinnerBreeding3);

        ArrayAdapter<String> breedingAdapter3 = new ArrayAdapter<>(BreedingMenu.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.breeding3));
        breedingAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breedingSpinner3.setAdapter(breedingAdapter3);

        //final String[] text3 = {breedingSpinner3.getSelectedItem().toString()};

        breedingSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                text = pos;
                type1 = item.toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        breedingSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                text2 = pos;
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        breedingSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                text3 = pos;
                type2 = item.toString();

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button btn = findViewById(R.id.buttonBreeding);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                text = text + text3;
                Intent i = new Intent(getApplicationContext(), BreedingResult.class);
                i.putExtra("DogBreed",text);
                i.putExtra("Type1",type1);
                i.putExtra("Type2",type2);
                //i.putExtra("DogBreed",text);
                i.putExtra("Old",text2);
                i.putExtra("PrefDog",text3);

                startActivity(i);

                text = text2 = text3 = 0;

            }
        });
    }

}
