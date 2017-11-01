package com.debut.ellipsis.freehit.More.Series;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesMainActivity extends AppCompatActivity {

    private ProgressBar mProgressbar;

    public SeriesMainActivity() {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            recyclerView.setBackgroundColor(getResources().getColor(R.color.night_background));
            toolbar.setBackgroundColor(getResources().getColor(R.color.dark));
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }

        final SwipeRefreshLayout refLayout = (SwipeRefreshLayout) viewRecycler.findViewById(R.id.refresh_layout);

        View ProgressView = findViewById(R.id.progress);
        mProgressbar = (ProgressBar) ProgressView.findViewById(R.id.progress_bar);

        MainActivity.apiInterface = ApiClient.getClient().create(APIInterface.class);

        Call<SeriesItem> seriesInfo = MainActivity.apiInterface.doGetSeries();
        seriesInfo.enqueue(new Callback<com.debut.ellipsis.freehit.More.Series.SeriesItem>() {
            @Override
            public void onResponse(Call<SeriesItem> call, Response<SeriesItem> response) {
                mProgressbar.setVisibility(View.INVISIBLE);
                List<SeriesItem> seriesInfo = response.body().getResults();
                recyclerView.setAdapter(new SeriesItemAdapter(seriesInfo, R.layout.fragment_more_series_list_item, getApplicationContext()));

            }

            @Override
            public void onFailure(Call<SeriesItem> call, Throwable t) {
                mProgressbar.setVisibility(View.INVISIBLE);
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

                                               Call<SeriesItem> seriesInfo = MainActivity.apiInterface.doGetSeries();
                                               seriesInfo.enqueue(new Callback<com.debut.ellipsis.freehit.More.Series.SeriesItem>() {
                                                   @Override
                                                   public void onResponse(Call<SeriesItem> call, Response<SeriesItem> response) {
                                                       mProgressbar.setVisibility(View.INVISIBLE);
                                                       List<SeriesItem> seriesInfo = response.body().getResults();
                                                       recyclerView.setAdapter(new SeriesItemAdapter(seriesInfo, R.layout.fragment_more_series_list_item, getApplicationContext()));

                                                   }

                                                   @Override
                                                   public void onFailure(Call<SeriesItem> call, Throwable t) {
                                                       mProgressbar.setVisibility(View.INVISIBLE);
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