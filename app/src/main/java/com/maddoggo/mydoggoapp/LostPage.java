package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maddoggo.mydoggoapp.Model.LostFoundDog;
import com.squareup.picasso.Picasso;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class LostPage extends AppCompatActivity {

    private LostFoundDog LFDog;
    private ImageView mLostDogImage;
    private TextView mLostDogType, mLostDogChara, mLostDogLastLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_page);
        Toolbar toolbar = findViewById(R.id.toolbarLostPage);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mLostDogImage = findViewById(R.id.LostDogImage);
        mLostDogType = findViewById(R.id.LostDogType);
        mLostDogChara = findViewById(R.id.LostDogChara);
        mLostDogLastLoc = findViewById(R.id.LostDogLastLoc);
        //Belum ada di database
        //mLostDogPhoneNumber = findViewById(R.id.LostDogPhoneNumber);


        FabSpeedDial fabSpeedDial = findViewById(R.id.fabSpeedDial);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true; //false: don't show menu
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.action_fab_call) {
                    String url = "https://api.whatsapp.com/send?phone="+"6282144477142";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }else if (id == R.id.action_fab_dialpad) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+6282146515988"));
                    startActivity(intent);
                }
                else if (id == R.id.action_fab_message) {
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.setData(Uri.parse("sms:+6282146515988"));
                    startActivity(sendIntent);
                }
                //Toast.makeText(LostPage.this, ""+menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });

        Intent i = getIntent();
        LostFoundDog done = (LostFoundDog) i.getSerializableExtra("LostFoundDogClass");
        toolbar.setTitle(done.getDogName());
        Picasso.with(getBaseContext())
                .load(done.getDogPict())
                .into(mLostDogImage);
        mLostDogType.setText(done.getDogType());
        mLostDogChara.setText(done.getDogChara());
        mLostDogLastLoc.setText("Last known location: " + done.getDogLastSeen());
        //mLostDogPhoneNumber.setText("Phone Number: " + done.getP());
    }

}
