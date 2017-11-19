package com.debut.ellipsis.freehit.Matches.ScoreCard;

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
        /*@SerializedName("top")
        private Top top;*/

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

        /*public Top getTop() {
            return top;
        }*/
    }

    /*public class Top
    {
        @SerializedName("bat1")
        private String bat1;
        @SerializedName("bat2")
        private String bat2;
        @SerializedName("bowl1")
        private String bowl1;
        @SerializedName("bowl2")
        private String bowl2;
    }*/

    public class Scorecard
    {
        @SerializedName("team1")
        private Team team1;
        @SerializedName("team2")
        private Team team2;
        @SerializedName("currentover")
        private CurrentOver currentOver;

        public Team getTeam1() {
            return team1;
        }

        public Team getTeam2() {
            return team2;
        }

        public CurrentOver getCurrentOver() {
            return currentOver;
        }
    }

    public class CurrentOver
    {
        @SerializedName("bat1")
        private CurrentBat bat1;
        @SerializedName("bat2")
        private CurrentBat bat2;
        @SerializedName("bowl")
        private CurrentBowl bowler;
        @SerializedName("currover")
        private List<String> overs;

        public CurrentBat getBat1() {
            return bat1;
        }

        public CurrentBat getBat2() {
            return bat2;
        }

        public CurrentBowl getBowler() {
            return bowler;
        }

        public List<String> getOvers() {
            return overs;
        }
    }

    public class CurrentBat
    {
        @SerializedName("name")
        private String name;
        @SerializedName("overs")
        private String overs;
        @SerializedName("score")
        private String score;
        @SerializedName("status")
        private String status;

        public String getName() {
            return name;
        }

        public String getScore() {
            return score;
        }

        public String getOvers() {
            return overs;
        }

        public String getStatus() {
            return status;
        }
    }

    public class CurrentBowl
    {
        @SerializedName("name")
        private String name;
        @SerializedName("overs")
        private String overs;
        @SerializedName("score")
        private String score;

        public String getName() {
            return name;
        }

        public String getScore() {
            return score;
        }

        public String getOvers() {
            return overs;
        }
    }

    public class Team
    {
        @SerializedName("firstinn")
        private Innings firstinn;
        @SerializedName("inncount")
        private int inncount;
        @SerializedName("teamname")
        private String teamname;
        @SerializedName("secondinn")
        private Innings secondinn;
        @SerializedName("lineup")
        public List<LineUp> lineup = new ArrayList<>();

        public Innings getFirstinn() {
            return firstinn;
        }

        public int getInncount() {
            return inncount;
        }

        public String getTeamname() {
            return teamname;
        }

        public Innings getSecondinn() {
            return secondinn;
        }

        public List<LineUp> getLineup() {
            return lineup;
        }
    }

    public class LineUp
    {
        @SerializedName("name")
        private String name;

        public String getName() {
            return name;
        }
    }


    public class Innings
    {
        @SerializedName("batting")
        public List<Batting> batting = new ArrayList<>();
        @SerializedName("bowling")
        public List<Bowling> bowling = new ArrayList<>();
        @SerializedName("extras")
        private String extras;
        @SerializedName("fow")
        public List<FOW> fow = new ArrayList<>();
        @SerializedName("score")
        private String score;
        @SerializedName("team")
        private String team;

        public List<Batting> getBatting() {
            return batting;
        }

        public List<Bowling> getBowling() {
            return bowling;
        }

        public String getExtras() {
            return extras;
        }

        public List<FOW> getFow() {
            return fow;
        }

        public String getScore() {
            return score;
        }

        public String getTeam() {
            return team;
        }
    }

    public class Batting
    {
        @SerializedName("balls")
        private String balls;
        @SerializedName("name")
        private String name;
        @SerializedName("runs")
        private String runs;
        @SerializedName("dots")
        private String dots;
        @SerializedName("fours")
        private String fours;
        @SerializedName("sixes")
        private String sixes;
        @SerializedName("sr")
        private String sr;
        @SerializedName("status")
        private String status;
        @SerializedName("avg")
        private String avg;

        public String getBalls() {
            return balls;
        }

        public String getName() {
            return name;
        }

        public String getRuns() {
            return runs;
        }

        public String getDots() {
            return dots;
        }

        public String getFours() {
            return fours;
        }

        public String getSixes() {
            return sixes;
        }

        public String getSr() {
            return sr;
        }

        public String getStatus() {
            return status;
        }

        public String getAvg() {
            return avg;
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

    public class Bowling
    {
        @SerializedName("er")
        private String er;
        @SerializedName("name")
        private String name;
        @SerializedName("runs")
        private String runs;
        @SerializedName("dots")
        private String dots;
        @SerializedName("maidens")
        private  String maidens;
        @SerializedName("nb")
        private String nb;
        @SerializedName("overs")
        private String overs;
        @SerializedName("wide")
        private String wide;
        @SerializedName("wickets")
        private String wickets;

        public String getEr() {
            return er;
        }

        public String getName() {
            return name;
        }

        public String getRuns() {
            return runs;
        }

        public String getDots() {
            return dots;
        }

        public String getMaidens() {
            return maidens;
        }

        public String getNb() {
            return nb;
        }

        public String getOvers() {
            return overs;
        }

        public String getWide() {
            return wide;
        }

        public String getWickets() {
            return wickets;
        }

    }


    public class FOW
    {
        @SerializedName("name")
        private String name;
        @SerializedName("overs")
        private String overs;
        @SerializedName("score")
        private String score;

        public String getName() {
            return name;
        }

        public String getOvers() {
            return overs;
        }

        public String getScore() {
            return score;
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
