package com.telstra.task.model;

import java.util.ArrayList;

/**
 * Created by Ruban on 1/22/2016.
 * This is a Model class for list item
 */


public class ListItem {
    String title;
    String description;
    String imgUrl;

    //constructor to assign values
    public ListItem(String title, String description, String imgUrl) {
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {return title;}//return title
    public String getDescription() {
        return description;
    }//return desc
    public String getImgUrl() {
        return imgUrl;
    }//return umg url

    //used to hold list item data
    public static ArrayList<ListItem> listItemsArray=new ArrayList<ListItem>();



}