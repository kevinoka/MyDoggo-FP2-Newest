package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maddoggo.mydoggoapp.Model.Adoption;
import com.squareup.picasso.Picasso;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class AdoptNow extends AppCompatActivity {

    private Adoption adoption;
    private ImageView mAdoptNowImage;
    private TextView mAdoptNowName, mAdoptNowType, mAdoptNowAge, mAdoptNowDesc, mAdoptNowLocation, mAdoptNowPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_now);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAdoptNowImage = findViewById(R.id.AdoptNowImage);
        mAdoptNowName = findViewById(R.id.AdoptNowName);
        mAdoptNowType = findViewById(R.id.AdoptNowType);
        mAdoptNowAge = findViewById(R.id.AdoptNowAge);
        mAdoptNowDesc = findViewById(R.id.AdoptNowDesc);
        mAdoptNowLocation = findViewById(R.id.AdoptNowLocation);
        mAdoptNowPhoneNumber = findViewById(R.id.AdoptNowPhoneNumber);

        Intent i = getIntent();
        final Adoption adopt  = (Adoption) i.getSerializableExtra("AdoptionClass");
        Picasso.with(getBaseContext())
                .load(adopt.getDogPict())
                .into(mAdoptNowImage);
        mAdoptNowName.setText(adopt.getDogName());
        mAdoptNowType.setText(adopt.getDogType());
        mAdoptNowAge.setText(adopt.getDogAge());
        mAdoptNowDesc.setText(adopt.getDogDesc());
        mAdoptNowLocation.setText("Location: " + adopt.getLocation());
        mAdoptNowPhoneNumber.setText("Phone number: " + adopt.getPhoneNum());


        FabSpeedDial fabSpeedDial = findViewById(R.id.fabSpeedDialAN);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true; //false: don't show menu
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.action_fab_dialpad1) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + adopt.getPhoneNum().toString()));
                    startActivity(intent);
                }
                else if (id == R.id.action_fab_message1) {
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.setData(Uri.parse("sms:" + adopt.getPhoneNum().toString()));
                    startActivity(sendIntent);
                }
                //Toast.makeText(BuyPage.this, ""+menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });
    }

}
