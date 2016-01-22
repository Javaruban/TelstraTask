package com.telstra.task.model;

import java.util.ArrayList;

public class ListItem {
    String title;
    String description;
    String imgUrl;

    public ListItem(String title, String description, String imgUrl) {
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public static ArrayList<ListItem> listItemsArray=new ArrayList<ListItem>();



}