package com.debut.ellipsis.freehit.Stats.Player;


import com.google.gson.annotations.SerializedName;

public class Batting_Items {

    @SerializedName("batstats")
    private Bat batstats;

    public Bat getBatstats() {
        return batstats;
    }

    public class Bat
    {
        @SerializedName("test")
        private BatStats test;
        @SerializedName("odi")
        private BatStats odi;
        @SerializedName("T20")
        private BatStats T20;
        @SerializedName("ipl")
        private BatStats ipl;

        public BatStats getTest() {
            return test;
        }
//currentItem.getBatsStats().getTest().getmatches();
//currentItem.getBatsStats().getOdi().getmatches();

        public BatStats getOdi() {
            return odi;
        }

        public class BatStats
        {
            @SerializedName("matches")
            private String matches;

            public BatStats(String matches)
            {
                this.matches=matches;

            }

            public String getMatches() {
                return matches;
            }
        }
    }
}
