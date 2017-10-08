package com.debut.ellipsis.freehit.Stats.favorites.FavouriteTeam;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.CountryItem;
import com.debut.ellipsis.freehit.PlayerCountryItem;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavTeamPlayers extends AppCompatActivity {

    APIInterface apiInterface;
    private String TeamID;
    private Toolbar toolbar;
    private ProgressBar mProgressBar;
    public TextView mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fav_team_player_list);

        Intent i = getIntent();
        final String Team = i.getStringExtra("CountryName");


        toolbar = (Toolbar) findViewById(R.id.toolbar_for_match_list);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(Team + " Players");

        apiInterface = ApiClient.getClient().create(APIInterface.class);

        mEmptyView = (TextView) findViewById(R.id.empty_view);
        mEmptyView.setVisibility(View.INVISIBLE);


        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.player_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        final SwipeRefreshLayout refLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);

        Call<CountryItem> call = apiInterface.doGetCountryResources();
        call.enqueue(new Callback<CountryItem>() {
            @Override
            public void onResponse(Call<CountryItem> call, Response<CountryItem> response) {

                List<CountryItem> countries = response.body().getResults();
                for (int i = 0; i < countries.size(); i++) {
                    if (countries.get(i).getTitle().equals(Team)) {
                        System.out.println(countries.get(i).getId());
                        int teamid = countries.get(i).getId();
                        TeamID = String.valueOf(teamid);

                        Call<PlayerCountryItem> call1 = apiInterface.doGetFavTeamPlayers(TeamID);
                        call1.enqueue(new Callback<PlayerCountryItem>() {
                            @Override
                            public void onResponse(Call<PlayerCountryItem> call, Response<PlayerCountryItem> response) {

                                mProgressBar.setVisibility(View.GONE);
                                List<PlayerCountryItem> playerCountryItems = response.body().getResults();
                                if (playerCountryItems.size() == 0) {
                                    mEmptyView.setVisibility(View.VISIBLE);
                                    mEmptyView.setText("NO MATCHES FOUND");
                                }
                                recyclerView.setAdapter(new FavTeamPlayersAdapter(playerCountryItems, R.layout.country_picker_row, getApplicationContext()));

                            }

                            @Override
                            public void onFailure(Call<PlayerCountryItem> call, Throwable t) {
                                mProgressBar.setVisibility(View.INVISIBLE);
                                mEmptyView.setVisibility(View.INVISIBLE);
                                Toast toast = Toast.makeText(getApplicationContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                                toast.show();
                                call.cancel();
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<CountryItem> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                toast.show();
                call.cancel();
            }
        });


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
                                System.out.println(countries.get(i).getId());
                                int teamid = countries.get(i).getId();
                                TeamID = String.valueOf(teamid);

                                Call<PlayerCountryItem> call1 = apiInterface.doGetFavTeamPlayers(TeamID);
                                call1.enqueue(new Callback<PlayerCountryItem>() {
                                    @Override
                                    public void onResponse(Call<PlayerCountryItem> call, Response<PlayerCountryItem> response) {

                                        mProgressBar.setVisibility(View.GONE);
                                        List<PlayerCountryItem> playerCountryItems = response.body().getResults();
                                        if (playerCountryItems.size() == 0) {
                                            mEmptyView.setVisibility(View.VISIBLE);
                                            mEmptyView.setText("NO MATCHES FOUND");
                                        }
                                        recyclerView.setAdapter(new FavTeamPlayersAdapter(playerCountryItems, R.layout.country_picker_row, getApplicationContext()));

                                    }

                                    @Override
                                    public void onFailure(Call<PlayerCountryItem> call, Throwable t) {
                                        mProgressBar.setVisibility(View.INVISIBLE);
                                        mEmptyView.setVisibility(View.INVISIBLE);
                                        Toast toast = Toast.makeText(getApplicationContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                                        toast.show();
                                        call.cancel();
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CountryItem> call, Throwable t) {
                        Toast toast = Toast.makeText(getApplicationContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                        toast.show();
                        call.cancel();
                    }
                });
                refLayout.setRefreshing(false);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(0, R.anim.exit_to_right);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FavTeamPlayers.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);

    }
}
