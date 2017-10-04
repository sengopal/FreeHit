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

    public UpcomingMatchCardItem(int id, String ndid, String tour, String title, String match, String stadium, String time) {
        this.id = id;
        this.ndid = ndid;
        this.tour = tour;
        this.title = title;
        this.match = match;
        this.stadium = stadium;
        this.time = time;
    }

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

    public List<UpcomingMatchCardItem> getResults() {
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

        public Date(String day, String month, String year, String finaldate) {
            this.day = day;
            this.month = month;
            this.year = year;
            this.finaldate = finaldate;

        }

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

        public Team(String name, String image, String sn) {
            this.name = name;
            this.image = image;
            this.sn = sn;

        }

        public String getImage() {
            return image;
        }

        public String getName() {
            return name;
        }

        public String getSn() {
            return sn;
        }
    }
}