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
import com.debut.ellipsis.freehit.CountryHash;
import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchCardItem;
import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchesListAdapter;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavTeamPastMatches extends AppCompatActivity {

    APIInterface apiInterface;
    private PastMatchesListAdapter MatchListAdapter;
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
        mProgressBar = (ProgressBar)findViewById(R.id.progress_bar);
        rv = (RecyclerView)findViewById(R.id.match_list);

        toolbar = (Toolbar) findViewById(R.id.toolbar_for_match_list);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.past_list);

        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);

        CountryHash countryHash = new CountryHash();

        final String TeamName = countryHash.getCountrySN(Team.toUpperCase());

        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mEmptyView = (TextView) findViewById(R.id.empty_view);
        mEmptyView.setVisibility(View.INVISIBLE);


        /**
         GET List Upcoming Matches for selected team
         **/
        Call<PastMatchCardItem> call = apiInterface.doGetPastFavTeam(TeamName);
        call.enqueue(new Callback<PastMatchCardItem>() {
            @Override
            public void onResponse(Call<PastMatchCardItem> call, Response<PastMatchCardItem> response) {

                mProgressBar.setVisibility(View.GONE);

                List<PastMatchCardItem> pastMatchcardItems = response.body().getResults();

                if(pastMatchcardItems.size()==0)
                {
                    mEmptyView.setVisibility(View.VISIBLE);
                    mEmptyView.setText("NO MATCHES FOUND");
                }
                MatchListAdapter = new PastMatchesListAdapter(pastMatchcardItems, getApplicationContext());
                rv.setAdapter(MatchListAdapter);
            }

            @Override
            public void onFailure(Call<PastMatchCardItem> call, Throwable t) {
                mProgressBar.setVisibility(View.INVISIBLE);
                mEmptyView.setVisibility(View.INVISIBLE);
                Toast toast=Toast.makeText(getApplicationContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT);
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
                            mEmptyView.setVisibility(View.VISIBLE);
                            mEmptyView.setText("NO MATCHES FOUND");
                        }

                        MatchListAdapter = new PastMatchesListAdapter(pastMatchcardItems, getApplicationContext());
                        rv.setAdapter(MatchListAdapter);
                    }

                    @Override
                    public void onFailure(Call<PastMatchCardItem> call, Throwable t) {
                        mProgressBar.setVisibility(View.INVISIBLE);
                        mEmptyView.setVisibility(View.INVISIBLE);
                        Toast toast=Toast.makeText(getApplicationContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT);
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
        FavTeamPastMatches.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);

    }
}