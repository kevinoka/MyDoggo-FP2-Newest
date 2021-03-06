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
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.maddoggo.mydoggoapp.Model.User;
import com.squareup.picasso.Picasso;

import com.google.firebase.database.ValueEventListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    FirebaseStorage storage;
    StorageReference storageRef;

    RelativeLayout rootLayEdit;

    ProgressDialog progressDialog;
    UploadTask uploadTask;
    CircleImageView imageView;

    private FirebaseAuth mAuth;
    private FirebaseDatabase Db;
    private DatabaseReference users;

    private EditText editFirstName, editLastName, editEmail, editPhone, editAddress, editBirthday;
    private Button mUploadButton;
    //private Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mAuth = FirebaseAuth.getInstance();
        Db = FirebaseDatabase.getInstance();
        users = Db.getReference("Users");

        //Init Storage --> For the profile picture
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        rootLayEdit = findViewById(R.id.rootLayEdit);


        editFirstName = findViewById(R.id.EditFirstNameProfile);
        Selection.setSelection(editFirstName.getText(), editFirstName.getText().length());
        editLastName = findViewById(R.id.EditLastNameProfile);
        Selection.setSelection(editLastName.getText(), editLastName.getText().length());
        editEmail = findViewById(R.id.EditEmailProfile);
        Selection.setSelection(editEmail.getText(), editEmail.getText().length());
        editPhone = findViewById(R.id.EditPhoneProfile);
        Selection.setSelection(editPhone.getText(), editPhone.getText().length());
        editAddress = findViewById(R.id.EditAddressProfile);
        Selection.setSelection(editAddress.getText(), editAddress.getText().length());
        editBirthday = findViewById(R.id.EditBirthdayProfile);
        Selection.setSelection(editBirthday.getText(), editBirthday.getText().length());


        getCurrentInfo();


        editFirstName.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /* TODO Auto-generated method stub */

            }

            @Override
            public void afterTextChanged(Editable s) {
                    Selection.setSelection(editFirstName.getText(), editFirstName.getText().length());
                }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

        });

        editLastName.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                Selection.setSelection(editLastName.getText(), editLastName.getText().length());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

        });

        editEmail.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                Selection.setSelection(editEmail.getText(), editEmail.getText().length());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

        });

        editPhone.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                Selection.setSelection(editPhone.getText(), editPhone.getText().length());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

        });

        editAddress.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                Selection.setSelection(editAddress.getText(), editAddress.getText().length());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

        });

        editBirthday.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                Selection.setSelection(editBirthday.getText(), editBirthday.getText().length());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

        });


        editBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        EditProfile.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(),"Datepickerdialog");

            }
        });

        imageView = findViewById(R.id.imageViewProfile);

        mUploadButton = findViewById(R.id.UploadPPButton);

        mUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select picture"), 9999);
            }
        });

        users.child(mAuth.getCurrentUser().getUid().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

              if (dataSnapshot.child("avatarUrl").exists()){
                  Picasso.with(getBaseContext())
                          .load(dataSnapshot.child("avatarUrl").getValue().toString())
                          .into(imageView);
              }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void getCurrentInfo() {
        users.child(mAuth.getCurrentUser().getUid().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);

                editFirstName.setText(user.getFirstName());
                editLastName.setText(user.getLastName());
                editEmail.setText(user.getEmailAddress());
                editPhone.setText(dataSnapshot.child("phoneNumber").getValue().toString());
                editAddress.setText(dataSnapshot.child("address").getValue().toString());
                editBirthday.setText(dataSnapshot.child("birthday").getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    @Override
    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = String.format("%02d/%02d/%02d",dayOfMonth,(monthOfYear+1),year%100);
        editBirthday.setText(date);
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu_edit_profile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.SaveButtonEditProfile) {

            if(TextUtils.isEmpty(editEmail.getText().toString()))
            {
                Snackbar.make(rootLayEdit, "Email cannot be empty", Snackbar.LENGTH_SHORT)
                        .show();
                return false;
            }
            else if(TextUtils.isEmpty(editFirstName.getText().toString())){
                Snackbar.make(rootLayEdit, "First name cannot be empty", Snackbar.LENGTH_SHORT)
                        .show();
                return false;
            }
            else if(TextUtils.isEmpty(editLastName.getText().toString())){
                Snackbar.make(rootLayEdit, "Last name cannot be empty", Snackbar.LENGTH_SHORT)
                        .show();
                return false;
            }
            else if(TextUtils.isEmpty(editBirthday.getText().toString())) {
                Snackbar.make(rootLayEdit, "Birthday cannot be empty", Snackbar.LENGTH_SHORT)
                        .show();
                return false;
            }
            else{
                saveProfile();
                Intent i = new Intent(getApplicationContext(), MyProfile.class);
                startActivity(i);
                this.finish();
            }

        }
        return super.onOptionsItemSelected(item);
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
                final StorageReference imageFolder = storageRef.child("images/"+imageName);
                imageFolder.putFile(saveUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getBaseContext(), "Avatar was uploaded", Toast.LENGTH_SHORT).show();

                                imageFolder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        //Save image url to User user information table
                                        Map<String, Object> avatarUpdate = new HashMap<>();
                                        avatarUpdate.put("avatarUrl",uri.toString());

                                        DatabaseReference userInformation = FirebaseDatabase.getInstance().getReference("Users");
                                        userInformation.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .updateChildren(avatarUpdate)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()){
                                                            Toast.makeText(getBaseContext(), "Avatar was uploaded", Toast.LENGTH_SHORT).show();
                                                            progressDialog.dismiss();
                                                        }

                                                        else
                                                            Toast.makeText(getBaseContext(), "Upload error", Toast.LENGTH_SHORT).show();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                });
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                                progressDialog.setMessage("Uploaded "+progress+"%");
                            }
                        });
            }
        }
    }

    private void saveProfile() {
        users.child(mAuth.getCurrentUser().getUid()).child("firstName").setValue(editFirstName.getText().toString());
        users.child(mAuth.getCurrentUser().getUid()).child("lastName").setValue(editLastName.getText().toString());
        users.child(mAuth.getCurrentUser().getUid()).child("emailAddress").setValue(editEmail.getText().toString());
        users.child(mAuth.getCurrentUser().getUid()).child("phoneNumber").setValue(editPhone.getText().toString());
        users.child(mAuth.getCurrentUser().getUid()).child("address").setValue(editAddress.getText().toString());
        users.child(mAuth.getCurrentUser().getUid()).child("birthday").setValue(editBirthday.getText().toString());
    }

}
