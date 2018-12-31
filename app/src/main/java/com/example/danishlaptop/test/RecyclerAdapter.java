package com.example.danishlaptop.test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by DANISH.LAPTOP on 1/31/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    ArrayList<ActionMovies> movies;


    public RecyclerAdapter(ArrayList<ActionMovies> movies) {
        this.movies = movies;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(myItemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ActionMovies movie =  movies.get(position);
        holder.MovieTitle.setText(movie.getName());
        holder.MovieDescription.setText(movie.getDescription());
        holder.MovieTrailer.setText(movie.getLink());
        Glide.with(holder.MovieImage.getContext()).load(movie.getImage()).into(holder.MovieImage);

        final String key = movie.getId();


        //detail khulwany k lie kisi 1 item ka//

        holder.ItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context=view.getContext();
                Intent intent = new Intent(context,DetailedActivity.class);
                //Intent intent1 = new Intent(context,Commentsubclass.class);
                //intent1.putExtra("KEY",key);
                intent.putExtra("KEY",key);
                context.startActivity(intent);
                //context.startActivity(intent1);

            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
