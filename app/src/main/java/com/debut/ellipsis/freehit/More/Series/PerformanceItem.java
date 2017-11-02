package com.debut.ellipsis.freehit.More.Series;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PerformanceItem {
    @SerializedName("batting")
    private List<Batting> batting = new ArrayList<>();
    @SerializedName("bowling")
    private List<Bowling> bowling = new ArrayList<>();
    @SerializedName("result")
    public List<PerformanceItem> results = new ArrayList<>();

    public List<Batting> getBatting() {
        return batting;
    }

    public List<Bowling> getBowling() {
        return bowling;
    }

    public List<PerformanceItem> getResults() {
        return results;
    }

    public class Batting {
        @SerializedName("avg")
        private String avg;
        @SerializedName("name")
        private String name;
        @SerializedName("runs")
        private String runs;
        @SerializedName("position")
        private String position;
        @SerializedName("team")
        private String team;

        public String getTeam() {
            return team;
        }

        public String getRuns() {
            return runs;
        }

        public String getName() {
            return name;
        }

        public String getPosition() {
            return position;
        }

        public String getAvg() {
            return avg;
        }
    }

    public class Bowling {

        @SerializedName("name")
        private String name;
        @SerializedName("balls")
        private String balls;
        @SerializedName("wkts")
        private String wkts;
        @SerializedName("position")
        private String position;
        @SerializedName("team")
        private String team;

        public String getTeam() {
            return team;
        }

        public String getwkts() {
            return wkts;
        }

        public String getName() {
            return name;
        }

        public String getBalls() {
            return balls;
        }

        public String getPosition() {
            return position;
        }


    }

}

