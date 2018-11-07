package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.maddoggo.mydoggoapp.Model.User;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfile extends AppCompatActivity {

    private FirebaseStorage storage;
    private StorageReference storageRef;

    private FirebaseAuth mAuth;
    private FirebaseDatabase Db;
    private DatabaseReference users;

    private TextView mName, mEmail, mPhone, mAddress, mBirthday;
    private CircleImageView mProfilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditProfile.class);
                startActivity(i);
                finish();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        Db = FirebaseDatabase.getInstance();
        users = Db.getReference("Users");

        //Init Storage --> For profile picture
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        mName = findViewById(R.id.NameInProfile);
        mEmail = findViewById(R.id.EmailInProfile);
        mPhone = findViewById(R.id.PhoneInProfile);
        mAddress = findViewById(R.id.AddressInProfile);
        mBirthday = findViewById(R.id.BirthdayInProfile);
        getCurrentInfo();

        mProfilePic = findViewById(R.id.ProfileImage);

        users.child(mAuth.getCurrentUser().getUid().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.child("avatarUrl").exists()){
                    Picasso.with(getBaseContext())
                            .load(dataSnapshot.child("avatarUrl").getValue().toString())
                            .into(mProfilePic);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void getCurrentInfo() {
        users.child(mAuth.getCurrentUser().getUid().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);

                mName.setText(user.getFirstName() + " " + user.getLastName());
                mEmail.setText(user.getEmailAddress());
                mPhone.setText(dataSnapshot.child("phoneNumber").getValue().toString());
                mAddress.setText(dataSnapshot.child("address").getValue().toString());
                mBirthday.setText(dataSnapshot.child("birthday").getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

}
