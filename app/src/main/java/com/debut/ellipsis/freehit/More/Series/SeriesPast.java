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


public class SeriesPast extends Fragment {
    APIInterface apiInterface;
    private PastMatchesListAdapter MatchListAdapter;
    private ProgressBar mProgressBar;
    private RecyclerView rv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_more_team_complete_match_list, container, false);

        Intent i = getActivity().getIntent();
        String Team = i.getStringExtra("CountryName");

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


        /*  GET List Past Matches for selected Team */
        Call<PastMatchCardItem> call = apiInterface.doGetPastFavTeam(TeamName);
        call.enqueue(new Callback<PastMatchCardItem>() {
            @Override
            public void onResponse(Call<PastMatchCardItem> call, Response<PastMatchCardItem> response) {

                List<PastMatchCardItem> pastMatchesList = response.body().getResults();
                mProgressBar.setVisibility(View.GONE);
                if (pastMatchesList.size() == 0) {
                    emptyView.setText(R.string.EmptyLiveMatches);
                    emptyView.setVisibility(View.VISIBLE);
                }

                MatchListAdapter = new PastMatchesListAdapter(pastMatchesList, getContext());
                rv.setAdapter(MatchListAdapter);
            }

            @Override
            public void onFailure(Call<PastMatchCardItem> call, Throwable t) {
                call.cancel();
            }
        });


        return rootView;
    }
}
