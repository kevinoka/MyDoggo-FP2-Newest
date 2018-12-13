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

public class ConsultationMenu extends AppCompatActivity {

    private FirebaseDatabase Db;
    private DatabaseReference dogSymptoms;

    private String text,text2,text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Db = FirebaseDatabase.getInstance();
        dogSymptoms = Db.getReference("DogSymptoms");

        Spinner consulSpinner = (Spinner) findViewById(R.id.spinnerConsul1);

        ArrayAdapter<String> consulAdapter = new ArrayAdapter<>(ConsultationMenu.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.consul1));
        consulAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        consulSpinner.setAdapter(consulAdapter);

        //final String[] text = {consulSpinner.getSelectedItem().toString()};


        Spinner consulSpinner2 = (Spinner) findViewById(R.id.spinnerConsul2);

        ArrayAdapter<String> consulAdapter2 = new ArrayAdapter<>(ConsultationMenu.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.consul2));
        consulAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        consulSpinner2.setAdapter(consulAdapter2);

        //final String[] text2 = {consulSpinner.getSelectedItem().toString()};


        Spinner consulSpinner3 = (Spinner) findViewById(R.id.spinnerConsul3);

        ArrayAdapter<String> consulAdapter3 = new ArrayAdapter<>(ConsultationMenu.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.consul3));
        consulAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        consulSpinner3.setAdapter(consulAdapter3);

        //final String[] text3 = {consulSpinner.getSelectedItem().toString()};

        consulSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                text = item.toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        consulSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                text2 = String.valueOf(pos);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        consulSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                text3 = item.toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button btn = (Button)findViewById(R.id.buttonConsul);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ConsultationResult.class);
                i.putExtra("DogType",text);
                i.putExtra("Old",text2);
                i.putExtra("Symptoms",text3);

                startActivity(i);
            }
        });



    }

}
