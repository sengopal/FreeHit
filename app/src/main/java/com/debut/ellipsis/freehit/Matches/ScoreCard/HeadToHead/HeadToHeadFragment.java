package com.debut.ellipsis.freehit.Matches.ScoreCard.HeadToHead;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.Matches.ScoreCard.InfoFragment;
import com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardElements.ScoreCardItem;
import com.debut.ellipsis.freehit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HeadToHeadFragment extends Fragment {

    private ProgressBar mProgressBar;
    private LinearLayoutManager mLinearLayoutManager;

    public HeadToHeadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String match_id = getActivity().getIntent().getStringExtra("match_id");

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_matchscorecard_head_to_head, container, false);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        View viewProgress = rootView.findViewById(R.id.progress);
        mProgressBar = (ProgressBar) viewProgress.findViewById(R.id.progress_bar);

        final View matches_won = rootView.findViewById(R.id.matches_won);

        final View home_wins = rootView.findViewById(R.id.home_wins);

        final View away_wins = rootView.findViewById(R.id.away_wins);

        final View highest_score = rootView.findViewById(R.id.highest_score);

        final View lowest_score = rootView.findViewById(R.id.lowest_score);

        final View highest_total_chased = rootView.findViewById(R.id.highest_total_chased);

        final View lowest_data_defended = rootView.findViewById(R.id.lowest_data_defended);

        Call<ScoreCardItem> call = MainActivity.apiInterface.doGetMatchScoreCard(match_id);
        call.enqueue(new Callback<ScoreCardItem>() {
            @Override
            public void onResponse(Call<ScoreCardItem> call, Response<ScoreCardItem> response) {

               InfoFragment.scoreCardItems = response.body().getResults();

                mProgressBar.setVisibility(View.GONE);
                TextView team1_name = (TextView) rootView.findViewById(R.id.team1_name);
                team1_name.setText(InfoFragment.scoreCardItems.get(2).getH2h().getTeam1().getTeam());

                TextView team2_name = (TextView) rootView.findViewById(R.id.team2_name);
                team2_name.setText(InfoFragment.scoreCardItems.get(2).getH2h().getTeam2().getTeam());

                TextView no_of_matches = (TextView) rootView.findViewById(R.id.no_of_matches);
                no_of_matches.setText("MATCHES : " +InfoFragment.scoreCardItems.get(2).getH2h().getStatus().getMatches());

                TextView tied_matches = (TextView) rootView.findViewById(R.id.tied_matches);
                tied_matches.setText("TIED : " +InfoFragment.scoreCardItems.get(2).getH2h().getStatus().getTied());

                TextView drawn_NR_matches = (TextView) rootView.findViewById(R.id.drawn_NR_matches);
                drawn_NR_matches.setText("DRAWN : " +InfoFragment.scoreCardItems.get(2).getH2h().getStatus().getDrawn());

                //For Matches Won
                TextView matches_won_label = (TextView) matches_won.findViewById(R.id.compare_type_name);
                matches_won_label.setText("MATCHES WON");

                TextView matches_won_team1 = (TextView) matches_won.findViewById(R.id.team1_value);
                matches_won_team1.setText(InfoFragment.scoreCardItems.get(2).getH2h().getTeam1().getMatches());

                TextView matches_won_team2 = (TextView) matches_won.findViewById(R.id.team2_value);
                matches_won_team2.setText(InfoFragment.scoreCardItems.get(2).getH2h().getTeam2().getMatches());

                //For Home Wins
                TextView home_win_label = (TextView) home_wins.findViewById(R.id.compare_type_name);
                home_win_label.setText("HOME WINS");

                TextView home_win_team1 = (TextView) home_wins.findViewById(R.id.team1_value);
                home_win_team1.setText(InfoFragment.scoreCardItems.get(2).getH2h().getTeam1().getHome());

                TextView home_win_team2 = (TextView) home_wins.findViewById(R.id.team2_value);
                home_win_team2.setText(InfoFragment.scoreCardItems.get(2).getH2h().getTeam2().getHome());

                //For Away Wins
                TextView away_win_label = (TextView) away_wins.findViewById(R.id.compare_type_name);
                away_win_label.setText("AWAY WINS");

                TextView away_win_team1 = (TextView) away_wins.findViewById(R.id.team1_value);
                away_win_team1.setText(InfoFragment.scoreCardItems.get(2).getH2h().getTeam1().getAway());

                TextView away_win_team2 = (TextView) away_wins.findViewById(R.id.team2_value);
                away_win_team2.setText(InfoFragment.scoreCardItems.get(2).getH2h().getTeam2().getAway());

                //For Highest Score
                TextView highest_score_label = (TextView) highest_score.findViewById(R.id.compare_type_name);
                highest_score_label.setText("HIGHEST SCORE");

                TextView highest_score_team1 = (TextView) highest_score.findViewById(R.id.team1_value);
                highest_score_team1.setText(InfoFragment.scoreCardItems.get(2).getH2h().getTeam1().getHighest());

                TextView highest_score_team2 = (TextView) highest_score.findViewById(R.id.team2_value);
                highest_score_team2.setText(InfoFragment.scoreCardItems.get(2).getH2h().getTeam2().getHighest());

                //For Lowest Score
                TextView lowest_score_label = (TextView) lowest_score.findViewById(R.id.compare_type_name);
                lowest_score_label.setText("LOWEST SCORE");

                TextView lowest_score_team1 = (TextView) lowest_score.findViewById(R.id.team1_value);
                lowest_score_team1.setText(InfoFragment.scoreCardItems.get(2).getH2h().getTeam1().getLowest());

                TextView lowest_score_team2 = (TextView) lowest_score.findViewById(R.id.team2_value);
                lowest_score_team2.setText(InfoFragment.scoreCardItems.get(2).getH2h().getTeam2().getLowest());

                //For Highest Total Chased
                TextView highest_total_chased_label = (TextView) highest_total_chased.findViewById(R.id.compare_type_name);
                highest_total_chased_label.setText("HIGHEST TOTAL CHASED");

                TextView highest_total_chased_team1 = (TextView) highest_total_chased.findViewById(R.id.team1_value);
                highest_total_chased_team1.setText(InfoFragment.scoreCardItems.get(2).getH2h().getTeam1().getChased());

                TextView highest_total_chased_team2 = (TextView) highest_total_chased.findViewById(R.id.team2_value);
                highest_total_chased_team2.setText(InfoFragment.scoreCardItems.get(2).getH2h().getTeam2().getChased());

                //For Lowest Total Defended
                TextView lowest_total_defended_label = (TextView) lowest_data_defended.findViewById(R.id.compare_type_name);
                lowest_total_defended_label.setText("LOWEST TOTAL DEFENDED");

                TextView lowest_total_defended_team1 = (TextView) lowest_data_defended.findViewById(R.id.team1_value);
                lowest_total_defended_team1.setText(InfoFragment.scoreCardItems.get(2).getH2h().getTeam1().getDefended());

                TextView lowest_total_defended_team2 = (TextView) lowest_data_defended.findViewById(R.id.team2_value);
                lowest_total_defended_team2.setText(InfoFragment.scoreCardItems.get(2).getH2h().getTeam2().getDefended());


            }
            @Override
            public void onFailure(Call<ScoreCardItem> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(),R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });

        return rootView;
    }

}
