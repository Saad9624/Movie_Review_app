package com.example.danishlaptop.test;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;

public class AdminaddActionActivity extends AppCompatActivity {

    ImageView AdminImage;
    private final int PICK_IMAGE = 100;
    Uri selectedimg;
    String updateId;
    ArrayList<ActionMovies> actionMovies;
    DatabaseReference action_movies_Ref;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminadd_action);


        final TextView AdminUploadMovieName=findViewById(R.id.AdminUploadMovieName);
        final TextView AdminMovieCat=findViewById(R.id.AdminMovieCat);
        final TextView AdminAboutMovie=findViewById(R.id.AdminAboutMovie);
        final TextView AdminMovieTrailer=findViewById(R.id.AdminMovieTrailer);
        Button AdminAddActionMovieBtn=findViewById(R.id.AdminAddActionMovieBtn);
        AdminImage = findViewById(R.id.AdminImage);


        AdminImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });



        storage = FirebaseStorage.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        action_movies_Ref = databaseReference.child("Action_Movies");

        ActionMovies actionMovies = new ActionMovies();
        updateId = actionMovies.getId();


        AdminAddActionMovieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // AdminRatingText.setText("Movie Rating is: " + RatingBarAdmin.getRating());

                String name = AdminUploadMovieName.getText().toString();
                String category = AdminMovieCat.getText().toString();
                String aboutmovie = AdminAboutMovie.getText().toString();
                String link = AdminMovieTrailer.getText().toString();
                String key = updateId;
                if(updateId == null)
                {
                    key = action_movies_Ref.push().getKey();
                }
                uploadImage(selectedimg,key);
                ActionMovies actmovie = new ActionMovies(key,name,category,aboutmovie,link);
                action_movies_Ref.child(key).setValue(actmovie);
                updateId = null;
                AdminUploadMovieName.setText("");
                AdminMovieCat.setText("");
                AdminAboutMovie.setText("");
                AdminMovieTrailer.setText("");
                AdminImage.setImageResource(R.drawable.ic_icon_add);


            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            selectedimg = data.getData();
            try {
                AdminImage.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedimg));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(resultCode == Activity.RESULT_CANCELED)
        {
            super.onBackPressed();
        }
    }

    private void uploadImage(Uri uri, final String key){
        if(uri == null){
            return;
        }

        StorageReference storageReference = storage.getReference();
        StorageReference fileRef = storageReference.child(key+".jpg");
        UploadTask uploadTask = fileRef.putFile(uri);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                String downloadUrlStr = downloadUrl.toString();
                action_movies_Ref.child(key).child("image").setValue(downloadUrlStr);
                Toast.makeText(AdminaddActionActivity.this,"Upload Successful",Toast.LENGTH_SHORT).show();
            }
        });
    }




}
