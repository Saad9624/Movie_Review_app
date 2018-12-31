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

public class RecyclerAdapterForcomments extends RecyclerView.Adapter<ViewHolderForComments> {
    ArrayList<Comments> comm;


    public RecyclerAdapterForcomments(ArrayList<Comments> comm)
    {
        this.comm= comm;
    }


    @Override
    public ViewHolderForComments onCreateViewHolder(ViewGroup parent, int viewType) {
        View myItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayoutforcomments,parent,false);
        ViewHolderForComments ViewHolder = new ViewHolderForComments(myItemView);
        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderForComments Cholder, int Cposition) {
        Comments comm1 =  comm.get(Cposition);
        Cholder.UserNameWhoCommented.setText(comm1.getId());
        Cholder.UserComment.setText(comm1.getComments());



        final String key = comm1.getId();

       /* Cholder.ItemLayout.setOnClickListener(new View.OnClickListener() {
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
*/
    }


    @Override
    public int getItemCount() {
        return comm.size();
    }
}
