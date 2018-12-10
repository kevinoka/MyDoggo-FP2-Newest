package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConsultationMenu extends AppCompatActivity {

    private FirebaseDatabase Db;
    private DatabaseReference dogsymptoms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Db = FirebaseDatabase.getInstance();
        dogsymptoms = Db.getReference("DogSymptoms");

        Spinner consulSpinner = (Spinner) findViewById(R.id.spinnerConsul1);

        ArrayAdapter<String> consulAdapter = new ArrayAdapter<>(ConsultationMenu.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.consul1));
        consulAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        consulSpinner.setAdapter(consulAdapter);

        final String[] text = {consulSpinner.getSelectedItem().toString()};


        Spinner consulSpinner2 = (Spinner) findViewById(R.id.spinnerConsul2);

        ArrayAdapter<String> consulAdapter2 = new ArrayAdapter<>(ConsultationMenu.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.consul2));
        consulAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        consulSpinner2.setAdapter(consulAdapter2);

        final String[] text2 = {consulSpinner.getSelectedItem().toString()};


        Spinner consulSpinner3 = (Spinner) findViewById(R.id.spinnerConsul3);

        ArrayAdapter<String> consulAdapter3 = new ArrayAdapter<>(ConsultationMenu.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.consul3));
        consulAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        consulSpinner3.setAdapter(consulAdapter3);

        final String[] text3 = {consulSpinner.getSelectedItem().toString()};

        consulSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                text[0] = item.toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        consulSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                text2[0] = String.valueOf(pos);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        consulSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                text3[0] = item.toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button btn = (Button)findViewById(R.id.buttonConsul);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(getApplicationContext(), ConsultationResult.class);
                //startActivity(i);
                //Toast.makeText(getBaseContext(), text[0], Toast.LENGTH_SHORT).show();
                //Toast.makeText(getBaseContext(), text2[0], Toast.LENGTH_SHORT).show();
                //Toast.makeText(getBaseContext(), text3[0], Toast.LENGTH_SHORT).show();
                checkSytome(text,text2,text3);
            }
        });



    }

    private void checkSytome(final String[] text, String[] text2, final String[] text3) {
        dogsymptoms.child("ListBigDog").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(text[0]).exists()) {
                    Toast.makeText(getBaseContext(), "Big", Toast.LENGTH_SHORT).show();
                    dogsymptoms.child("BigDog").child(text3[0]).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Toast.makeText(getBaseContext(), dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
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
