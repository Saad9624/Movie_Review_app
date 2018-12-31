package com.example.danishlaptop.test;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by DANISH.LAPTOP on 5/1/2018.
 */

public class ViewHolderForComments extends RecyclerView.ViewHolder {

    TextView UserNameWhoCommented;
    TextView UserComment;
    LinearLayout ItemLayoutForComments;

    public ViewHolderForComments(View itemView) {
        super(itemView);
        UserNameWhoCommented = itemView.findViewById(R.id.UserNameWhoCommented);
        UserComment = itemView.findViewById(R.id.UserComment);
    }
}
