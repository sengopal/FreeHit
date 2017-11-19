package com.debut.ellipsis.freehit.Matches.ScoreCard;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.debut.ellipsis.freehit.Matches.LiveMatches.LiveMatchScoreCard;
import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchScoreCard;
import com.debut.ellipsis.freehit.R;

import java.util.List;

public class PlayingX1 extends AppCompatActivity {
    ScoreCardPlayingX1Adapter mAdapter;
    LiveScoreCardPlayingX1Adapter mLiveAdapter;
    private List<ScoreCardItem> teamList = null;
    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppThemeDark);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_matchscorecard_info_playingx1);

        String team = getIntent().getStringExtra("team");

        String match_type = getIntent().getStringExtra("match_type");

        View viewToolbar = findViewById(R.id.toolbar_players_list);

        toolbar = viewToolbar.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (match_type.equals("PAST")) {
            teamList = PastMatchScoreCard.getQList();
        } else if (match_type.equals("LIVE")) {
            teamList = LiveMatchScoreCard.getQList();
        }

        View playingX1_list = findViewById(R.id.playingX1_list);

        ListView listTeam = playingX1_list.findViewById(R.id.list);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            listTeam.setBackgroundColor(getResources().getColor(R.color.night_background));
        }

        if (match_type.equals("PAST")) {
            if (team.equals("TEAM_1")) {
                setTitle(teamList.get(0).getScorecard().getTeam1().getTeamname());
                mAdapter = new ScoreCardPlayingX1Adapter(getBaseContext(), teamList.get(0).getScorecard().getTeam1().getFirstinn().getBatting());
                listTeam.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            } else if (team.equals("TEAM_2")) {
                setTitle(teamList.get(0).getScorecard().getTeam2().getTeamname());
                mAdapter = new ScoreCardPlayingX1Adapter(getBaseContext(), teamList.get(0).getScorecard().getTeam2().getFirstinn().getBatting());
                listTeam.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        }
        else if(match_type.equals("LIVE")) {
            if (team.equals("TEAM_1")) {
                setTitle(teamList.get(0).getScorecard().getTeam1().getTeamname());
                mLiveAdapter = new LiveScoreCardPlayingX1Adapter(getBaseContext(), teamList.get(0).getScorecard().getTeam1().getLineup());
                listTeam.setAdapter(mLiveAdapter);
                mLiveAdapter.notifyDataSetChanged();
            } else if (team.equals("TEAM_2")) {
                setTitle(teamList.get(0).getScorecard().getTeam2().getTeamname());
                mLiveAdapter = new LiveScoreCardPlayingX1Adapter(getBaseContext(), teamList.get(0).getScorecard().getTeam2().getLineup());
                listTeam.setAdapter(mLiveAdapter);
                mLiveAdapter.notifyDataSetChanged();
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(0, R.anim.exit_to_right);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        PlayingX1.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);
    }
}
