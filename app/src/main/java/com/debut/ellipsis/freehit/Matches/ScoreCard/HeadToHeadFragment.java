package com.debut.ellipsis.freehit.Matches.ScoreCard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchScoreCard;
import com.debut.ellipsis.freehit.R;

import java.util.List;


public class HeadToHeadFragment extends Fragment {


    public HeadToHeadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String match_type = getActivity().getIntent().getStringExtra("match_type");

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_matchscorecard_head_to_head, container, false);

        final View matches_won = rootView.findViewById(R.id.matches_won);

        final View home_wins = rootView.findViewById(R.id.home_wins);

        final View away_wins = rootView.findViewById(R.id.away_wins);

        final View highest_score = rootView.findViewById(R.id.highest_score);

        final View lowest_score = rootView.findViewById(R.id.lowest_score);

        final View highest_total_chased = rootView.findViewById(R.id.highest_total_chased);

        final View lowest_data_defended = rootView.findViewById(R.id.lowest_data_defended);

        List<ScoreCardItem> teamList = null;

        if (match_type.equals("PAST")) {
            teamList = ((PastMatchScoreCard) getActivity()).getQList();
        }

        TextView team1_name = (TextView) rootView.findViewById(R.id.team1_name);
        team1_name.setText(teamList.get(2).getH2h().getTeam1().getTeam());

        TextView team2_name = (TextView) rootView.findViewById(R.id.team2_name);
        team2_name.setText(teamList.get(2).getH2h().getTeam2().getTeam());

        TextView no_of_matches = (TextView) rootView.findViewById(R.id.no_of_matches);
        no_of_matches.setText("MATCHES : " + teamList.get(2).getH2h().getStatus().getMatches());

        TextView tied_matches = (TextView) rootView.findViewById(R.id.tied_matches);
        tied_matches.setText("TIED : " + teamList.get(2).getH2h().getStatus().getTied());

        TextView drawn_NR_matches = (TextView) rootView.findViewById(R.id.drawn_NR_matches);
        drawn_NR_matches.setText("DRAWN : " + teamList.get(2).getH2h().getStatus().getDrawn());

        //For Matches Won
        TextView matches_won_label = (TextView) matches_won.findViewById(R.id.compare_type_name);
        matches_won_label.setText("MATCHES WON");

        TextView matches_won_team1 = (TextView) matches_won.findViewById(R.id.team1_value);
        matches_won_team1.setText(teamList.get(2).getH2h().getTeam1().getMatches());

        TextView matches_won_team2 = (TextView) matches_won.findViewById(R.id.team2_value);
        matches_won_team2.setText(teamList.get(2).getH2h().getTeam2().getMatches());

        //For Home Wins
        TextView home_win_label = (TextView) home_wins.findViewById(R.id.compare_type_name);
        home_win_label.setText("HOME WINS");

        TextView home_win_team1 = (TextView) home_wins.findViewById(R.id.team1_value);
        home_win_team1.setText(teamList.get(2).getH2h().getTeam1().getHome());

        TextView home_win_team2 = (TextView) home_wins.findViewById(R.id.team2_value);
        home_win_team2.setText(teamList.get(2).getH2h().getTeam2().getHome());

        //For Away Wins
        TextView away_win_label = (TextView) away_wins.findViewById(R.id.compare_type_name);
        away_win_label.setText("AWAY WINS");

        TextView away_win_team1 = (TextView) away_wins.findViewById(R.id.team1_value);
        away_win_team1.setText(teamList.get(2).getH2h().getTeam1().getAway());

        TextView away_win_team2 = (TextView) away_wins.findViewById(R.id.team2_value);
        away_win_team2.setText(teamList.get(2).getH2h().getTeam2().getAway());

        //For Highest Score
        TextView highest_score_label = (TextView) highest_score.findViewById(R.id.compare_type_name);
        highest_score_label.setText("HIGHEST SCORE");

        TextView highest_score_team1 = (TextView) highest_score.findViewById(R.id.team1_value);
        highest_score_team1.setText(teamList.get(2).getH2h().getTeam1().getHighest());

        TextView highest_score_team2 = (TextView) highest_score.findViewById(R.id.team2_value);
        highest_score_team2.setText(teamList.get(2).getH2h().getTeam2().getHighest());

        //For Lowest Score
        TextView lowest_score_label = (TextView) lowest_score.findViewById(R.id.compare_type_name);
        lowest_score_label.setText("LOWEST SCORE");

        TextView lowest_score_team1 = (TextView) lowest_score.findViewById(R.id.team1_value);
        lowest_score_team1.setText(teamList.get(2).getH2h().getTeam1().getLowest());

        TextView lowest_score_team2 = (TextView) lowest_score.findViewById(R.id.team2_value);
        lowest_score_team2.setText(teamList.get(2).getH2h().getTeam2().getLowest());

        //For Highest Total Chased
        TextView highest_total_chased_label = (TextView) highest_total_chased.findViewById(R.id.compare_type_name);
        highest_total_chased_label.setText("HIGHEST TOTAL CHASED");

        TextView highest_total_chased_team1 = (TextView) highest_total_chased.findViewById(R.id.team1_value);
        highest_total_chased_team1.setText(teamList.get(2).getH2h().getTeam1().getChased());

        TextView highest_total_chased_team2 = (TextView) highest_total_chased.findViewById(R.id.team2_value);
        highest_total_chased_team2.setText(teamList.get(2).getH2h().getTeam2().getChased());

        //For Lowest Total Defended
        TextView lowest_total_defended_label = (TextView) lowest_data_defended.findViewById(R.id.compare_type_name);
        lowest_total_defended_label.setText("LOWEST TOTAL DEFENDED");

        TextView lowest_total_defended_team1 = (TextView) lowest_data_defended.findViewById(R.id.team1_value);
        lowest_total_defended_team1.setText(teamList.get(2).getH2h().getTeam1().getDefended());

        TextView lowest_total_defended_team2 = (TextView) lowest_data_defended.findViewById(R.id.team2_value);
        lowest_total_defended_team2.setText(teamList.get(2).getH2h().getTeam2().getDefended());

        return rootView;
    }

}
