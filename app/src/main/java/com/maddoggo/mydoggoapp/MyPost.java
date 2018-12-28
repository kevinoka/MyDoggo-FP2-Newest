package com.maddoggo.mydoggoapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.maddoggo.mydoggoapp.Fragment.LostDogPostFragment;
import com.maddoggo.mydoggoapp.Fragment.SaleDogPostFragment;

public class MyPost extends AppCompatActivity {

    private TabLayout tabLayout;
    //private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        tabLayout = findViewById(R.id.TabLayoutMyPost);
        //appBarLayout = findViewById(R.id.AppBarMyPost);
        viewPager = findViewById(R.id.viewPagerMyPost);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //Adding fragments
        adapter.AddFragment(new SaleDogPostFragment(),"Sale Dog");
        adapter.AddFragment(new LostDogPostFragment(), "Lost Dog");

        //Adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

}
