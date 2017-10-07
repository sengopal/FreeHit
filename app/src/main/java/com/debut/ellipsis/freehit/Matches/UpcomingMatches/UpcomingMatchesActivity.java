package com.debut.ellipsis.freehit.Matches.UpcomingMatches;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingMatchesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    APIInterface apiInterface;
    private UpcomingMatchListAdapter MatchListAdapter;
    private ProgressBar mProgressBar;
    private RecyclerView rv;
    public TextView mEmptyView;
    public SwipeRefreshLayout refresh_layout;

    public UpcomingMatchesActivity() {
        // Required empty public constructor
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_matches_complete_match_list);
        apiInterface = ApiClient.getClient().create(APIInterface.class);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        rv = (RecyclerView) findViewById(R.id.match_list);

        rv.setLayoutManager(new LinearLayoutManager(this));

        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);

        mEmptyView = (TextView) findViewById(R.id.empty_view);
        mEmptyView.setVisibility(View.INVISIBLE);

        toolbar = (Toolbar) findViewById(R.id.toolbar_for_match_list);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.upcoming_list);


        /**
         GET List Users
         **/
        Call<UpcomingMatchCardItem> call = apiInterface.doGetUpcomingCompleteMatchListResources();
        call.enqueue(new Callback<UpcomingMatchCardItem>() {
            @Override
            public void onResponse(Call<UpcomingMatchCardItem> call, Response<UpcomingMatchCardItem> response) {

                mProgressBar.setVisibility(View.GONE);

                List<UpcomingMatchCardItem> upcomingMatchesList = response.body().getResults();
                mProgressBar.setVisibility(View.GONE);
                if(upcomingMatchesList.size()==0)
                {
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

                Call<UpcomingMatchCardItem> call = apiInterface.doGetUpcomingCompleteMatchListResources();
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
                        MatchListAdapter = new UpcomingMatchListAdapter(getApplicationContext(),upcomingMatchesList);
                        rv.setAdapter(MatchListAdapter);
                    }

                    @Override
                    public void onFailure(Call<UpcomingMatchCardItem> call, Throwable t) {
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
        UpcomingMatchesActivity.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);

    }

}
