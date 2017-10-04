package com.debut.ellipsis.freehit.Matches.UpcomingMatches;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

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

                List<UpcomingMatchCardItem> upcomingMatchesList = response.body().getResults();
                mProgressBar.setVisibility(View.GONE);

                MatchListAdapter = new UpcomingMatchListAdapter(getApplicationContext(), upcomingMatchesList);
                rv.setAdapter(MatchListAdapter);
            }

            @Override
            public void onFailure(Call<UpcomingMatchCardItem> call, Throwable t) {
                call.cancel();
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
        UpcomingMatchesActivity.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);

    }

}
