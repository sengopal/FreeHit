package com.debut.ellipsis.freehit.More.Player;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayerItem {

    @SerializedName("name")
    private String name;
    @SerializedName("nationality")
    private String nationality;
    @SerializedName("dob")
    private String dob;
    @SerializedName("age")
    private String age;
    @SerializedName("batstyle")
    private String batstyle;
    @SerializedName("bowlstyle")
    private String bowlstyle;
    @SerializedName("teamsplayed")
    private String teamsplayed;
    @SerializedName("img")
    private String img;
    @SerializedName("manofthematch")
    private List<String> manofthematch;
    @SerializedName("batrank")
    private List<String> batrank;
    @SerializedName("bowlrank")
    private List<String> bowlrank;
    @SerializedName("batstats")
    private Bat batstats;
    @SerializedName("bowlstats")
    private Bowl bowlstats;
    @SerializedName("desc")
    private String desc;

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getDob() {
        return dob;
    }

    public String getAge() {
        return age;
    }

    public String getBatstyle() {
        return batstyle;
    }

    public String getBowlstyle() {
        return bowlstyle;
    }

    public String getTeamsplayed() {
        return teamsplayed;
    }

    public String getImg() {
        return img;
    }

    public List<String> getManofthematch() {
        return manofthematch;
    }

    public List<String> getBatrank() {
        return batrank;
    }

    public List<String> getBowlrank() {
        return bowlrank;
    }

    public Bat getBatstats() {
        return batstats;
    }

    public Bowl getBowlstats() {
        return bowlstats;
    }

    public String getDesc() {
        return desc;
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

        public BatStats getTest() {
            return test;
        }
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

        }
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
