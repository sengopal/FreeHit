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

        View viewProgress = (View) findViewById(R.id.progress);
        mProgressBar = (ProgressBar) viewProgress.findViewById(R.id.progress_bar);

        View viewRecyclerView = (View) findViewById(R.id.match_list_team);
        rv = (RecyclerView) viewRecyclerView.findViewById(R.id.recycler_list);

        View viewToolbar = (View) findViewById(R.id.toolbar_matches_list);

        toolbar = (Toolbar) viewToolbar.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.past_list);

        refresh_layout = (SwipeRefreshLayout) viewRecyclerView.findViewById(R.id.refresh_layout);

        CountryHash countryHash = new CountryHash();

        final String TeamName = countryHash.getCountrySN(Team.toUpperCase());

        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        View viewEmpty = (View) findViewById(R.id.empty);
        mEmptyView = (TextView) viewEmpty.findViewById(R.id.empty_view);

        /* GET List Past Matches for selected team */
        Call<PastMatchCardItem> call = apiInterface.doGetPastFavTeam(TeamName);
        call.enqueue(new Callback<PastMatchCardItem>() {
            @Override
            public void onResponse(Call<PastMatchCardItem> call, Response<PastMatchCardItem> response) {

                mProgressBar.setVisibility(View.GONE);

                List<PastMatchCardItem> pastMatchcardItems = response.body().getResults();

                if (pastMatchcardItems.size() == 0) {
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
                Toast toast = Toast.makeText(getApplicationContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                toast.show();
                call.cancel();
            }
        });

        refresh_layout.setColorSchemeResources(R.color.orange);
        refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                                @Override
                                                public void onRefresh() {

                                                    refresh_layout.setRefreshing(true);

                                                    Call<PastMatchCardItem> call = apiInterface.doGetPastFavTeam(TeamName);
                                                    call.enqueue(new Callback<PastMatchCardItem>() {
                                                        @Override
                                                        public void onResponse(Call<PastMatchCardItem> call, Response<PastMatchCardItem> response) {
                                                            mProgressBar.setVisibility(View.INVISIBLE);

                                                            List<PastMatchCardItem> pastMatchcardItems = response.body().getResults();
                                                            if (pastMatchcardItems.size() == 0) {
                                                                mEmptyView.setVisibility(View.VISIBLE);
                                                                mEmptyView.setText("NO MATCHES FOUND");
                                                            }

                                                            rv.setAdapter(new PastMatchesListAdapter(pastMatchcardItems, getApplicationContext()));
                                                        }

                                                        @Override
                                                        public void onFailure(Call<PastMatchCardItem> call, Throwable t) {
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
        FavTeamPastMatches.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);

    }
}