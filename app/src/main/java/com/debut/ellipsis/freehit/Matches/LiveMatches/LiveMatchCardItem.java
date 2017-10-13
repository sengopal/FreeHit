package com.debut.ellipsis.freehit.Matches.LiveMatches;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LiveMatchCardItem {
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
    @SerializedName("mresult")
    private String mresult;
    @SerializedName("result")
    public List<LiveMatchCardItem> results = new ArrayList<>();


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

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
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

    public String getMresult() {
        return mresult;
    }

    public List<LiveMatchCardItem> getResults() {
        return results;
    }

    public class Date {

        @SerializedName("day")
        private String day;
        @SerializedName("month")
        private String month;
        @SerializedName("year")
        private String year;
        @SerializedName("final")
        private String finaldate;


        public String getDay() {
            return day;
        }

        public String getMonth() {
            return month;
        }

        public String getYear() {
            return year;
        }

        public String getFinaldate() {
            return finaldate;
        }
    }

    public class Team {
        @SerializedName("name")
        private String name;
        @SerializedName("image")
        private String image;
        @SerializedName("sn")
        private String sn;
        @SerializedName("inn1")
        private String inn1;
        @SerializedName("inn2")
        private String inn2;


        public String getImage() {
            return image;
        }

        public String getName() {
            return name;
        }

        public String getSn() {
            return sn;
        }

        public String getInn1() {
            return inn1;
        }

        public String getInn2() {
            return inn2;
        }
    }
}
