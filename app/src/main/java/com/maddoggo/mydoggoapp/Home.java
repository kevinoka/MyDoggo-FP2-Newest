package com.maddoggo.mydoggoapp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.maddoggo.mydoggoapp.Model.User;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private CardView adoptionCard,buySellCard,breedingCard,lostFoundCard,consultationCard,doggopediaCard,locationCard;

    FirebaseStorage storage;
    StorageReference storageRef;

    private FirebaseAuth mAuth;
    private FirebaseDatabase Db;
    private DatabaseReference users;

    private TextView mName, mEmail;
    private CircleImageView mProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*getSupportActionBar().setLogo(R.drawable.mydoggo_text);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //For the nav drawer
        mAuth = FirebaseAuth.getInstance();
        Db = FirebaseDatabase.getInstance();
        users = Db.getReference("Users");

        //Init Storage --> For user profile picture
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navigationHeaderView = navigationView.getHeaderView(0);
        mName  =  navigationHeaderView.findViewById(R.id.textViewUsernameNav);
        mEmail = navigationHeaderView.findViewById(R.id.textViewEmailNav);
        getCurrentInfo();

        mProfileImage = navigationHeaderView.findViewById(R.id.NavImageView);

        users.child(mAuth.getCurrentUser().getUid().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.child("avatarUrl").exists()){
                    Picasso.with(getBaseContext())
                            .load(dataSnapshot.child("avatarUrl").getValue().toString())
                            .into(mProfileImage);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        //Defining Cards
        adoptionCard = findViewById(R.id.adoption_card);
        buySellCard = findViewById(R.id.buy_sell_card);
        breedingCard = findViewById(R.id.breeding_card);
        lostFoundCard = findViewById(R.id.lost_found_card);
        consultationCard = findViewById(R.id.consultation_card);
        doggopediaCard = findViewById(R.id.doggopedia_card);
        locationCard = findViewById(R.id.location_card);

        //Add onClick Listener to the cards
        adoptionCard.setOnClickListener(this);
        buySellCard.setOnClickListener(this);
        breedingCard.setOnClickListener(this);
        lostFoundCard.setOnClickListener(this);
        consultationCard.setOnClickListener(this);
        doggopediaCard.setOnClickListener(this);
        locationCard.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.adoption_card:
                i = new Intent(this, AdoptionMenu.class);
                startActivity(i);
                break;
            case R.id.buy_sell_card:
                i = new Intent(this, BuyOrSellMenu.class);
                startActivity(i);
                break;
            case R.id.breeding_card:
                i = new Intent(this, BreedingMenu.class);
                startActivity(i);
                break;
            case R.id.lost_found_card:
                i = new Intent(this, LostFoundMenu.class);
                startActivity(i);
                break;
            case R.id.consultation_card:
                i = new Intent(this, ConsultationMenu.class);
                startActivity(i);
                break;
            case R.id.doggopedia_card:
                i = new Intent(this, DoggopediaMenu.class);
                startActivity(i);
                break;
            case R.id.location_card:
                i = new Intent(this, MapsActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent i;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            //Profile function
            i = new Intent(this, MyProfile.class);
            startActivity(i);
            item.setChecked(true);
        } else if (id == R.id.nav_logout) {
            //Logout function
            logOut();
        }
        else if (id == R.id.nav_my_post) {
            i = new Intent(this, MyPost.class);
            startActivity(i);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    private void logOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Home.this,MainActivity.class);
        startActivity(intent);
        finish();
        SaveSharedPreference.clearUserName(Home.this);

    }


    private void getCurrentInfo(){
        users.child(mAuth.getCurrentUser().getUid().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);

                mName.setText(user.getFirstName()+" "+user.getLastName());
                mEmail.setText(user.getEmailAddress());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
