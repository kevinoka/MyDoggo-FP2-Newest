package com.maddoggo.mydoggoapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.maddoggo.mydoggoapp.Fragment.PetShopLocFragment;
import com.maddoggo.mydoggoapp.Fragment.VeterinaryLocFragment;


public class LocationMenu extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = findViewById(R.id.TabLayoutLocationMenu);
        viewPager = findViewById(R.id.viewPagerLocationMenu);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //Adding fragments
        adapter.AddFragment(new PetShopLocFragment(),"Pet Shop");
        adapter.AddFragment(new VeterinaryLocFragment(), "Veterinarian");

        //Adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

}