package com.example.danishlaptop.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class UerProfileActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    DatabaseReference users_Ref;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uer_profile);

       final TextView UserName= findViewById(R.id.UserName);
        final TextView UserEmail=findViewById(R.id.UserEmail);

        ImageView imageView=findViewById(R.id.UserImage);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.user3);
        RoundedBitmapDrawable roundedBitmapDrawable= RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundedBitmapDrawable.setCircular(true);
        imageView.setImageDrawable(roundedBitmapDrawable);


        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference();
        users_Ref = reference.child("User_Info");


        users_Ref.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserInfo user1 = dataSnapshot.getValue(UserInfo.class);

                if (user1 != null) {
                    UserName.setText(user1.getUserName());
                }

                UserEmail.setText(mAuth.getCurrentUser().getEmail());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
