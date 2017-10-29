package com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardElements;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ScoreCardItem {
    @SerializedName("info")
    private Info info;
    @SerializedName("scorecard")
    private Scorecard scorecard;
    @SerializedName("result")
    public List<ScoreCardItem> results = new ArrayList<>();

    public Info getInfo() {
        return info;
    }

    public Scorecard getScorecard() {
        return scorecard;
    }

    public List<ScoreCardItem> getResults() {
        return results;
    }

    public class Info{

        @SerializedName("day")
        private String day;
        @SerializedName("match")
        private String match;
        @SerializedName("mom")
        private String mom;
        @SerializedName("ref")
        private String ref;
        @SerializedName("series")
        private String series;
        @SerializedName("status")
        private String status;
        @SerializedName("time")
        private String time;
        @SerializedName("toss")
        private String toss;
        @SerializedName("umpires")
        private String umpires;
        @SerializedName("weather")
        private String weather;

        public String getDay() {
            return day;
        }

        public String getMatch() {
            return match;
        }

        public String getMom() {
            return mom;
        }

        public String getRef() {
            return ref;
        }

        public String getSeries() {
            return series;
        }

        public String getStatus() {
            return status;
        }

        public String getTime() {
            return time;
        }

        public String getToss() {
            return toss;
        }

        public String getUmpires() {
            return umpires;
        }

        public String getWeather() {
            return weather;
        }

    }

    public class Scorecard
    {
        @SerializedName("team1")
        private Team team1;
        @SerializedName("team2")
        private Team team2;

        public Team getTeam1() {
            return team1;
        }

        public Team getTeam2() {
            return team2;
        }
    }

    public class Team
    {
        @SerializedName("team")
        private String team;

        public String getTeam() {
            return team;
        }
    }
}
