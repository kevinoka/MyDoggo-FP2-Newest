package com.maddoggo.mydoggoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
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
import com.maddoggo.mydoggoapp.Model.Adoption;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class AddAdoption extends AppCompatActivity {

    FirebaseStorage storage;
    StorageReference storageRef;

    ImageView imageAddAdoption;

    private FirebaseAuth mAuth;
    private FirebaseDatabase Db;
    private DatabaseReference dogAdoption;

    private Adoption dogAdoptionIn;

    private EditText nameAddAdoption, ageAddAdoption, typeAddAdoption, descAddAdoption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_adoption);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        //Init Storage --> For the dog picture
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        mAuth = FirebaseAuth.getInstance();
        Db = FirebaseDatabase.getInstance();
        dogAdoption = Db.getReference("Adoption");

        dogAdoptionIn = new Adoption() ;

        nameAddAdoption = findViewById(R.id.NameAddAdoption);
        ageAddAdoption = findViewById(R.id.AgeAddAdoption);
        typeAddAdoption = findViewById(R.id.TypeAddAdoption);
        descAddAdoption = findViewById(R.id.DescAddAdoption);
        imageAddAdoption = findViewById(R.id.ImageAddAdoption);
    }

    private void SaveNewAdoption() {

        dogAdoption.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long num = dataSnapshot.getChildrenCount();

                dogAdoptionIn.setDogName(nameAddAdoption.getText().toString());
                dogAdoptionIn.setDogAge(ageAddAdoption.getText().toString());
                dogAdoptionIn.setDogType(typeAddAdoption.getText().toString());
                dogAdoptionIn.setDogDesc(descAddAdoption.getText().toString());
                dogAdoptionIn.setDogId("AdoptDog"+num);

                dogAdoption.child("AdoptDog"+num).setValue(dogAdoptionIn);
                //dogAdoption.child("AdoptDog"+num).child("dogId").setValue("AdoptDog"+num);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_adoption_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.save_new_adoption_button) {
            SaveNewAdoption();
            /* Intent i = new Intent(getApplicationContext(), AdoptionMenu.class);
            startActivity(i);*/
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
                final StorageReference imageFolder = storageRef.child("adoption/"+imageName);
                imageFolder.putFile(saveUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getBaseContext(), "Image was uploaded", Toast.LENGTH_SHORT).show();

                                imageFolder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        dogAdoptionIn.setDogPict(uri.toString());
                                        Toast.makeText(getBaseContext(), "Image was uploaded", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();

                                        Picasso.with(getBaseContext())
                                                .load(dogAdoptionIn.getDogPict())
                                                .into(imageAddAdoption);

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
