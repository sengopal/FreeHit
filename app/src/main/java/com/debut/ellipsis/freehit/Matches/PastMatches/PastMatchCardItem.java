package com.debut.ellipsis.freehit.Matches.PastMatches;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PastMatchCardItem {
    @SerializedName("title")
    private String title;
    @SerializedName("stadium")
    private String stadium;
    @SerializedName("tour")
    private String tour;
    @SerializedName("time")
    private String time;
    @SerializedName("desc")
    private String desc;
    @SerializedName("date")
    private Date date;
    @SerializedName("team1info")
    private TeamInfo team1;
    @SerializedName("team2info")
    private TeamInfo team2;
    @SerializedName("tag")
    private String tag;
    @SerializedName("id")
    private int id;
    @SerializedName("ndid")
    private String ndid;
    @SerializedName("mresult")
    private String mresult;
    @SerializedName("result")
    public List<PastMatchCardItem> results = new ArrayList<>();


    public String getStadium() {
        return stadium;
    }

    public String getTitle() {
        return title;
    }

    public String getTour() {
        return tour;
    }

    public String getResult() {
        return mresult;
    }

    public String getDate() {
        return date.toString();
    }

    public TeamInfo getTeam1Info() {
        return team1;
    }

    public TeamInfo getTeam2Info() {
        return team2;
    }

    public int getId() {
        return id;
    }

    public String getNdid() {
        return ndid;
    }

    public String getTime() {
        return time;
    }

    public List<PastMatchCardItem> getResults() {
        return results;
    }


    public class Date {
        @SerializedName("day")
        private String day;
        @SerializedName("month")
        private String month;
        @SerializedName("year")
        private String year;

        Date(String day, String month, String year) {
            this.day = day;
            this.month = month;
            this.year = year;
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


    }

    public class TeamInfo {
        @SerializedName("sn")
        private String sn;
        @SerializedName("image")
        private String image;
        @SerializedName("inn1")
        private String inn1;
        @SerializedName("inn2")
        private String inn2;

        TeamInfo(String sn, String image, String inn1, String inn2) {
            this.sn = sn;
            this.image = image;
            this.inn1 = inn1;
            this.inn2 = inn2;
        }

        public String getSn() {
            return sn;
        }

        public String getImage() {
            return image;
        }

        public String getInn1() {
            return inn1;
        }

        public String getInn2() {
            return inn2;
        }


    }

}
