package com.debut.ellipsis.freehit.More.Rankings;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RankingItem {

    @SerializedName("odi")
    private ODI odi;
    @SerializedName("t20")
    private T20 t20;
    @SerializedName("test")
    private Test test;
    @SerializedName("result")
    public List<RankingItem> results = new ArrayList<>();

    public ODI getOdi() {
        return odi;
    }

    public T20 getT20() {
        return t20;
    }

    public Test getTest() {
        return test;
    }

    public List<RankingItem> getResults() {
        return results;
    }

    public class ODI
    {
        @SerializedName("allround")
        public List<AllRounder> allRounders = new ArrayList<>();
        @SerializedName("batting")
        public List<AllRounder> battingList = new ArrayList<>();
        @SerializedName("bowling")
        public List<AllRounder> bowlingList = new ArrayList<>();
        @SerializedName("team")
        public List<Team> teamList = new ArrayList<>();

        public List<AllRounder> getAllRounders() {
            return allRounders;
        }

        public List<AllRounder> getBattingList() {
            return battingList;
        }

        public List<AllRounder> getBowlingList() {
            return bowlingList;
        }

        public List<Team> getTeamList() {
            return teamList;
        }
    }

    public class AllRounder
    {
        @SerializedName("best")
        private String best;
        @SerializedName("country")
        private String country;
        @SerializedName("link")
        private String link;
        @SerializedName("name")
        private String name;
        @SerializedName("pos")
        private String pos;
        @SerializedName("rating")
        private String rating;

        public String getBest() {
            return best;
        }

        public String getCountry() {
            return country;
        }

        public String getLink() {
            return link;
        }

        public String getName() {
            return name;
        }

        public String getPos() {
            return pos;
        }

        public String getRating() {
            return rating;
        }
    }

    public class Team
    {
        @SerializedName("pos")
        private String pos;
        @SerializedName("rating")
        private String rating;
        @SerializedName("team")
        private String team;

        public String getPos() {
            return pos;
        }

        public String getRating() {
            return rating;
        }

        public String getTeam() {
            return team;
        }
    }


    public class T20
    {
        @SerializedName("allround")
        public List<AllRounder> allRounders = new ArrayList<>();
        @SerializedName("batting")
        public List<AllRounder> battingList = new ArrayList<>();
        @SerializedName("bowling")
        public List<AllRounder> bowlingList = new ArrayList<>();
        @SerializedName("team")
        public List<Team> teamList = new ArrayList<>();

        public List<AllRounder> getAllRounders() {
            return allRounders;
        }

        public List<AllRounder> getBattingList() {
            return battingList;
        }

        public List<AllRounder> getBowlingList() {
            return bowlingList;
        }

        public List<Team> getTeamList() {
            return teamList;
        }
    }

    public class Test
    {
        @SerializedName("allround")
        public List<AllRounder> allRounders = new ArrayList<>();
        @SerializedName("batting")
        public List<AllRounder> battingList = new ArrayList<>();
        @SerializedName("bowling")
        public List<AllRounder> bowlingList = new ArrayList<>();
        @SerializedName("team")
        public List<Team> teamList = new ArrayList<>();

        public List<AllRounder> getAllRounders() {
            return allRounders;
        }

        public List<AllRounder> getBattingList() {
            return battingList;
        }

        public List<AllRounder> getBowlingList() {
            return bowlingList;
        }

        public List<Team> getTeamList() {
            return teamList;
        }
    }
}
