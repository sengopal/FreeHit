package com.debut.ellipsis.freehit.Matches.ScoreCard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.debut.ellipsis.freehit.Matches.LiveMatches.LiveMatchScoreCard;
import com.debut.ellipsis.freehit.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {


    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES ? inflater.inflate(R.layout.fragment_matchscorecard_summary_dark, container, false) : inflater.inflate(R.layout.fragment_matchscorecard_summary, container, false);

        TextView currentTeamName = rootView.findViewById(R.id.currentTeamName);
        TextView currentTeamScore = rootView.findViewById(R.id.currentTeamScore);
        TextView currentTeamRR = rootView.findViewById(R.id.currentTeamRR);
        TextView currentTeamOvers = rootView.findViewById(R.id.currentTeamOvers);
        TextView currentTeamStatus = rootView.findViewById(R.id.currentTeamStatus);
        TextView currentTeamcurrover = rootView.findViewById(R.id.currentTeamcurrover);
        TextView currentStatus = rootView.findViewById(R.id.currentStatus);

        View currentPlayers = rootView.findViewById(R.id.currentPlayers);
        TextView Batsman1 = currentPlayers.findViewById(R.id.Batsman1);
        TextView Batsman1Score = currentPlayers.findViewById(R.id.Batsman1Score);
        TextView Batsman1Status = currentPlayers.findViewById(R.id.Batsman1Status);
        TextView Batsman2 = currentPlayers.findViewById(R.id.Batsman2);
        TextView Batsman2Score = currentPlayers.findViewById(R.id.Batsman2Score);
        TextView Batsman2Status = currentPlayers.findViewById(R.id.Batsman2Status);
        TextView BowlerName = currentPlayers.findViewById(R.id.Bowler);
        TextView BowlerScore = currentPlayers.findViewById(R.id.BowlerScore);

        View topPlayers = rootView.findViewById(R.id.topPlayers);
        TextView Batsman1Top = topPlayers.findViewById(R.id.Batsman1);
        TextView Batsman1ScoreTop = topPlayers.findViewById(R.id.Batsman1Score);
        TextView Batsman2Top = topPlayers.findViewById(R.id.Batsman2);
        TextView Batsman2ScoreTop = topPlayers.findViewById(R.id.Batsman2Score);
        TextView Bowler1Top = topPlayers.findViewById(R.id.Bowler1);
        TextView Bowler1ScoreTop = topPlayers.findViewById(R.id.BowlerScore);
        TextView Bowler2Top = topPlayers.findViewById(R.id.Bowler2);
        TextView Bowler2ScoreTop = topPlayers.findViewById(R.id.Bowler2Score);


        List<ScoreCardItem> teamList = LiveMatchScoreCard.getQList();

        if (teamList.get(1).getInfo().getSummary().getSummmatchstat().equals("Stumps")) {
            currentPlayers.setVisibility(View.INVISIBLE);
            topPlayers.setVisibility(View.VISIBLE);
        } else {
            currentPlayers.setVisibility(View.VISIBLE);
            topPlayers.setVisibility(View.INVISIBLE);

        }

        currentTeamName.setText(teamList.get(1).getInfo().getSummary().getSummteam());
        currentTeamScore.setText(teamList.get(1).getInfo().getSummary().getSummscore());
        currentTeamOvers.setText(teamList.get(1).getInfo().getSummary().getSummover());
        currentStatus.setText(teamList.get(1).getInfo().getSummary().getSummmatchstat());
        currentTeamRR.setText(teamList.get(1).getInfo().getSummary().getSummrr());
        currentTeamStatus.setText(teamList.get(1).getInfo().getSummary().getSummstatus());
        String currover = "";
        for (int i = 0; i < teamList.get(0).getScorecard().getCurrentOver().getOvers().size(); i++) {
            currover = currover + "|" + teamList.get(0).getScorecard().getCurrentOver().getOvers().get(i);
        }
        currover = currover + "|";

        currentTeamcurrover.setText(currover);


        Batsman1.setText(teamList.get(0).getScorecard().getCurrentOver().getBat1().getName());
        Batsman1Score.setText(teamList.get(0).getScorecard().getCurrentOver().getBat1().getScore() + teamList.get(0).getScorecard().getCurrentOver().getBat1().getOvers());
        Batsman1Status.setText(teamList.get(0).getScorecard().getCurrentOver().getBat1().getStatus());
        Batsman2.setText(teamList.get(0).getScorecard().getCurrentOver().getBat2().getName());
        Batsman2Score.setText(teamList.get(0).getScorecard().getCurrentOver().getBat2().getScore() + teamList.get(0).getScorecard().getCurrentOver().getBat2().getOvers());
        Batsman2Status.setText(teamList.get(0).getScorecard().getCurrentOver().getBat2().getStatus());
        BowlerName.setText(teamList.get(0).getScorecard().getCurrentOver().getBowler().getName());
        BowlerScore.setText(teamList.get(0).getScorecard().getCurrentOver().getBowler().getScore() + teamList.get(0).getScorecard().getCurrentOver().getBowler().getOvers());


        Batsman1Top.setText(teamList.get(1).getInfo().getTop().getBat1().getName());
        Batsman1ScoreTop.setText(teamList.get(1).getInfo().getTop().getBat1().getScore());
        Batsman2Top.setText(teamList.get(1).getInfo().getTop().getBat2().getName());
        Batsman2ScoreTop.setText(teamList.get(1).getInfo().getTop().getBat2().getScore());
        Bowler1Top.setText(teamList.get(1).getInfo().getTop().getBowl1().getName());
        Bowler1ScoreTop.setText(teamList.get(1).getInfo().getTop().getBowl1().getScore());
        Bowler2Top.setText(teamList.get(1).getInfo().getTop().getBowl2().getName());
        Bowler2ScoreTop.setText(teamList.get(1).getInfo().getTop().getBowl2().getScore());

        return rootView;
    }

}
