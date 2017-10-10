package com.debut.ellipsis.freehit.Stats.Team;


import android.content.Intent;
import android.os.Bundle;
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
import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchCardItem;
import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchesListAdapter;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamPast extends Fragment {
    APIInterface apiInterface;
    private PastMatchesListAdapter MatchListAdapter;
    private ProgressBar mProgressBar;
    public SwipeRefreshLayout refresh_layout;
    private RecyclerView rv;

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

        final TextView emptyView = (TextView) rootView.findViewById(R.id.empty_view);

        refresh_layout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh_layout);


        /**
         GET List Past Matches for selected Team
         **/
        Call<PastMatchCardItem> call = apiInterface.doGetPastFavTeam(TeamName);
        call.enqueue(new Callback<PastMatchCardItem>() {
            @Override
            public void onResponse(Call<PastMatchCardItem> call, Response<PastMatchCardItem> response) {

                List<PastMatchCardItem> pastMatchesList = response.body().getResults();
                mProgressBar.setVisibility(View.GONE);
                if (pastMatchesList.size() == 0) {
                    emptyView.setText("NO MATCHES FOUND");
                    emptyView.setVisibility(View.VISIBLE);
                }

                MatchListAdapter = new PastMatchesListAdapter(pastMatchesList, getContext());
                rv.setAdapter(MatchListAdapter);
            }

            @Override
            public void onFailure(Call<PastMatchCardItem> call, Throwable t) {
                mProgressBar.setVisibility(View.INVISIBLE);
                emptyView.setVisibility(View.INVISIBLE);
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

                Call<PastMatchCardItem> call = apiInterface.doGetPastFavTeam(TeamName);
                call.enqueue(new Callback<PastMatchCardItem>() {
                    @Override
                    public void onResponse(Call<PastMatchCardItem> call, Response<PastMatchCardItem> response) {
                        mProgressBar.setVisibility(View.GONE);

                        List<PastMatchCardItem> pastMatchcardItems = response.body().getResults();
                        if(pastMatchcardItems.size()==0)
                        {
                            emptyView.setVisibility(View.VISIBLE);
                            emptyView.setText("NO MATCHES FOUND");
                        }

                        MatchListAdapter = new PastMatchesListAdapter(pastMatchcardItems, getContext());
                        rv.setAdapter(MatchListAdapter);
                    }

                    @Override
                    public void onFailure(Call<PastMatchCardItem> call, Throwable t) {
                        mProgressBar.setVisibility(View.INVISIBLE);
                        emptyView.setVisibility(View.INVISIBLE);
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
