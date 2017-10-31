package com.debut.ellipsis.freehit.More.Series;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchCardItem;
import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchListAdapter;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesUpcoming extends Fragment {

    private ProgressBar mProgressBar;
    public SwipeRefreshLayout refresh_layout;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_more_team_complete_match_list, container, false);

        View viewFAB = rootView.findViewById(R.id.fab);
        FloatingActionButton fab = (FloatingActionButton) viewFAB.findViewById(R.id.common_fab);
        fab.setVisibility(View.GONE);

        Intent i = getActivity().getIntent();
        SeriesActivity.date = i.getStringExtra("date");
        SeriesActivity.Teams = i.getStringExtra("Teams");

        MainActivity.apiInterface = ApiClient.getClient().create(APIInterface.class);

        View viewProgress = rootView.findViewById(R.id.progress);
        mProgressBar = (ProgressBar) viewProgress.findViewById(R.id.progress_bar);

        View viewRecycler = rootView.findViewById(R.id.complete_match_list);
        rv = (RecyclerView) viewRecycler.findViewById(R.id.recycler_list);

        refresh_layout = (SwipeRefreshLayout) viewRecycler.findViewById(R.id.refresh_layout);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        View viewEmpty = rootView.findViewById(R.id.empty);
        final TextView emptyView = (TextView) viewEmpty.findViewById(R.id.empty_view);



        /* GET List Upcoming Matches for selected team */
        Call<UpcomingMatchCardItem> call = MainActivity.apiInterface.doGetUpComingSeriesMatches(SeriesActivity.Teams,SeriesActivity.date);
        call.enqueue(new Callback<UpcomingMatchCardItem>() {
            @Override
            public void onResponse(Call<UpcomingMatchCardItem> call, Response<UpcomingMatchCardItem> response) {

                List<UpcomingMatchCardItem> upcomingMatchesList = response.body().getResults();
                mProgressBar.setVisibility(View.GONE);
                if (upcomingMatchesList.size() == 0) {
                    emptyView.setText(R.string.EmptyMatches);
                    emptyView.setVisibility(View.VISIBLE);
                }

                rv.setAdapter(new UpcomingMatchListAdapter(getContext(), upcomingMatchesList));
            }

            @Override
            public void onFailure(Call<UpcomingMatchCardItem> call, Throwable t) {
                mProgressBar.setVisibility(View.INVISIBLE);
                emptyView.setVisibility(View.INVISIBLE);
                Toast toast=Toast.makeText(getContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT);
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

                                                    Call<UpcomingMatchCardItem> call = MainActivity.apiInterface.doGetUpComingSeriesMatches(SeriesActivity.Teams,SeriesActivity.date);
                                                    call.enqueue(new Callback<UpcomingMatchCardItem>() {
                                                        @Override
                                                        public void onResponse(Call<UpcomingMatchCardItem> call, Response<UpcomingMatchCardItem> response) {
                                                            mProgressBar.setVisibility(View.GONE);

                                                            List<UpcomingMatchCardItem> upcomingMatchesList = response.body().getResults();
                                                            if(upcomingMatchesList.size()==0)
                                                            {
                                                                emptyView.setVisibility(View.VISIBLE);
                                                                emptyView.setText(R.string.EmptyMatches);
                                                            }
                                                            rv.setAdapter(new UpcomingMatchListAdapter(getContext(),upcomingMatchesList));
                                                        }

                                                        @Override
                                                        public void onFailure(Call<UpcomingMatchCardItem> call, Throwable t) {
                                                            mProgressBar.setVisibility(View.INVISIBLE);
                                                            emptyView.setVisibility(View.INVISIBLE);
                                                            Toast toast=Toast.makeText(getContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT);
                                                            toast.show();
                                                            call.cancel();

                                                        }
                                                    });
                                                    refresh_layout.setRefreshing(false);
                                                }
                                            }
        );

        return rootView;
    }

}
