package com.debut.ellipsis.freehit.Stats.Player;


import com.google.gson.annotations.SerializedName;

public class BowlingItem {
    @SerializedName("bowlstats")
    private Bowl bowlstats;
    public Bowl getBowlstats() {
        return bowlstats;
    }
    
    public class Bowl{

        @SerializedName("test")
        private BowlStats test;
        @SerializedName("odi")
        private BowlStats odi;
        @SerializedName("t20")
        private BowlStats t20;
        @SerializedName("ipl")
        private BowlStats ipl;

        public BowlStats getTest() {
            return test;
        }
//currentItem.getBatsStats().getTest().getinnbowled();
//currentItem.getBatsStats().getOdi().getinnbowled();

        public BowlStats getOdi() {
            return odi;
        }
        public BowlStats getT20(){return t20;}
        public BowlStats getIpl() {return ipl;}

        public class BowlStats{
            @SerializedName("innbowled")
            private String innbowled;
            @SerializedName("oversbowled")
            private String oversbowled;
            @SerializedName("maidens")
            private String maidens;
            @SerializedName("runsgiven")
            private String runsgiven;
            @SerializedName("wicktaken")
            private String wicktaken;
            @SerializedName("bestinn")
            private String bestinn;

            @SerializedName("threewick")
            private String threewick;
            @SerializedName("fivewick")
            private String fivewick;
            @SerializedName("bowlingavg")
            private String bowlingavg;
            @SerializedName("economy")
            private String economy;
            @SerializedName("strrate")
            private String strrate;

            public BowlStats(String innbowled,String oversbowled,String maidens,String runsgiven,String wicktaken,String bestinn,String threewick,String fivewick,String bowlingavg,String economy,String strrate)
            {
                this.innbowled=innbowled;
                this.oversbowled=oversbowled;
                this.maidens=maidens;
                this.runsgiven=runsgiven;
                this.wicktaken=wicktaken;
                this.bestinn=bestinn;
                this.threewick=threewick;
                this.bowlingavg=bowlingavg;
                this.fivewick=fivewick;
                this.economy=economy;
                this.strrate=strrate;
                


            }

            public String getinnbowled() {
                return innbowled;
            }
            public String getoversbowled(){return oversbowled;}
            public String getmaidens(){return maidens;}
            public String getrunsgiven(){return runsgiven;}

            public String getwicktaken() {
                return wicktaken;
            }
            public String getbestinn(){return bestinn;}

            public String getthreewick() {
                return threewick;
            }

            public String getfivewick() {
                return fivewick;
            }

            public String getbowlingavg() {
                return bowlingavg;
            }

            public String geteconomy() {
                return economy;
            }

            public String getstrrate() {
                return strrate;
            }

           
        }
    }
    
}
