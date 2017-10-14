package com.debut.ellipsis.freehit.More.Team;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.CountryItem;
import com.debut.ellipsis.freehit.More.favorites.FavouriteTeam.FavTeamPlayersAdapter;
import com.debut.ellipsis.freehit.PlayerCountryItem;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamPlayers extends Fragment {
    APIInterface apiInterface;
    private String TeamID;
    private Toolbar toolbar;
    private ProgressBar mProgressBar;
    public TextView mEmptyView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_more_fav_team_player_list, container, false);

        Intent i = getActivity().getIntent();
        final int TeamName = i.getIntExtra("CountryName",0);
        final String Team = this.getContext().getString(TeamName);

        View viewToolbar = (View) rootView.findViewById(R.id.toolbar_fav_players);

        toolbar = (Toolbar) viewToolbar.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        apiInterface = ApiClient.getClient().create(APIInterface.class);


        View viewEmpty = (View) rootView.findViewById(R.id.empty);
        mEmptyView = (TextView) viewEmpty.findViewById(R.id.empty_view);

        View viewRecycler = (View) rootView.findViewById(R.id.player_list);
        final RecyclerView recyclerView = (RecyclerView) viewRecycler.findViewById(R.id.recycler_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        View viewProgress = (View) rootView.findViewById(R.id.progress);
        mProgressBar = (ProgressBar) viewProgress.findViewById(R.id.progress_bar);

        final SwipeRefreshLayout refLayout = (SwipeRefreshLayout) viewRecycler.findViewById(R.id.refresh_layout);

        Call<CountryItem> call = apiInterface.doGetCountryResources();
        call.enqueue(new Callback<CountryItem>() {
            @Override
            public void onResponse(Call<CountryItem> call, Response<CountryItem> response) {

                List<CountryItem> countries = response.body().getResults();
                for (int i = 0; i < countries.size(); i++) {
                    if (countries.get(i).getTitle().equals(Team)) {
                        int teamID = countries.get(i).getId();
                        TeamID = String.valueOf(teamID);

                        Call<PlayerCountryItem> call1 = apiInterface.doGetFavTeamPlayers(TeamID);
                        call1.enqueue(new Callback<PlayerCountryItem>() {
                            @Override
                            public void onResponse(Call<PlayerCountryItem> call, Response<PlayerCountryItem> response) {

                                mProgressBar.setVisibility(View.GONE);
                                List<PlayerCountryItem> playerCountryItems = response.body().getResults();
                                if (playerCountryItems.size() == 0) {
                                    mEmptyView.setVisibility(View.VISIBLE);
                                    mEmptyView.setText("No Players Found");
                                }
                                recyclerView.setAdapter(new FavTeamPlayersAdapter(playerCountryItems, R.layout.country_picker_row, getContext()));

                            }

                            @Override
                            public void onFailure(Call<PlayerCountryItem> call, Throwable t) {
                                mProgressBar.setVisibility(View.INVISIBLE);
                                mEmptyView.setVisibility(View.INVISIBLE);
                                Toast toast = Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                                toast.show();
                                call.cancel();
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<CountryItem> call, Throwable t) {
                Toast toast = Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                toast.show();
                call.cancel();
            }
        });


        refLayout.setColorSchemeResources(R.color.orange);
        refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refLayout.setRefreshing(true);

                Call<CountryItem> call = apiInterface.doGetCountryResources();
                call.enqueue(new Callback<CountryItem>() {
                    @Override
                    public void onResponse(Call<CountryItem> call, Response<CountryItem> response) {

                        List<CountryItem> countries = response.body().getResults();
                        for (int i = 0; i < countries.size(); i++) {
                            if (countries.get(i).getTitle().equals(Team)) {
                                int teamID = countries.get(i).getId();
                                TeamID = String.valueOf(teamID);

                                Call<PlayerCountryItem> call1 = apiInterface.doGetFavTeamPlayers(TeamID);
                                call1.enqueue(new Callback<PlayerCountryItem>() {
                                    @Override
                                    public void onResponse(Call<PlayerCountryItem> call, Response<PlayerCountryItem> response) {

                                        mProgressBar.setVisibility(View.GONE);
                                        List<PlayerCountryItem> playerCountryItems = response.body().getResults();
                                        if (playerCountryItems.size() == 0) {
                                            mEmptyView.setVisibility(View.VISIBLE);
                                            mEmptyView.setText("No Players Found");
                                        }
                                        recyclerView.setAdapter(new FavTeamPlayersAdapter(playerCountryItems, R.layout.country_picker_row, getContext()));

                                    }

                                    @Override
                                    public void onFailure(Call<PlayerCountryItem> call, Throwable t) {
                                        mProgressBar.setVisibility(View.INVISIBLE);
                                        mEmptyView.setVisibility(View.INVISIBLE);
                                        Toast toast = Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                                        toast.show();
                                        call.cancel();
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CountryItem> call, Throwable t) {
                        Toast toast = Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                        toast.show();
                        call.cancel();
                    }
                });
                refLayout.setRefreshing(false);
            }
        });

        return rootView;
    }
}
