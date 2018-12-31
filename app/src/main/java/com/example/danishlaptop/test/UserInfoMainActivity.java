package com.example.danishlaptop.test;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class UserInfoMainActivity extends AppCompatActivity {

 TextView UserName;
 ImageView UserImage;
 private final int PICK_IMAGE=100;
    String updateId;
 Uri selectedimg;
 ArrayList<UserInfo>userinfo;
 DatabaseReference User_Info_Ref;
    FirebaseStorage storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_main);

        UserName=findViewById(R.id.UserName);
        UserImage=findViewById(R.id.UserImage);





        storage = FirebaseStorage.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        User_Info_Ref = databaseReference.child("User_Info");

        /*UserInfo userInfo = new UserInfo();

        updateId = UserInfo.getId();

*/
    }


}
