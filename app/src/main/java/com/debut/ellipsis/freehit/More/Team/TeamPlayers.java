package com.debut.ellipsis.freehit.More.Team;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.PlayerCountryItem;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamPlayers extends Fragment {

    ProgressBar mProgressBar;
    TextView mEmptyView;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_more_fav_team_player_list, container, false);

        Intent i = getActivity().getIntent();
        TeamActivity.Team = i.getIntExtra("CountryName", 0);
        TeamActivity.favTeam = i.getStringExtra("fav_country");

        if (TeamActivity.Team == 0) {
            TeamActivity.tempTeamName = TeamActivity.favTeam;
        } else {
            TeamActivity.tempTeamName = this.getContext().getString(TeamActivity.Team);
        }

        final String Team = TeamActivity.tempTeamName;

        View viewToolbar = rootView.findViewById(R.id.toolbar_fav_players);

        Toolbar toolbar = viewToolbar.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        View viewEmpty = rootView.findViewById(R.id.empty);
        mEmptyView = viewEmpty.findViewById(R.id.empty_view);

        View viewRecycler = rootView.findViewById(R.id.player_list);
        recyclerView = viewRecycler.findViewById(R.id.recycler_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        View viewProgress = rootView.findViewById(R.id.progress);
        mProgressBar = viewProgress.findViewById(R.id.progress_bar);

        final SwipeRefreshLayout refLayout = viewRecycler.findViewById(R.id.refresh_layout);

        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_YES:
                recyclerView.setBackgroundColor(getResources().getColor(R.color.night_background));
                toolbar.setBackgroundColor(getResources().getColor(R.color.dark));
                refLayout.setColorSchemeColors(Color.BLACK);
                break;
            default:
                refLayout.setColorSchemeResources(R.color.orange);
                break;
        }


        TeamPlayersList(Team);

        refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refLayout.setRefreshing(true);
                TeamPlayersList(Team);
                refLayout.setRefreshing(false);
            }
        });

        return rootView;
    }

    void TeamPlayersList(String Team)
    {
        Call<PlayerCountryItem> call1 = MainActivity.apiInterface.doGetFavTeamPlayers(Team);
        call1.enqueue(new Callback<PlayerCountryItem>() {
            @Override
            public void onResponse(Call<PlayerCountryItem> call, Response<PlayerCountryItem> response) {
                if (response.isSuccessful()) {
                    mProgressBar.setVisibility(View.GONE);
                    List<PlayerCountryItem> playerCountryItems = response.body().getResults();
                    if (playerCountryItems.size() == 0) {
                        mEmptyView.setVisibility(View.VISIBLE);
                        mEmptyView.setText(R.string.NoPlayers);
                    }
                    recyclerView.setAdapter(new TeamPlayerAdapter(playerCountryItems, R.layout.country_picker_row, getContext()));

                } else {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    mEmptyView.setVisibility(View.INVISIBLE);
                    Toast toast = Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                    toast.show();
                }
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
