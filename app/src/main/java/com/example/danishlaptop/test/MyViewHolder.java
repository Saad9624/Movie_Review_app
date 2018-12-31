package com.example.danishlaptop.test;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by DANISH.LAPTOP on 1/31/2018.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView MovieTitle;
    TextView MovieDescription;
    RatingBar RatingBar;
    TextView RatingText;
    Button MovieTrailer;
    Button ReadMoreBtn;
    ImageView MovieImage;
    LinearLayout ItemLayout;

    public MyViewHolder(View itemView) {
        super(itemView);

        MovieTitle = (TextView)itemView.findViewById(R.id.MovieTitle);
        MovieDescription=(TextView)itemView.findViewById(R.id.MovieDescription);
        RatingBar=(RatingBar)itemView.findViewById(R.id.RatingBar);
        MovieTrailer=(Button)itemView.findViewById(R.id.MovieTrailer);
 ReadMoreBtn=(Button)itemView.findViewById(R.id.ReadMoreBtn);
 MovieImage=(ImageView)itemView.findViewById(R.id.MovieImage);
 ItemLayout=itemView.findViewById(R.id.ItemLayout);

    }
    }
