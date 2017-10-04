package com.debut.ellipsis.freehit.Stats.Player;


import com.google.gson.annotations.SerializedName;

public class Batting_Items {

    @SerializedName("batstats")
    private Bat batstats;

  /*  public Batting_Items(Bat batstats){
        this.batstats=batstats;
    }*/

    public Bat getBatstats() {
        return batstats;
    }

    public class Bat
    {
        @SerializedName("test")
        private BatStats test;
        @SerializedName("odi")
        private BatStats odi;
        @SerializedName("t20")
        private BatStats t20;
        @SerializedName("ipl")
        private BatStats ipl;
       /* public Bat(BatStats test,BatStats odi,BatStats t20,BatStats ipl){
            this.test=test;
            this.odi=odi;
            this.t20=t20;
            this.ipl=ipl;
        }*/

        public BatStats getTest() {
            return test;
        }
//currentItem.getBatsStats().getTest().getmatches();
//currentItem.getBatsStats().getOdi().getmatches();

        public BatStats getOdi() {
            return odi;
        }
        public BatStats getT20(){return t20;}
        public BatStats getIpl() {return ipl;}

        public class BatStats
        {
            @SerializedName("matches")
            private String matches;
            @SerializedName("innbat")
            private String innbat;
            @SerializedName("notout")
            private String notout;
            @SerializedName("runs")
            private String runs;
            @SerializedName("highestinn")
            private String highestinn;
            @SerializedName("hundreds")
            private String hundreds;

            @SerializedName("fifties")
            private String fifties;
            @SerializedName("fours")
            private String fours;
            @SerializedName("sixes")
            private String sixes;
            @SerializedName("batavg")
            private String batavg;
            @SerializedName("batstr")
            private String batstr;
            @SerializedName("catches")
            private String catches;
            @SerializedName("stumpings")
            private String stumpings;

            public BatStats(String matches,String innbat,String notout,String runs,String highestinn,String hundreds,String fifties,String fours,String sixes,String batavg,String batstr,String catches,String stumpings)
            {
                this.matches=matches;
                this.innbat=innbat;
                this.notout=notout;
                this.runs=runs;
                this.highestinn=highestinn;
                this.hundreds=hundreds;
                this.fifties=fifties;
                this.sixes=sixes;
                this.fours=fours;
                this.batavg=batavg;
                this.batstr=batstr;
                this.catches=catches;
                this.stumpings=stumpings;


            }

            public String getMatches() {
                return matches;
            }
            public String getInnbat(){return innbat;}
            public String getNotout(){return notout;}
            public String getRuns(){return runs;}

            public String getHighestinn() {
                return highestinn;
            }
            public String getHundreds(){return hundreds;}

            public String getFifties() {
                return fifties;
            }

            public String getFours() {
                return fours;
            }

            public String getSixes() {
                return sixes;
            }

            public String getBatavg() {
                return batavg;
            }

            public String getBatstr() {
                return batstr;
            }

            public String getCatches() {
                return catches;
            }

            public String getStumpings() {
                return stumpings;
            }
        }
    }
}
