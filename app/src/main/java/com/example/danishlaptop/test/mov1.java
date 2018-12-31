package com.example.danishlaptop.test;

/**
 * Created by DANISH.LAPTOP on 12/21/2017.
 */
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class mov1 extends Fragment {
    private FirebaseAuth mAuth;
    DatabaseReference actionmoviesRef;
    FirebaseStorage storage;

    ArrayList<ActionMovies> movies;
    RecyclerAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);
        return rootView;


    }

}
