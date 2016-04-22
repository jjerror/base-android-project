package com.example.jyuen.baseproject.model.flickr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jyuen on 1/17/16.
 */
public class SearchResult implements Serializable {

    int page;
    int pages;

    ArrayList<Photo> photo;

    public List<Photo> getItems() {
        return photo;
    }

    public int getNextPage () {
        return page + 1;
    }

    public boolean hasMorePage () {
        return page < pages;
    }
}
