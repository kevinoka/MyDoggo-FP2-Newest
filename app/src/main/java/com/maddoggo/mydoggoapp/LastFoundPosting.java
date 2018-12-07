package com.maddoggo.mydoggoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import com.maddoggo.mydoggoapp.Model.LostFoundDog;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class LastFoundPosting extends AppCompatActivity {

    FirebaseStorage storage;
    StorageReference storageRef;

    ImageView imageView;

    private FirebaseAuth mAuth;
    private FirebaseDatabase Db;
    private DatabaseReference lostFoundDog;
    private DatabaseReference lostFoundDogByUser;
    private LostFoundDog lostFoundDogIn;

    private EditText editLFDogName, editLFDogTypeLF, editLFDogLastSeen, editLFChara, editLFPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_found_posting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Init Storage --> For the dog picture
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        mAuth = FirebaseAuth.getInstance();
        Db = FirebaseDatabase.getInstance();
        lostFoundDog = Db.getReference("LostFoundList");
        lostFoundDogByUser = Db.getReference("LostFoundUserList");

        lostFoundDogIn = new LostFoundDog() ;

        editLFDogName = findViewById(R.id.EditNameLF);
        editLFDogTypeLF = findViewById(R.id.EditDogTypeLF);
        editLFDogLastSeen = findViewById(R.id.EditLocationLF);
        editLFChara = findViewById(R.id.EditDescLF);
        editLFPhone = findViewById(R.id.EditPhoneLF);

        imageView = findViewById(R.id.ImageLFPosting);
        
    }

    private void SaveLFDog() {

        lostFoundDog.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long no = dataSnapshot.getChildrenCount();


                lostFoundDogIn.setDogName(editLFDogName.getText().toString());
                lostFoundDogIn.setDogType(editLFDogTypeLF.getText().toString());
                lostFoundDogIn.setDogLastSeen(editLFDogLastSeen.getText().toString());
                lostFoundDogIn.setDogChara(editLFChara.getText().toString());
                //lostFoundDogIn.setPhoneNumber(editLFPhone.getText().toString());

                lostFoundDogIn.setOwner(mAuth.getCurrentUser().getUid());

                lostFoundDog.child("LFDog"+no).setValue(lostFoundDogIn);

                lostFoundDogByUser.child(mAuth.getCurrentUser().getUid()).child("LFDog"+no).setValue(true);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
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
            SaveLFDog();
            Intent i = new Intent(getApplicationContext(), LostFoundMenu.class);
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
                final StorageReference imageFolder = storageRef.child("lostfound/"+imageName);
                imageFolder.putFile(saveUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getBaseContext(), "Image uploaded successfully", Toast.LENGTH_SHORT).show();

                                imageFolder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        lostFoundDogIn.setDogPict(uri.toString());
                                        Toast.makeText(getBaseContext(), "Image uploaded successfully", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();

                                        Picasso.with(getBaseContext())
                                                .load(lostFoundDogIn.getDogPict())
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
