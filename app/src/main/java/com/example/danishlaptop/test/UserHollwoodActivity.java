package com.example.danishlaptop.test;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class UserHollwoodActivity extends AppCompatActivity{

    private FirebaseAuth mAuth;
    DatabaseReference actionmoviesRef;
    FirebaseStorage storage;

    ArrayList<ActionMovies> movies;
    RecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hollywood__user)
        ;

        
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);




        final ImageView UserProfileBtn = findViewById(R.id.UserProfileBtn);
        UserProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHollwoodActivity.this, UerProfileActivity.class);
                startActivity(intent);
            }
        });

         //For retrieval from firebase//


        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference();
        actionmoviesRef = reference.child("Action_Movies");


        actionmoviesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ActionMovies actionMovies = dataSnapshot.getValue(ActionMovies.class);
                movies.add(actionMovies);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                ActionMovies actionMovies = dataSnapshot.getValue(ActionMovies.class);
                int indexOfItem = movies.indexOf(actionMovies);
                if (indexOfItem >= 0) {
                    movies.set(indexOfItem, actionMovies);
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                ActionMovies actionMovies = dataSnapshot.getValue(ActionMovies.class);
                int indexOfItem = movies.indexOf(actionMovies);
                movies.remove(indexOfItem);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        movies = new ArrayList<>();
        adapter = new RecyclerAdapter(movies);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }



}
