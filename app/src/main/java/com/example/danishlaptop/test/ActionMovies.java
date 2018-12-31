package com.example.danishlaptop.test;

/**
 * Created by DANISH.LAPTOP on 1/28/2018.
 */

public class ActionMovies {

    private String id;
    private String name;
    private String category;
    private String description;
    private String link;
    private String image;

    public ActionMovies() {
    }


    public ActionMovies(String id, String name, String category, String description, String link) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.link = link;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public boolean equals(Object obj) {
        ActionMovies anotherContact = (ActionMovies) obj;
        return anotherContact.getId().equals(this.id);
    }

}
