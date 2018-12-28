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

import com.maddoggo.mydoggoapp.Model.SaleDog;
import com.squareup.picasso.Picasso;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class BuyPage extends AppCompatActivity {

    private SaleDog saleDog;
    private ImageView mBuyDogImage;
    private TextView mBuyDogPrice, mBuyDogDesc, mBuyDogLocation, mBuyDogPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_page);
        Toolbar toolbar = findViewById(R.id.toolbarBuyPage);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBuyDogImage = findViewById(R.id.BuyDogImage);
        mBuyDogPrice = findViewById(R.id.BuyDogPrice);
        mBuyDogDesc = findViewById(R.id.BuyDogDesc);
        mBuyDogLocation = findViewById(R.id.BuyDogLocation);
        mBuyDogPhoneNumber = findViewById(R.id.BuyDogPhoneNumber);

        Intent i = getIntent();
        final SaleDog done = (SaleDog) i.getSerializableExtra("SaleDogClass");
        //saleDog = getIntent().getSerializableExtra("SaleDogClass");
        toolbar.setTitle(done.getDogName());
        Picasso.with(getBaseContext())
                .load(done.getSellDogImage())
                .into(mBuyDogImage);
        mBuyDogPrice.setText(done.getPrice());
        mBuyDogDesc.setText(done.getDogDesc());
        mBuyDogLocation.setText("Location: " + done.getSellerLocation());
        mBuyDogPhoneNumber.setText("Phone Number: " + done.getPhoneNumber());


        FabSpeedDial fabSpeedDial = findViewById(R.id.fabSpeedDial);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true; //false: don't show menu
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();

                /*if (id == R.id.action_fab_call) {
                    String url = "https://api.whatsapp.com/send?phone=62"+done.getPhoneNumber();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }*/
                if (id == R.id.action_fab_dialpad) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + done.getPhoneNumber().toString()));
                    startActivity(intent);
                }
                else if (id == R.id.action_fab_message) {
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.setData(Uri.parse("sms:" + done.getPhoneNumber().toString()));
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