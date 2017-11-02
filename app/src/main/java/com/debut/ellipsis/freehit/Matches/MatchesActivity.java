package com.debut.ellipsis.freehit.Matches;


import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
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
import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchCardItem;
import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchesListAdapter;
import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchCardItem;
import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchListAdapter;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    APIInterface apiInterface;
    private PastMatchesListAdapter PastMatchListAdapter;
    private UpcomingMatchListAdapter UpcomingMatchListAdapter;
    private ProgressBar mProgressBar;
    private RecyclerView rv;
    public SwipeRefreshLayout refresh_layout;
    private FloatingActionButton fab;
    public TextView mEmptyView;
    private LinearLayoutManager mLinearLayoutManager;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_matches_complete_match_list);

        String match_format = getIntent().getStringExtra("match_format");

        apiInterface = ApiClient.getClient().create(APIInterface.class);

        View viewProgress = findViewById(R.id.progress);
        mProgressBar = viewProgress.findViewById(R.id.progress_bar);

        View viewFAB = findViewById(R.id.fab);
        fab = viewFAB.findViewById(R.id.common_fab);
        fab.hide();
        fab.setImageResource(R.drawable.arrow_up);

        View viewRecycler = findViewById(R.id.match_list_team);
        rv = viewRecycler.findViewById(R.id.recycler_list);


        refresh_layout = viewRecycler.findViewById(R.id.refresh_layout);

        mLinearLayoutManager = new LinearLayoutManager(this);

        rv.setLayoutManager(mLinearLayoutManager);

        View viewToolbar = findViewById(R.id.toolbar_matches_list);

        toolbar = viewToolbar.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppThemeDark);
            rv.setBackgroundColor(getResources().getColor(R.color.night_background));
            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dark)));
        }

        View viewEmpty = findViewById(R.id.empty);
        mEmptyView = viewEmpty.findViewById(R.id.empty_view);

        if (match_format.equals("PAST")) {
            setTitle(R.string.past_list);
            Call<PastMatchCardItem> call = apiInterface.doGetCompletePastCardResources();
            call.enqueue(new Callback<PastMatchCardItem>() {
                @Override
                public void onResponse(Call<PastMatchCardItem> call, Response<PastMatchCardItem> response) {
                    mProgressBar.setVisibility(View.GONE);

                    List<PastMatchCardItem> pastMatchCardItems = response.body().getResults();
                    if (pastMatchCardItems.size() == 0) {
                        mEmptyView.setVisibility(View.VISIBLE);
                        mEmptyView.setText(R.string.EmptyMatches);
                    }

                    PastMatchListAdapter = new PastMatchesListAdapter(pastMatchCardItems, getApplicationContext());
                    rv.setAdapter(PastMatchListAdapter);
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
                                                        // Checking if connected or not on refresh
                                                        refresh_layout.setRefreshing(true);

                                                        Call<PastMatchCardItem> call = apiInterface.doGetCompletePastCardResources();
                                                        call.enqueue(new Callback<PastMatchCardItem>() {
                                                            @Override
                                                            public void onResponse(Call<PastMatchCardItem> call, Response<PastMatchCardItem> response) {
                                                                mProgressBar.setVisibility(View.GONE);

                                                                List<PastMatchCardItem> pastMatchCardItems = response.body().getResults();
                                                                if (pastMatchCardItems.size() == 0) {
                                                                    mEmptyView.setVisibility(View.VISIBLE);
                                                                    mEmptyView.setText(R.string.EmptyMatches);
                                                                }

                                                                PastMatchListAdapter = new PastMatchesListAdapter(pastMatchCardItems, getApplicationContext());
                                                                rv.setAdapter(PastMatchListAdapter);
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
        if (match_format.equals("UPCOMING")) {
            setTitle(R.string.upcoming_list);

            Call<UpcomingMatchCardItem> call = apiInterface.doGetUpcomingCompleteMatchListResources();
            call.enqueue(new Callback<UpcomingMatchCardItem>() {
                @Override
                public void onResponse(Call<UpcomingMatchCardItem> call, Response<UpcomingMatchCardItem> response) {

                    mProgressBar.setVisibility(View.GONE);

                    List<UpcomingMatchCardItem> upcomingMatchesList = response.body().getResults();
                    mProgressBar.setVisibility(View.GONE);
                    if (upcomingMatchesList.size() == 0) {
                        mEmptyView.setVisibility(View.VISIBLE);
                        mEmptyView.setText(R.string.EmptyMatches);
                    }

                    UpcomingMatchListAdapter = new UpcomingMatchListAdapter(getApplicationContext(), upcomingMatchesList);
                    rv.setAdapter(UpcomingMatchListAdapter);
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

                                                        Call<UpcomingMatchCardItem> call = apiInterface.doGetUpcomingCompleteMatchListResources();
                                                        call.enqueue(new Callback<UpcomingMatchCardItem>() {
                                                            @Override
                                                            public void onResponse(Call<UpcomingMatchCardItem> call, Response<UpcomingMatchCardItem> response) {
                                                                mProgressBar.setVisibility(View.GONE);

                                                                List<UpcomingMatchCardItem> upcomingMatchesList = response.body().getResults();

                                                                if (upcomingMatchesList.size() == 0) {
                                                                    mEmptyView.setVisibility(View.VISIBLE);
                                                                    mEmptyView.setText(R.string.EmptyMatches);
                                                                }
                                                                UpcomingMatchListAdapter = new UpcomingMatchListAdapter(getApplicationContext(), upcomingMatchesList);
                                                                rv.setAdapter(UpcomingMatchListAdapter);
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

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (mLinearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                    fab.hide();

                } else {
                    fab.show();
                }
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int firstVisibleItemIndex = mLinearLayoutManager.findFirstVisibleItemPosition();
                if (firstVisibleItemIndex > 0) {
                    mLinearLayoutManager.smoothScrollToPosition(rv, null, 0);
                }
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
        MatchesActivity.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);
    }
}
