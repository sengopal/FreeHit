package com.debut.ellipsis.freehit;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PlayerCountryItem {
    @SerializedName("countryid")
    private int countryid;

    @SerializedName("team")
    private String team;

    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private String image;

    @SerializedName("link")
    private String link;

    @SerializedName("result")
    public List<PlayerCountryItem> result = new ArrayList<>();

    public int getCountryid() {
        return countryid;
    }

    public String getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public List<PlayerCountryItem> getResults() {
        return result;
    }

}
