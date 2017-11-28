package com.debut.ellipsis.freehit.Home;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RecentItem {

    @SerializedName("id")
    private int id;
    @SerializedName("ndid")
    private String ndid;
    @SerializedName("tour")
    private String tour;
    @SerializedName("title")
    private String title;
    @SerializedName("match")
    private String match;
    @SerializedName("stadium")
    private String stadium;
    @SerializedName("time")
    private String time;
    @SerializedName("date")
    private Date date;
    @SerializedName("team1info")
    private Team team1;
    @SerializedName("team2info")
    private Team team2;
    @SerializedName("type")
    private String type;
    @SerializedName("mresult")
    private String mresult;
    @SerializedName("result")
    public List<RecentItem> results = new ArrayList<>();


    public int getId() {
        return id;
    }

    public String getMatch() {
        return match;
    }

    public Date getDate() {
        return date;
    }

    public String getNdid() {
        return ndid;
    }

    public String getStadium() {
        return stadium;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getTour() {
        return tour;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public String getType() {
        return type;
    }

    public String getMresult() {
        return mresult;
    }

    public List<RecentItem> getResults() {
        return results;
    }

    public class Date {

        @SerializedName("final")
        private String finaldate;

        public String getFinaldate() {
            return finaldate;
        }
    }

    public class Team {
        @SerializedName("name")
        private String name;
        @SerializedName("inn1")
        private String inn1;
        @SerializedName("inn2")
        private String inn2;
        @SerializedName("sn")
        private String sn;


        public String getName() {
            return name;
        }

        public String getInn1() {
            return inn1;
        }

        public String getInn2() {
            return inn2;
        }


        public String getSn() {
            return sn;
        }
    }


}
