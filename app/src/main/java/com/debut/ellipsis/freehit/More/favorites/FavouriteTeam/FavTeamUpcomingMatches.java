package com.debut.ellipsis.freehit.More.favorites.FavouriteTeam;

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
import com.debut.ellipsis.freehit.CountryHash;
import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchCardItem;
import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchListAdapter;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavTeamUpcomingMatches extends AppCompatActivity {

    APIInterface apiInterface;
    private UpcomingMatchListAdapter MatchListAdapter;
    private ProgressBar mProgressBar;
    private RecyclerView rv;
    private Toolbar toolbar;
    public SwipeRefreshLayout refresh_layout;
    public TextView mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_matches_complete_match_list);
        Intent i = getIntent();
        String Team = i.getStringExtra("CountryName");

        apiInterface = ApiClient.getClient().create(APIInterface.class);

        View viewProgress = (View) findViewById(R.id.progress);
        mProgressBar = (ProgressBar) viewProgress.findViewById(R.id.progress_bar);

        View viewRecyclerView = (View) findViewById(R.id.match_list_team);
        rv = (RecyclerView) viewRecyclerView.findViewById(R.id.recycler_list);

        View viewToolbar = (View) findViewById(R.id.toolbar_matches_list);

        toolbar = (Toolbar) viewToolbar.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.upcoming_list);

        CountryHash countryHash = new CountryHash();

        final String TeamName = countryHash.getCountrySN(Team.toUpperCase());

        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        refresh_layout = (SwipeRefreshLayout) viewRecyclerView.findViewById(R.id.refresh_layout);

        View viewEmpty = (View) findViewById(R.id.empty);
        mEmptyView = (TextView) viewEmpty.findViewById(R.id.empty_view);


        /* GET List Upcoming Matches for selected team */
        Call<UpcomingMatchCardItem> call = apiInterface.doGetUpcomingFavTeam(TeamName);
        call.enqueue(new Callback<UpcomingMatchCardItem>() {
            @Override
            public void onResponse(Call<UpcomingMatchCardItem> call, Response<UpcomingMatchCardItem> response) {

                mProgressBar.setVisibility(View.GONE);

                List<UpcomingMatchCardItem> upcomingMatchesList = response.body().getResults();
                mProgressBar.setVisibility(View.GONE);
                if (upcomingMatchesList.size() == 0) {
                    mEmptyView.setVisibility(View.VISIBLE);
                    mEmptyView.setText("NO MATCHES FOUND");
                }
                MatchListAdapter = new UpcomingMatchListAdapter(getApplicationContext(), upcomingMatchesList);
                rv.setAdapter(MatchListAdapter);
            }

            @Override
            public void onFailure(Call<UpcomingMatchCardItem> call, Throwable t) {
                mProgressBar.setVisibility(View.INVISIBLE);
                mEmptyView.setVisibility(View.INVISIBLE);
                Toast toast = Toast.makeText(getApplicationContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                toast.show();
                call.cancel();
            }
        });

        refresh_layout.setColorSchemeResources(R.color.orange);
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
                                                            if (upcomingMatchesList.size() == 0) {
                                                                mEmptyView.setVisibility(View.VISIBLE);
                                                                mEmptyView.setText("NO MATCHES FOUND");
                                                            }

                                                            MatchListAdapter = new UpcomingMatchListAdapter(getApplicationContext(), upcomingMatchesList);
                                                            rv.setAdapter(MatchListAdapter);
                                                        }

                                                        @Override
                                                        public void onFailure(Call<UpcomingMatchCardItem> call, Throwable t) {
                                                            mProgressBar.setVisibility(View.INVISIBLE);
                                                            mEmptyView.setVisibility(View.INVISIBLE);
                                                            Toast toast = Toast.makeText(getApplicationContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                                                            toast.show();
                                                            call.cancel();

                                                        }
                                                    });
                                                    refresh_layout.setRefreshing(false);
                                                }
                                            }
        );


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
        FavTeamUpcomingMatches.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);

    }
}
