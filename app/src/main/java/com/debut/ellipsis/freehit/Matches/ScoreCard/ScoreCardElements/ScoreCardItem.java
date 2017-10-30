package com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardElements;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ScoreCardItem {
    @SerializedName("info")
    private Info info;
    @SerializedName("scorecard")
    private Scorecard scorecard;
    @SerializedName("h2h")
    private H2H h2h;
    @SerializedName("result")
    public List<ScoreCardItem> results = new ArrayList<>();

    public Info getInfo() {
        return info;
    }

    public Scorecard getScorecard() {
        return scorecard;
    }

    public H2H getH2h() {
        return h2h;
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
        @SerializedName("manofseries")
        private String manofseries;
        @SerializedName("ref")
        private String ref;
        @SerializedName("result")
        private String result;
        @SerializedName("series")
        private String series;
        @SerializedName("stadium")
        private String stadium;
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

        public String getManofseries() {
            return manofseries;
        }

        public String getRef() {
            return ref;
        }

        public String getResult() {
            return result;
        }

        public String getSeries() {
            return series;
        }

        public String getStadium() {
            return stadium;
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

    public class H2H
    {
        @SerializedName("status")
        private Status status;
        @SerializedName("team1")
        private TeamH2H team1;
        @SerializedName("team2")
        private TeamH2H team2;

        public Status getStatus() {
            return status;
        }

        public TeamH2H getTeam1() {
            return team1;
        }

        public TeamH2H getTeam2() {
            return team2;
        }
    }

    public class Status
    {
        @SerializedName("drawn")
        private String drawn;
        @SerializedName("matches")
        private String matches;
        @SerializedName("tied")
        private String tied;

        public String getMatches() {
            return matches;
        }

        public String getDrawn() {
            return drawn;
        }

        public String getTied() {
            return tied;
        }
    }

    public class TeamH2H
    {
        @SerializedName("away")
        private String away;
        @SerializedName("chased")
        private String chased;
        @SerializedName("defended")
        private String defended;
        @SerializedName("highest")
        private String highest;
        @SerializedName("home")
        private String home;
        @SerializedName("lowest")
        private String lowest;
        @SerializedName("matches")
        private String matches;
        @SerializedName("team")
        private String team;

        public String getAway() {
            return away;
        }

        public String getChased() {
            return chased;
        }

        public String getDefended() {
            return defended;
        }

        public String getHighest() {
            return highest;
        }

        public String getHome() {
            return home;
        }

        public String getLowest() {
            return lowest;
        }

        public String getMatches() {
            return matches;
        }

        public String getTeam() {
            return team;
        }
    }

}
