package com.debut.ellipsis.freehit.Matches.ScoreCard;


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
import android.widget.TextView;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchScoreCard;
import com.debut.ellipsis.freehit.R;

import java.util.List;


public class InfoFragment extends Fragment {

    public InfoFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        String match_type = getActivity().getIntent().getStringExtra("match_type");
        final String match_name = getActivity().getIntent().getStringExtra("match_name");

        View rootView= null ;

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
            teamList = ((PastMatchScoreCard) getActivity()).getQList();
        }

        TextView match_info_team1 = rootView.findViewById(R.id.match_info_team1);
        match_info_team1.setText(teamList.get(0).getScorecard().getTeam1().getFirstinn().getTeam());

        TextView match_info_team2 = rootView.findViewById(R.id.match_info_team2);
        match_info_team2.setText(teamList.get(0).getScorecard().getTeam2().getFirstinn().getTeam());

        TextView match = rootView.findViewById(R.id.match_info_match_name);
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

        return rootView;
    }

}
