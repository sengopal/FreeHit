package com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardElements;


public class ScoreCardItem {

    private String mPlayerName;

    private String mPlayerOutType;

    private String mNumberOfFours;

    private String mNumberOfDots;

    private String mNumberOfSixes;

    private String mRuns;

    private String mBalls;

    private String mStrikeRate;

    public ScoreCardItem(String PlayerName, String PlayerOutType, String NumberOfFOurs, String NumberOfSixes, String NumberOfDots, String Runs, String Balls, String StrikeRate) {
        mPlayerName = PlayerName;
        mPlayerOutType = PlayerOutType;
        mNumberOfFours = NumberOfFOurs;
        mNumberOfSixes = NumberOfSixes;
        mNumberOfDots = NumberOfDots;
        mRuns = Runs;
        mBalls = Balls;
        mStrikeRate = StrikeRate;
    }

    public String getmPlayerName() {
        return mPlayerName;
    }

    public String getmPlayerOutType() {
        return mPlayerOutType;
    }

    public String getmNumberOfFours() {
        return mNumberOfFours;
    }

    public String getmNumberOfSixes() {
        return mNumberOfSixes;
    }

    public String getmNumberOfDots() {
        return mNumberOfDots;
    }

    public String getmBalls() {
        return mBalls;
    }

    public String getmRuns() {
        return mRuns;
    }

    public String getmStrikeRate() {
        return mStrikeRate;
    }
}
