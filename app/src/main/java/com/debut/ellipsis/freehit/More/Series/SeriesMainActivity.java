package com.debut.ellipsis.freehit.More.Series;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesMainActivity extends AppCompatActivity {


    public SeriesMainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_more_series_listview);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

        View viewToolbar = (View) findViewById(R.id.series_toolbar);

        Toolbar toolbar = (Toolbar) viewToolbar.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Series");

        View viewRecycler = findViewById(R.id.series_list);

        final RecyclerView recyclerView = (RecyclerView) viewRecycler.findViewById(R.id.recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        final SwipeRefreshLayout refLayout = (SwipeRefreshLayout) viewRecycler.findViewById(R.id.refresh_layout);

        View vProgress = findViewById(R.id.progress);
        final ProgressBar mProgressbar = (ProgressBar) vProgress.findViewById(R.id.progress_bar);

        final APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);

        Call<SeriesItem> seriesInfo = apiInterface.doGetSeries();
        seriesInfo.enqueue(new Callback<com.debut.ellipsis.freehit.More.Series.SeriesItem>() {
            @Override
            public void onResponse(Call<SeriesItem> call, Response<SeriesItem> response) {
                mProgressbar.setVisibility(View.GONE);
                List<SeriesItem> seriesInfo = response.body().getResults();

                recyclerView.setAdapter(new SeriesItemAdapter(seriesInfo, R.layout.fragment_more_series_list_item, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<SeriesItem> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });

        refLayout.setColorSchemeResources(R.color.orange);
        refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                                @Override
                                                public void onRefresh() {
                                                    // Checking if connected or not on refresh
                                                    refLayout.setRefreshing(true);

                                                    Call<SeriesItem> seriesInfo = apiInterface.doGetSeries();
                                                    seriesInfo.enqueue(new Callback<com.debut.ellipsis.freehit.More.Series.SeriesItem>() {
                                                        @Override
                                                        public void onResponse(Call<SeriesItem> call, Response<SeriesItem> response) {
                                                            mProgressbar.setVisibility(View.GONE);
                                                            List<SeriesItem> seriesInfo = response.body().getResults();
                                                            recyclerView.setAdapter(new SeriesItemAdapter(seriesInfo, R.layout.fragment_more_series_list_item, getApplicationContext()));

                                                        }

                                                        @Override
                                                        public void onFailure(Call<SeriesItem> call, Throwable t) {
                                                            Toast.makeText(getApplicationContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                                                            call.cancel();
                                                        }
                                                    });
                                                    refLayout.setRefreshing(false);
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
        SeriesMainActivity.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);

    }


}