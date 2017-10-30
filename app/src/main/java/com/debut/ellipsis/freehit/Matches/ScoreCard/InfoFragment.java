package com.debut.ellipsis.freehit.Matches.ScoreCard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardElements.ScoreCardItem;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoFragment extends Fragment {

    private ProgressBar mProgressBar;
    public static List<ScoreCardItem> scoreCardItems;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        String match_id = getActivity().getIntent().getStringExtra("match_id");
        final String match_name = getActivity().getIntent().getStringExtra("match_name");
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_matchscorecard_info, container, false);

        MainActivity.apiInterface = ApiClient.getClient().create(APIInterface.class);

        View viewProgress = rootView.findViewById(R.id.progress);
        mProgressBar = (ProgressBar) viewProgress.findViewById(R.id.progress_bar);

        Call<ScoreCardItem> call = MainActivity.apiInterface.doGetMatchScoreCard(match_id);
        call.enqueue(new Callback<ScoreCardItem>() {
            @Override
            public void onResponse(Call<ScoreCardItem> call, Response<ScoreCardItem> response) {

                mProgressBar.setVisibility(View.GONE);
                scoreCardItems = response.body().getResults();

                TextView match_info_team1 = (TextView) rootView.findViewById(R.id.match_info_team1);
                match_info_team1.setText(scoreCardItems.get(0).getScorecard().getTeam1().getTeam());

                TextView match_info_team2 = (TextView) rootView.findViewById(R.id.match_info_team2);
                match_info_team2.setText(scoreCardItems.get(0).getScorecard().getTeam2().getTeam());

                TextView match = (TextView) rootView.findViewById(R.id.match_info_match_name);
                match.setText(match_name);

                TextView series = (TextView) rootView.findViewById(R.id.match_info_series_name);
                series.setText(scoreCardItems.get(1).getInfo().getSeries());

                TextView toss = (TextView) rootView.findViewById(R.id.match_info_toss_status);
                toss.setText(scoreCardItems.get(1).getInfo().getToss());

                TextView MOTM = (TextView) rootView.findViewById(R.id.match_info_motm_status);
                MOTM.setText(scoreCardItems.get(1).getInfo().getMom());

                TextView MOTS = (TextView) rootView.findViewById(R.id.match_info_mots_status);
                MOTS.setText(scoreCardItems.get(1).getInfo().getManofseries());

                TextView result = (TextView) rootView.findViewById(R.id.match_info_result_status);
                result.setText(scoreCardItems.get(1).getInfo().getResult());

                TextView SeriesStatus = (TextView) rootView.findViewById(R.id.match_info_SeriesStatus_status);
                SeriesStatus.setText(scoreCardItems.get(1).getInfo().getStatus());

                TextView date = (TextView) rootView.findViewById(R.id.match_info_date);
                date.setText(scoreCardItems.get(1).getInfo().getDay());

                TextView time = (TextView) rootView.findViewById(R.id.match_info_time);
                time.setText(scoreCardItems.get(1).getInfo().getTime());

                TextView stadium = (TextView) rootView.findViewById(R.id.match_info_stadium);
                stadium.setText(scoreCardItems.get(1).getInfo().getStadium());

                TextView umpires = (TextView) rootView.findViewById(R.id.match_info_umpires);
                umpires.setText(scoreCardItems.get(1).getInfo().getUmpires());

                TextView ref = (TextView) rootView.findViewById(R.id.match_info_referee);
                ref.setText(scoreCardItems.get(1).getInfo().getRef());

                TextView weather = (TextView) rootView.findViewById(R.id.match_info_weather);
                weather.setText(scoreCardItems.get(1).getInfo().getWeather());

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
