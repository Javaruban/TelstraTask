package com.telstra.task.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ruban on 1/22/2016.
 * This is a Model class for list item Which connects to GSON
 */
public class ListItem {

    String title;
    String description;
    @SerializedName("imageHref")
    String imgUrl;

    /**
     * empty constructor
     */
    public ListItem() {

    }

    /**
     * constructor to assign values
     */
    public ListItem(String title, String description, String imgUrl) {
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;//return title
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;//return desc
    }

    /**
     * @return image url
     */
    public String getImgUrl() {
        return imgUrl;//return umg url
    }



}