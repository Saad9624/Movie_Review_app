package com.example.danishlaptop.test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class SignupActivity extends AppCompatActivity {

    EditText siginUserID;
    EditText signinUsername;
    EditText signinPassword;
    EditText signinReEnterPassword;
    Button SigninBtn;
    ProgressDialog pd;
    private FirebaseAuth mAuth;
    FirebaseStorage storage;
    DatabaseReference User_info_Ref;
    String updateId;
    String authID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        siginUserID=findViewById(R.id.siginUserID);
        signinUsername = findViewById(R.id.signinUserName);
        signinPassword = findViewById(R.id.signinPassword);
        signinReEnterPassword = findViewById(R.id.signinReEnterPassword);
        SigninBtn =findViewById(R.id.SigninBtn);

        storage = FirebaseStorage.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        User_info_Ref = databaseReference.child("User_Info");

        UserInfo userInfo = new UserInfo();
        updateId = userInfo.getUserid();



        pd = new ProgressDialog(SignupActivity.this);
        pd.setMessage("Authenticating...");
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(SignupActivity.this, MovieMatePro.class));
            finish();
        }



        SigninBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String st_user=signinUsername.getText().toString();
                final String email=siginUserID.getText().toString();
                String st_signinpass=signinPassword.getText().toString();
                String st_repass=signinReEnterPassword.getText().toString();

                if(st_user.isEmpty() && st_signinpass.isEmpty() && st_repass.isEmpty())
                {
                    signinUsername.setError("What's your Name?");
                    signinPassword.setError("Field is Empty");
                    signinReEnterPassword.setError("Field is Empty");
                }

                else if(st_user.isEmpty() && st_signinpass.isEmpty())
                {
                    signinUsername.setError("What's your Name?");
                    signinPassword.setError("Field is Empty");
                }
                else if(st_user.isEmpty() && st_repass.isEmpty())
                {
                    signinUsername.setError("What's your Name?");
                    signinReEnterPassword.setError("Field is Empty");
                }

                else if(st_signinpass.isEmpty() && st_repass.isEmpty())
                {
                    signinPassword.setError("Field is Empty");
                    signinReEnterPassword.setError("Field is Empty");
                }

               else if (st_user.isEmpty()){
                    signinUsername.setError("What's your Name?");
                }

               else if (st_signinpass.isEmpty()){
                    signinPassword.setError("Field is Empty");
                }

                else  if (st_repass.isEmpty()){
                    signinReEnterPassword.setError("Field is Empty");
                }

               else if (!st_repass.matches(st_signinpass)){
                    signinReEnterPassword.setError("Please Enter the correct Password");
                }

                else
                {

                    pd.show();
                   // Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();


                    /*
                    String key=updateId;
                    if(updateId == null)
                    {
                        key = User_info_Ref.push().getKey();
                    }
                    //uploadImage(selectedimg,key);
                    UserInfo uerInfo = new UserInfo(key,st_user,email);
                    User_info_Ref.child(key).setValue(uerInfo);
                    updateId = null;
                    signinUsername.setText("");
                    siginUserID.setText("");
                */




                    mAuth.createUserWithEmailAndPassword(email, st_signinpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            pd.dismiss();
                            if (task.isSuccessful()) {

                                authID = mAuth.getCurrentUser().getUid();

                                /*
                                String key=updateId;
                                if(updateId == null)
                                {
                                    key = User_info_Ref.push().getKey();
                                }

                                */
                                //uploadImage(selectedimg,key);
                                UserInfo uerInfo = new UserInfo(authID,st_user,email);
                                User_info_Ref.child(authID).setValue(uerInfo);
                                updateId = null;
                                signinUsername.setText("");
                                siginUserID.setText("");

                                Intent intent = new Intent(SignupActivity.this, UserHollwoodActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(SignupActivity.this, "SignUp Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    //SignUp(signinUsername.getText().toString(),signinPassword.getText().toString());
                }
            }
        });
    }

    private void SignUp(String email, String password)
    {

    }








    /*
    public void SignClick (View view) {

        String name = String.valueOf(signinUsername.getText().toString());
        String password = String.valueOf(signinPassword.getText().toString());
        String reEnterpassword = String.valueOf(signinReEnterPassword.getText().toString());


        if(name.matches("admin@gmail.com") && password.matches("123") && reEnterpassword.matches("123"))
        {
            Intent intent = new Intent(SignupActivity.this,AdminActivity.class);
            startActivity(intent);
        }
        else {

            Intent intent = new Intent(SignupActivity.this, MovieMatePro.class);
            startActivity(intent);
        }
    }

    */

}
