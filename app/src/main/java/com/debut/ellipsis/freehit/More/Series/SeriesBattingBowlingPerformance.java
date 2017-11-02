package com.debut.ellipsis.freehit.More.Series;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesBattingBowlingPerformance extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final String id = getActivity().getIntent().getStringExtra("id");

        String fragment_name = getArguments().getString("sub_fragment_name");

        View rootView = inflater.inflate(R.layout.fragment_more_series_performance_list, container, false);

        View viewRecycler = rootView.findViewById(R.id.series_performance_list);

        final RecyclerView recyclerView = viewRecycler.findViewById(R.id.recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        TextView sl_no = rootView.findViewById(R.id.sl_no);
        TextView name = rootView.findViewById(R.id.name);
        TextView Runs_Wickets_Label= rootView.findViewById(R.id.Runs_Wickets_Label);
        TextView Average_balls_Label = rootView.findViewById(R.id.Average_balls_Label);

        LinearLayout LabelStrip = rootView.findViewById(R.id.parent_layout);

        final SwipeRefreshLayout refLayout = viewRecycler.findViewById(R.id.refresh_layout);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            recyclerView.setBackgroundColor(getResources().getColor(R.color.night_background));
            LabelStrip.setBackgroundColor(getResources().getColor(R.color.ranking_layout));
            sl_no.setTextColor(Color.WHITE);
            name.setTextColor(Color.WHITE);
            Runs_Wickets_Label.setTextColor(Color.WHITE);
            Average_balls_Label.setTextColor(Color.WHITE);
            refLayout.setColorSchemeColors(Color.BLACK);
        }
        else
        {
            refLayout.setColorSchemeResources(R.color.orange);
        }

        View vProgress = rootView.findViewById(R.id.progress);
        final ProgressBar mProgressbar = vProgress.findViewById(R.id.progress_bar);



        switch (fragment_name) {
            case "BATTING": {
                Call<PerformanceItem> seriesInfo = MainActivity.apiInterface.doGetSeriesPerformance(id);
                seriesInfo.enqueue(new Callback<PerformanceItem>() {
                    @Override
                    public void onResponse(Call<PerformanceItem> call, Response<PerformanceItem> response) {
                        mProgressbar.setVisibility(View.GONE);
                        List<PerformanceItem> seriesInfo = response.body().getResults();
                        recyclerView.setAdapter(new SeriesBattingAdapter(seriesInfo, R.layout.fragment_more_series_performance_list_item, getContext()));


                    }

                    @Override
                    public void onFailure(Call<PerformanceItem> call, Throwable t) {
                        call.cancel();

                    }
                });

                refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                                   @Override
                                                   public void onRefresh() {
                                                       // Checking if connected or not on refresh
                                                       refLayout.setRefreshing(true);

                                                       Call<PerformanceItem> seriesInfo = MainActivity.apiInterface.doGetSeriesPerformance(id);
                                                       seriesInfo.enqueue(new Callback<PerformanceItem>() {
                                                           @Override
                                                           public void onResponse(Call<PerformanceItem> call, Response<PerformanceItem> response) {
                                                               mProgressbar.setVisibility(View.GONE);
                                                               List<PerformanceItem> seriesInfo = response.body().getResults();
                                                               recyclerView.setAdapter(new SeriesBattingAdapter(seriesInfo, R.layout.fragment_more_series_performance_list_item, getContext()));

                                                           }

                                                           @Override
                                                           public void onFailure(Call<PerformanceItem> call, Throwable t) {
                                                               call.cancel();
                                                           }
                                                       });
                                                       refLayout.setRefreshing(false);
                                                   }
                                               }
                );
                break;
            }
            case "BOWLING": {

                Runs_Wickets_Label.setText("WIckets");

                Average_balls_Label.setText("Balls");

                Call<PerformanceItem> seriesInfo = MainActivity.apiInterface.doGetSeriesPerformance(id);
                seriesInfo.enqueue(new Callback<PerformanceItem>() {
                    @Override
                    public void onResponse(Call<PerformanceItem> call, Response<PerformanceItem> response) {
                        mProgressbar.setVisibility(View.GONE);
                        List<PerformanceItem> seriesInfo = response.body().getResults();
                        recyclerView.setAdapter(new SeriesBowlingAdapter(seriesInfo, R.layout.fragment_more_series_performance_list_item, getContext()));


                    }

                    @Override
                    public void onFailure(Call<PerformanceItem> call, Throwable t) {
                        Toast.makeText(getActivity(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                        call.cancel();

                    }
                });

                refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                                   @Override
                                                   public void onRefresh() {
                                                       // Checking if connected or not on refresh
                                                       refLayout.setRefreshing(true);

                                                       Call<PerformanceItem> seriesInfo = MainActivity.apiInterface.doGetSeriesPerformance(id);
                                                       seriesInfo.enqueue(new Callback<PerformanceItem>() {
                                                           @Override
                                                           public void onResponse(Call<PerformanceItem> call, Response<PerformanceItem> response) {
                                                               mProgressbar.setVisibility(View.GONE);
                                                               List<PerformanceItem> seriesInfo = response.body().getResults();
                                                               recyclerView.setAdapter(new SeriesBowlingAdapter(seriesInfo, R.layout.fragment_more_series_performance_list_item, getContext()));

                                                           }

                                                           @Override
                                                           public void onFailure(Call<PerformanceItem> call, Throwable t) {
                                                               Toast.makeText(getActivity(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                                                               call.cancel();
                                                           }
                                                       });
                                                       refLayout.setRefreshing(false);
                                                   }
                                               }
                );
                break;
            }
        }


        return rootView;
    }

}

