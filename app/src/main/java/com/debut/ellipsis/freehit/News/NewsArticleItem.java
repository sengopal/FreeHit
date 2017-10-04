package com.debut.ellipsis.freehit.News;


import com.google.gson.annotations.SerializedName;

public class NewsArticleItem {
    @SerializedName("image")
    private String image;
    @SerializedName("title")
    private String title;
    @SerializedName("article")
    private String desc;
    @SerializedName("date")
    private String date;
    @SerializedName("tag1")
    private String tag1;
    @SerializedName("tag2")
    private String tag2;
    @SerializedName("tag3")
    private String tag3;
    @SerializedName("id")
    private int id;

    public NewsArticleItem(String imageURL, String title, String desc, String date, String tag1, String tag2, String tag3, Integer id) {
        this.image = imageURL;
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public String getTag1() {
        return tag1;
    }

    public Integer getId() {
        return id;
    }

    public String getTag2() {
        return tag2;
    }

    public String getTag3() {
        return tag3;
    }

}
