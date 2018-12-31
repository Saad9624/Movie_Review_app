package com.example.danishlaptop.test;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HollywoodActionFragment extends Fragment {

    RecyclerAdapter adapter;
    ArrayList<ActionMovies> movies;

    RecyclerView RecyclerViewAction;

    public HollywoodActionFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_action, container, false);

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);

        adapter = new RecyclerAdapter(movies);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        return recyclerView;

    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView MovieTitle;
        TextView MovieDescription;
        android.widget.RatingBar RatingBar;
        TextView RatingText;
        Button MovieTrailer;
        Button ReadMoreBtn;
        ImageView MovieImage;
        LinearLayout ItemLayout;

        public MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_action, parent, false));

            MovieTitle = itemView.findViewById(R.id.MovieTitle);
            MovieDescription=itemView.findViewById(R.id.MovieDescription);
            RatingBar=itemView.findViewById(R.id.RatingBar);
            MovieTrailer=itemView.findViewById(R.id.MovieTrailer);
            ReadMoreBtn=itemView.findViewById(R.id.ReadMoreBtn);
            MovieImage=(ImageView)itemView.findViewById(R.id.MovieImage);
            ItemLayout=itemView.findViewById(R.id.ItemLayout);

        }
    }







    public class RecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
        ArrayList<ActionMovies> movies;

        public RecyclerAdapter(ArrayList<ActionMovies> movies) {
            this.movies = movies;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            ActionMovies movie =  movies.get(position);
            holder.MovieTitle.setText(movie.getName());
            holder.MovieDescription.setText(movie.getDescription());
            holder.MovieTrailer.setText(movie.getLink());
            Glide.with(holder.MovieImage.getContext()).load(movie.getImage()).into(holder.MovieImage);

            final String key = movie.getId();

            holder.ItemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Context context=view.getContext();
                    Intent intent = new Intent(context,DetailedActivity.class);
                    intent.putExtra("KEY",key);
                    context.startActivity(intent);

                }
            });

        }

        @Override
        public int getItemCount() {
            return movies.size();
        }
    }



}
