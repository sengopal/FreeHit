package com.debut.ellipsis.freehit.Stats.Team;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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


public class TeamUpcoming extends Fragment {
    private TabLayout tabLayout;
    APIInterface apiInterface;
    private UpcomingMatchListAdapter MatchListAdapter;
    private ProgressBar mProgressBar;
    private RecyclerView rv;
    public SwipeRefreshLayout refresh_layout;
    public TextView mEmptyView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_more_team_complete_match_list, container, false);

        Intent i = getActivity().getIntent();
        int Team = i.getIntExtra("CountryName", 0);
        String tempTeamName = this.getContext().getString(Team);

        CountryHash countryHash = new CountryHash();
        final String TeamName = countryHash.getCountrySN(tempTeamName.toUpperCase());

        apiInterface = ApiClient.getClient().create(APIInterface.class);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        rv = (RecyclerView) rootView.findViewById(R.id.match_list);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        refresh_layout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh_layout);

        mEmptyView = (TextView) rootView.findViewById(R.id.empty_view);
        mEmptyView.setVisibility(View.INVISIBLE);


        /**
         GET List Upcoming Matches for selected team
         **/
        Call<UpcomingMatchCardItem> call = apiInterface.doGetUpcomingFavTeam(TeamName);
        call.enqueue(new Callback<UpcomingMatchCardItem>() {
            @Override
            public void onResponse(Call<UpcomingMatchCardItem> call, Response<UpcomingMatchCardItem> response) {

                List<UpcomingMatchCardItem> upcomingMatchesList = response.body().getResults();
                mProgressBar.setVisibility(View.GONE);
                if (upcomingMatchesList.size() == 0) {
                    mEmptyView.setVisibility(View.VISIBLE);
                    mEmptyView.setText("NO MATCHES FOUND");
                }

                MatchListAdapter = new UpcomingMatchListAdapter(getContext(), upcomingMatchesList);
                rv.setAdapter(MatchListAdapter);
            }

            @Override
            public void onFailure(Call<UpcomingMatchCardItem> call, Throwable t) {
                mProgressBar.setVisibility(View.INVISIBLE);
                mEmptyView.setVisibility(View.INVISIBLE);
                Toast toast=Toast.makeText(getContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT);
                toast.show();
                call.cancel();
            }
        });

        refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Checking if connected or not on refresh
                refresh_layout.setRefreshing(true);

                Call<UpcomingMatchCardItem> call = apiInterface.doGetUpcomingFavTeam(TeamName);
                call.enqueue(new Callback<UpcomingMatchCardItem>() {
                    @Override
                    public void onResponse(Call<UpcomingMatchCardItem> call, Response<UpcomingMatchCardItem> response) {
                        mProgressBar.setVisibility(View.GONE);

                        List<UpcomingMatchCardItem> upcomingMatchesList = response.body().getResults();
                        if(upcomingMatchesList.size()==0)
                        {
                            mEmptyView.setVisibility(View.VISIBLE);
                            mEmptyView.setText("NO MATCHES FOUND");
                        }
                        MatchListAdapter = new UpcomingMatchListAdapter(getContext(),upcomingMatchesList);
                        rv.setAdapter(MatchListAdapter);
                    }

                    @Override
                    public void onFailure(Call<UpcomingMatchCardItem> call, Throwable t) {
                        mProgressBar.setVisibility(View.INVISIBLE);
                        mEmptyView.setVisibility(View.INVISIBLE);
                        Toast toast=Toast.makeText(getContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT);
                        toast.show();
                        call.cancel();

                    }
                });
                refresh_layout.setRefreshing(false);
            }
        }
        );


        return rootView;
    }
}
