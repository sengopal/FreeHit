package com.debut.ellipsis.freehit;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CountryItem {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("link")
    private String link;
    @SerializedName("image")
    private String image;
    @SerializedName("result")
    public List<CountryItem> result = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getImage() {
        return image;
    }

    public List<CountryItem> getResults() {
        return result;
    }
}
