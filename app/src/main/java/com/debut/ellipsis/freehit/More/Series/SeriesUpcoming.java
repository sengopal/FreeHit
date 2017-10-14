package com.debut.ellipsis.freehit.More.Series;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.CountryHash;
import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchCardItem;
import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchListAdapter;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesUpcoming extends Fragment {
    private TabLayout tabLayout;
    APIInterface apiInterface;
    private UpcomingMatchListAdapter MatchListAdapter;
    private ProgressBar mProgressBar;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_more_team_complete_match_list, container, false);

        Intent i = getActivity().getIntent();
        String Team = i.getStringExtra("CountryName");
        String date=i.getStringExtra("date");
        String teams[]=Team.split(" vs " );
        String team1=teams[0];
        String team2=teams[1];

        CountryHash countryHash = new CountryHash();
        String TeamName = countryHash.getCountrySN(Team.toUpperCase());

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.match_card_tabs);
        tabLayout.setVisibility(View.GONE);

        apiInterface = ApiClient.getClient().create(APIInterface.class);

        View viewProgress = (View) rootView.findViewById(R.id.progress);
        mProgressBar = (ProgressBar) viewProgress.findViewById(R.id.progress_bar);

        View viewRecycler = (View) rootView.findViewById(R.id.complete_match_list);
        rv = (RecyclerView) viewRecycler.findViewById(R.id.recycler_list);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        View viewEmpty = (View) rootView.findViewById(R.id.empty);
        final TextView emptyView = (TextView) viewEmpty.findViewById(R.id.empty_view);
        CountryHash obj=new CountryHash();
        Toast.makeText(getContext(), team1+team2, Toast.LENGTH_SHORT).show();
        team1=obj.getCountrySN1(team1);

        team2=obj.getCountrySN1(team2);

        //Toast.makeText(getContext(), team1+team2, Toast.LENGTH_SHORT).show();


        /* GET List Upcoming Matches for selected team */
        String apiTeam=team1+","+team2;
        Call<UpcomingMatchCardItem> call = apiInterface.doGetUpComingSeriesMatches(apiTeam,date);
        call.enqueue(new Callback<UpcomingMatchCardItem>() {
            @Override
            public void onResponse(Call<UpcomingMatchCardItem> call, Response<UpcomingMatchCardItem> response) {

                List<UpcomingMatchCardItem> upcomingMatchesList = response.body().getResults();
                mProgressBar.setVisibility(View.GONE);
                if (upcomingMatchesList.size() == 0) {
                    emptyView.setText(R.string.EmptyLiveMatches);
                    emptyView.setVisibility(View.VISIBLE);
                }

                MatchListAdapter = new UpcomingMatchListAdapter(getContext(), upcomingMatchesList);
                rv.setAdapter(MatchListAdapter);
            }

            @Override
            public void onFailure(Call<UpcomingMatchCardItem> call, Throwable t) {
                call.cancel();
            }
        });


        return rootView;
    }

}
