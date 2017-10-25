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
    @SerializedName("team1info")
    private TeamInfo team1;
    @SerializedName("team2info")
    private TeamInfo team2;
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



    public class TeamInfo {
        @SerializedName("sn")
        private String sn;
        @SerializedName("inn1")
        private String inn1;
        @SerializedName("inn2")
        private String inn2;


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
