package com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardElements;


public class ScoreCardItemBowling {

    private String mBowlerName;

    private String mWickets;

    private String mBowlerRuns;

    private String mEconomyRate;

    private String mOvers;

    private String mMaidens;

    private String mNoBalls;

    private String mWides;

    private String mDots;

    public ScoreCardItemBowling(String BowlerName, String Wickets, String BowlerRuns, String EconomyRate, String Overs, String Maidens, String NoBalls, String Wides, String Dots) {
        mBowlerName = BowlerName;
        mWickets = Wickets;
        mBowlerRuns = BowlerRuns;
        mEconomyRate = EconomyRate;
        mOvers = Overs;
        mMaidens = Maidens;
        mNoBalls = NoBalls;
        mWides = Wides;
        mDots = Dots;
    }

    public String getmBowlerName() {
        return mBowlerName;
    }

    public String getmWickets() {
        return mWickets;
    }

    public String getmBowlerRuns() {
        return mBowlerRuns;
    }

    public String getmOvers() {
        return mOvers;
    }

    public String getmMaidens() {
        return mMaidens;
    }

    public String getmNoBalls() {
        return mNoBalls;
    }

    public String getmDots() {
        return mDots;
    }

    public String getmEconomyRate() {
        return mEconomyRate;
    }

    public String getmWides() {
        return mWides;
    }

}
