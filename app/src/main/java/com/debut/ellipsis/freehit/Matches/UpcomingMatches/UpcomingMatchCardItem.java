package com.debut.ellipsis.freehit.Matches.UpcomingMatches;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UpcomingMatchCardItem {
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
    @SerializedName("result")
    public List<UpcomingMatchCardItem> results = new ArrayList<>();


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

    public List<UpcomingMatchCardItem> getResults() {
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
        @SerializedName("sn")
        private String sn;


        public String getName() {
            return name;
        }

        public String getSn() {
            return sn;
        }
    }
}