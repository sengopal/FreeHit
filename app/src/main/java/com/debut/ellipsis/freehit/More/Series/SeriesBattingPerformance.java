package com.debut.ellipsis.freehit.More.Series;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesBattingPerformance extends Fragment {
    String id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        id=getArguments().getString("id");

        View rootView = inflater.inflate(R.layout.series_perfo_list_view, container, false);
      /*  View viewRecycler = rootView.findViewById(R.id.series_list);*/

        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
/*
        final SwipeRefreshLayout refLayout = (SwipeRefreshLayout) viewRecycler.findViewById(R.id.refresh_layout);*/
/*
        View vProgress = rootView.findViewById(R.id.progress);
        final ProgressBar mProgressbar = (ProgressBar) vProgress.findViewById(R.id.progress_bar);*/
        Call<PerformanceItem> seriesInfo = MainActivity.apiInterface.doGetSeriesPerformance("1");
        seriesInfo.enqueue(new Callback<PerformanceItem>() {
            @Override
            public void onResponse(Call<PerformanceItem> call, Response<PerformanceItem> response) {
                /*mProgressbar.setVisibility(View.GONE);*/
                List<PerformanceItem> seriesInfo = response.body().getResults();
                recyclerView.setAdapter(new SeriesPerformanceAdapter(seriesInfo, R.layout.series_perfo_item, getContext()));


            }

            @Override
            public void onFailure(Call<PerformanceItem> call, Throwable t) {
                Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                call.cancel();

            }
        });

        /*refLayout.setColorSchemeResources(R.color.orange);
        refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                           @Override
                                           public void onRefresh() {
                                               // Checking if connected or not on refresh
                                               refLayout.setRefreshing(true);

                                               Call<PerformanceItem> seriesInfo = MainActivity.apiInterface.doGetSeriesPerformance(id);
                                               seriesInfo.enqueue(new Callback<com.debut.ellipsis.freehit.More.Series.PerformanceItem>() {
                                                   @Override
                                                   public void onResponse(Call<PerformanceItem> call, Response<PerformanceItem> response) {
                                                       mProgressbar.setVisibility(View.GONE);
                                                       List<PerformanceItem> seriesInfo = response.body().getResults();
                                                       recyclerView.setAdapter(new SeriesPerformanceAdapter(seriesInfo, R.layout.fragment_more_series_list_item, getContext()));

                                                   }

                                                   @Override
                                                   public void onFailure(Call<PerformanceItem> call, Throwable t) {
                                                       Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                                                       call.cancel();
                                                   }
                                               });
                                               refLayout.setRefreshing(false);
                                           }
                                       }
        );*/

return rootView;
    }

}

