package com.example.danishlaptop.test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText EmailText = findViewById(R.id.EmailText);
        final EditText PassText = findViewById(R.id.PassText);


        final Button LogBtn = findViewById(R.id.LogBtn);
        final Button SignupBtn = findViewById(R.id.SignupBtn);


        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Logging in...");

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(MainActivity.this, MovieMatePro.class));
            finish();
        }

        LogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st_Email = EmailText.getText().toString();
                String st_Pass = PassText.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (st_Email.isEmpty() && st_Pass.isEmpty()) {
                    EmailText.setError("What's your Email?");
                    PassText.setError("Enter password");
                } else if (st_Email.isEmpty()) {

                    EmailText.setError("What's your Email?");
                } else if (!st_Email.matches(emailPattern)) {

                    EmailText.setError("Enter Valid Email");
                } else if (st_Pass.isEmpty()) {

                    PassText.setError("Enter password");
                }

                  else if(st_Email.matches("admin@gmail.com") && st_Pass.matches("123"))
                    {
                        Intent intent = new Intent(MainActivity.this,AdminActivity.class);
                        startActivity(intent);
                    }

                 else {

                    //Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();

                   // Intent intent = new Intent(MainActivity.this, MovieMatePro.class);
                    //startActivity(intent);

                    loginMethod(EmailText.getText().toString(),PassText.getText().toString()
                    );


                }


            }
        });


        SignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

    }



    public void loginMethod(String email, String password)
    {
        pd.show();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                pd.dismiss();
                if (task.isSuccessful()) {
                    Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(MainActivity.this, "SignUp Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


}

