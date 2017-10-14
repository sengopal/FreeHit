package com.debut.ellipsis.freehit.More.Series;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SeriesApiItem {
    @SerializedName("id")
    private String id;
    @SerializedName("title") 
    private String title;
    @SerializedName("date")
    private String date;
    @SerializedName("team1")
    private String team1;
    @SerializedName("team2")
    private String team2;
    @SerializedName("matches")
    private String matches;
    @SerializedName("result")
    private List<SeriesApiItem> result;
    public SeriesApiItem(String id,String title,String date,String team1,String team2,String matches) {
        this.id=id;
        this.title=title;
        this.date=date;
        this.team1=team1;
        this.team2=team2;
        this.matches=matches;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getMatches() {
        return matches;
    }
    public List<SeriesApiItem> getResults(){

        return result;
    }
}
