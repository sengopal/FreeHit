package com.debut.ellipsis.freehit.Matches.ScoreCard;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.Matches.LiveMatches.LiveMatchScoreCard;
import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchScoreCard;
import com.debut.ellipsis.freehit.R;

import java.util.List;


public class InfoFragment extends Fragment {

    String team_1, team_2;

    public InfoFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        final String match_type = getActivity().getIntent().getStringExtra("match_type");
        String match_name = getActivity().getIntent().getStringExtra("match_name");
        final String team_1_intent = getActivity().getIntent().getStringExtra("team1");
        String team_2_intent = getActivity().getIntent().getStringExtra("team2");

        View rootView = null;

        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_YES:
                rootView = inflater.inflate(R.layout.fragment_matchscorecard_info_dark, container, false);
                ImageView arrow1 = rootView.findViewById(R.id.arrow_team_1);
                arrow1.setColorFilter(Color.WHITE);
                ImageView arrow2 = rootView.findViewById(R.id.arrow_team_2);
                arrow2.setColorFilter(Color.WHITE);
                break;
            case AppCompatDelegate.MODE_NIGHT_NO:
                rootView = inflater.inflate(R.layout.fragment_matchscorecard_info, container, false);
                break;
        }

        MainActivity.apiInterface = ApiClient.getClient().create(APIInterface.class);


        List<ScoreCardItem> teamList = null;

        if (match_type.equals("PAST")) {
            teamList = PastMatchScoreCard.getQList();
        }
        else if(match_type.equals("LIVE")){
            teamList = LiveMatchScoreCard.getQList();
        }

        if (teamList.get(0).getScorecard().getTeam1().getInncount() != 0) {
            if (teamList.get(0).getScorecard().getTeam1().getTeamname().equals(team_1_intent)) {
                team_1 = team_1_intent;
                team_2 = team_2_intent;
            } else {
                team_1 = team_2_intent;
                team_2 = team_1_intent;
            }
        } else if (teamList.get(0).getScorecard().getTeam2().getInncount() != 0) {
            if (teamList.get(0).getScorecard().getTeam2().getTeamname().equals(team_2_intent)) {
                team_2 = team_2_intent;
                team_1 = team_1_intent;
            } else {
                team_2 = team_1_intent;
                team_1 = team_2_intent;
            }
        }

        TextView match_info_team1 = rootView.findViewById(R.id.match_info_team1);
        match_info_team1.setText(team_1);

        TextView match_info_team2 = rootView.findViewById(R.id.match_info_team2);
        match_info_team2.setText(team_2);

        final TextView match = rootView.findViewById(R.id.match_info_match_name);
        match.setText(match_name);

        TextView series = rootView.findViewById(R.id.match_info_series_name);
        series.setText(teamList.get(1).getInfo().getSeries());

        TextView toss = rootView.findViewById(R.id.match_info_toss_status);
        toss.setText(teamList.get(1).getInfo().getToss());

        TextView MOTM = rootView.findViewById(R.id.match_info_motm_status);
        MOTM.setText(teamList.get(1).getInfo().getMom());

        TextView MOTS = rootView.findViewById(R.id.match_info_mots_status);
        MOTS.setText(teamList.get(1).getInfo().getManofseries());

        TextView result = rootView.findViewById(R.id.match_info_result_status);
        result.setText(teamList.get(1).getInfo().getResult());

        TextView SeriesStatus = rootView.findViewById(R.id.match_info_SeriesStatus_status);
        SeriesStatus.setText(teamList.get(1).getInfo().getStatus());

        TextView date = rootView.findViewById(R.id.match_info_date);
        date.setText(teamList.get(1).getInfo().getDay());

        TextView time = rootView.findViewById(R.id.match_info_time);
        time.setText(teamList.get(1).getInfo().getTime());

        TextView stadium = rootView.findViewById(R.id.match_info_stadium);
        stadium.setText(teamList.get(1).getInfo().getStadium());

        TextView umpires = rootView.findViewById(R.id.match_info_umpires);
        umpires.setText(teamList.get(1).getInfo().getUmpires());

        TextView ref = rootView.findViewById(R.id.match_info_referee);
        ref.setText(teamList.get(1).getInfo().getRef());

        TextView weather = rootView.findViewById(R.id.match_info_weather);
        weather.setText(teamList.get(1).getInfo().getWeather());

        TableRow team1 = rootView.findViewById(R.id.team1);

        final List<ScoreCardItem> finalTeamList = teamList;
        team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(finalTeamList.get(0).getScorecard().getTeam1().getTeamname()!= null)
                {
                    Intent PlayingX1 = new Intent(getActivity().getBaseContext(), com.debut.ellipsis.freehit.Matches.ScoreCard.PlayingX1.class);
                    PlayingX1.putExtra("team", "TEAM_1");
                    if (match_type.equals("PAST")) {
                        PlayingX1.putExtra("match_type", "PAST");
                    }
                    else if(match_type.equals("LIVE")) {
                        PlayingX1.putExtra("match_type","LIVE");
                    }
                    getActivity().startActivity(PlayingX1);
                }
                else
                {
                    Toast.makeText(getContext(),"Playing X1 not available",Toast.LENGTH_SHORT).show();
                }
            }
        });

        TableRow team2 = rootView.findViewById(R.id.team2);

        team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(finalTeamList.get(0).getScorecard().getTeam2().getTeamname()!= null)
                {
                    Intent PlayingX1 = new Intent(getActivity().getBaseContext(), com.debut.ellipsis.freehit.Matches.ScoreCard.PlayingX1.class);
                    PlayingX1.putExtra("team", "TEAM_2");
                    if (match_type.equals("PAST")) {
                        PlayingX1.putExtra("match_type", "PAST");
                    }
                    else if(match_type.equals("LIVE")) {
                        PlayingX1.putExtra("match_type","LIVE");
                    }
                    getActivity().startActivity(PlayingX1);
                }
                else
                {
                    Toast.makeText(getContext(),"Playing X1 not available",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

}
