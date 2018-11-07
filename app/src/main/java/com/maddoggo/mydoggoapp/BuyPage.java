package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class BuyPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                }
                Toast.makeText(BuyPage.this, ""+menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });
    }

}
