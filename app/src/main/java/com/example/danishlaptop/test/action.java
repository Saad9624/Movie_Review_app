package com.example.danishlaptop.test;

/**
 * Created by DANISH.LAPTOP on 12/29/2017.
 */

public class action {

    String Title;
    String description;

    public action(String title, String description) {
        Title = title;
        this.description = description;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
