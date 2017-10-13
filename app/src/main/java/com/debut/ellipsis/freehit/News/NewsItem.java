package com.debut.ellipsis.freehit.News;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NewsItem {
    @SerializedName("image")
    private String image;
    @SerializedName("title")
    private String title;
    @SerializedName("desc")
    private String desc;
    @SerializedName("date")
    private String date;
    @SerializedName("tag")
    private String tag;
    @SerializedName("id")
    private int id;
    @SerializedName("result")
    public List<NewsItem> results = new ArrayList<>();


    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
    //
    public String getDate() {
        return date;
    }

    public String getTag() {
        return tag;
    }

    public Integer getId() {
        return id;
    }

    public List<NewsItem> getResults() {
        return results;
    }

}