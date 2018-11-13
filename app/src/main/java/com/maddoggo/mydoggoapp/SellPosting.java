package com.maddoggo.mydoggoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.maddoggo.mydoggoapp.Model.SaleDog;
import com.maddoggo.mydoggoapp.Model.User;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SellPosting extends AppCompatActivity {

    FirebaseStorage storage;
    StorageReference storageRef;

    ImageView imageView;

    private FirebaseDatabase Db;
    private DatabaseReference saleDog;
    private SaleDog saleDogIn;

    private EditText editNameSellPosting, editPriceSellPosting, editLocationSellPosting, editDescSellPosting, editPhoneSellPosting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_posting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Init Storage --> For the dog picture
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        Db = FirebaseDatabase.getInstance();
        saleDog = Db.getReference("SaleDogList");

        saleDogIn = new SaleDog() ;

        editNameSellPosting = findViewById(R.id.EditNameSellPosting);
        editPriceSellPosting = findViewById(R.id.EditPriceSellPosting);
        editLocationSellPosting = findViewById(R.id.EditLocationSellPosting);
        editDescSellPosting = findViewById(R.id.EditDescSellPosting);
        editPhoneSellPosting = findViewById(R.id.EditPhoneSellPosting);
        //getCurrentInfo();

        imageView = findViewById(R.id.ImageSellPosting);

        editPriceSellPosting.setText("Rp ");
        Selection.setSelection(editPriceSellPosting.getText(), editPriceSellPosting.getText().length());


        editPriceSellPosting.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().startsWith("Rp ")){
                    editPriceSellPosting.setText("Rp ");
                    Selection.setSelection(editPriceSellPosting.getText(), editPriceSellPosting.getText().length());

                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

        });
    }

    private void SaveSellDog() {

        saleDog.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long no = dataSnapshot.getChildrenCount();


                saleDogIn.setDogName(editNameSellPosting.getText().toString());
                saleDogIn.setPrice(editPriceSellPosting.getText().toString());
                saleDogIn.setSellerLocation(editLocationSellPosting.getText().toString());
                saleDogIn.setDogDesc(editDescSellPosting.getText().toString());
                saleDogIn.setPhoneNumber(editPhoneSellPosting.getText().toString());

                saleDog.child("SaleDog"+no).setValue(saleDogIn);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            SaveSellDog();
            Intent i = new Intent(getApplicationContext(), BuyOrSellMenu.class);
            startActivity(i);
            this.finish();

        }
        return super.onOptionsItemSelected(item);
    }

    public void imageClicked (View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select picture"), 9999);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 9999 && resultCode == RESULT_OK && data != null && data.getData() != null){
            final Uri saveUri = data.getData();
            if(saveUri != null){
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Uploading...");
                progressDialog.show();

                String imageName = UUID.randomUUID().toString(); //random new image name to upload
                final StorageReference imageFolder = storageRef.child("buysell/"+imageName);
                imageFolder.putFile(saveUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getBaseContext(), "Image was uploaded", Toast.LENGTH_SHORT).show();

                                imageFolder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        saleDogIn.setSellDogImage(uri.toString());
                                        Toast.makeText(getBaseContext(), "Image was uploaded", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();

                                        Picasso.with(getBaseContext())
                                                .load(saleDogIn.getSellDogImage())
                                                .into(imageView);

                                    }
                                });
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                                progressDialog.setMessage("Uploaded "+progress+"%");
                            }
                        });
            }
        }
    }

}
