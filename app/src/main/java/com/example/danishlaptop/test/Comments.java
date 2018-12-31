package com.example.danishlaptop.test;

/**
 * Created by DANISH.LAPTOP on 4/22/2018.
 */

public class Comments {


    private String comments;
    private String id;


    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Comments(String id, String comments) {

        this.comments = comments;
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        Comments moviescomments= (Comments)obj;

        return moviescomments.getId().equals(this.id);
    }
}


