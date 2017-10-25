package com.debut.ellipsis.freehit.More.Team;


public class TeamListItem {

    private String mTeamIconURL;

    private int mTeamName;


    public TeamListItem(String TeamIconURL, int TeamName) {
        mTeamIconURL = TeamIconURL;
        mTeamName = TeamName;

    }

    public String getmTeamIconURL() {
        return mTeamIconURL;
    }

    public int getmTeamName() {
        return mTeamName;
    }

}