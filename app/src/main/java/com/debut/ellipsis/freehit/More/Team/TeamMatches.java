package com.debut.ellipsis.freehit.More.Team;

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
import com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchCardItem;
import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchesListAdapter;
import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchCardItem;
import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchListAdapter;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamMatches extends Fragment {

    private UpcomingMatchListAdapter UpcomingMatchListAdapter;
    private PastMatchesListAdapter PastMatchListAdapter;
    private ProgressBar mProgressBar;
    private RecyclerView rv;
    public SwipeRefreshLayout refresh_layout;
    public TextView mEmptyView;
    private FloatingActionButton fab;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_more_team_complete_match_list, container, false);
        String fragment_name = getArguments().getString("fragment_name");

        View viewFAB = rootView.findViewById(R.id.fab);
        fab = (FloatingActionButton) viewFAB.findViewById(R.id.common_fab);
        fab.hide();
        fab.setImageResource(R.drawable.arrow_up);

        Intent i = getActivity().getIntent();
        TeamActivity.Team = i.getIntExtra("CountryName", 0);
        TeamActivity.favTeam = i.getStringExtra("fav_country");


        if (TeamActivity.Team == 0) {
            TeamActivity.tempTeamName = TeamActivity.favTeam;
        } else {
            TeamActivity.tempTeamName = this.getContext().getString(TeamActivity.Team);
        }


        TeamActivity.tempTeamName = WelcomeActivity.countryHash.getCountrySN(TeamActivity.tempTeamName.toUpperCase());

        MainActivity.apiInterface = ApiClient.getClient().create(APIInterface.class);

        View viewProgress = rootView.findViewById(R.id.progress);
        mProgressBar = (ProgressBar) viewProgress.findViewById(R.id.progress_bar);

        View viewRecycler = rootView.findViewById(R.id.complete_match_list);
        rv = (RecyclerView) viewRecycler.findViewById(R.id.recycler_list);

        mLinearLayoutManager = new LinearLayoutManager(getContext());

        rv.setLayoutManager(mLinearLayoutManager);

        refresh_layout = (SwipeRefreshLayout) viewRecycler.findViewById(R.id.refresh_layout);

        View viewEmpty = rootView.findViewById(R.id.empty);
        mEmptyView = (TextView) viewEmpty.findViewById(R.id.empty_view);

        if (fragment_name.equals("UPCOMING")) {
            Call<UpcomingMatchCardItem> call = MainActivity.apiInterface.doGetUpcomingFavTeam(TeamActivity.tempTeamName);
            call.enqueue(new Callback<UpcomingMatchCardItem>() {
                @Override
                public void onResponse(Call<UpcomingMatchCardItem> call, Response<UpcomingMatchCardItem> response) {

                    List<UpcomingMatchCardItem> upcomingMatchesList = response.body().getResults();
                    mProgressBar.setVisibility(View.GONE);
                    if (upcomingMatchesList.size() == 0) {
                        mEmptyView.setVisibility(View.VISIBLE);
                        mEmptyView.setText(R.string.EmptyMatches);
                    }

                    UpcomingMatchListAdapter = new UpcomingMatchListAdapter(getContext(), upcomingMatchesList);
                    rv.setAdapter(UpcomingMatchListAdapter);
                }

                @Override
                public void onFailure(Call<UpcomingMatchCardItem> call, Throwable t) {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    mEmptyView.setVisibility(View.INVISIBLE);
                    Toast toast = Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
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

                                                        Call<UpcomingMatchCardItem> call = MainActivity.apiInterface.doGetUpcomingFavTeam(TeamActivity.tempTeamName);
                                                        call.enqueue(new Callback<UpcomingMatchCardItem>() {
                                                            @Override
                                                            public void onResponse(Call<UpcomingMatchCardItem> call, Response<UpcomingMatchCardItem> response) {
                                                                mProgressBar.setVisibility(View.GONE);

                                                                List<UpcomingMatchCardItem> upcomingMatchesList = response.body().getResults();
                                                                if (upcomingMatchesList.size() == 0) {
                                                                    mEmptyView.setVisibility(View.VISIBLE);
                                                                    mEmptyView.setText(R.string.EmptyMatches);
                                                                }
                                                                UpcomingMatchListAdapter = new UpcomingMatchListAdapter(getContext(), upcomingMatchesList);
                                                                rv.setAdapter(UpcomingMatchListAdapter);
                                                            }

                                                            @Override
                                                            public void onFailure(Call<UpcomingMatchCardItem> call, Throwable t) {
                                                                mProgressBar.setVisibility(View.INVISIBLE);
                                                                mEmptyView.setVisibility(View.INVISIBLE);
                                                                Toast toast = Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                                                                toast.show();
                                                                call.cancel();

                                                            }
                                                        });
                                                        refresh_layout.setRefreshing(false);
                                                    }
                                                }
            );
        }

        if(fragment_name.equals("PAST"))
        {
            Call<PastMatchCardItem> call = MainActivity.apiInterface.doGetPastFavTeam(TeamActivity.tempTeamName);
            call.enqueue(new Callback<PastMatchCardItem>() {
                @Override
                public void onResponse(Call<PastMatchCardItem> call, Response<PastMatchCardItem> response) {

                    List<PastMatchCardItem> pastMatchesList = response.body().getResults();
                    mProgressBar.setVisibility(View.GONE);
                    if (pastMatchesList.size() == 0) {
                        mEmptyView.setText(R.string.EmptyMatches);
                        mEmptyView.setVisibility(View.VISIBLE);
                    }

                    PastMatchListAdapter = new PastMatchesListAdapter(pastMatchesList, getContext());
                    rv.setAdapter(PastMatchListAdapter);
                }

                @Override
                public void onFailure(Call<PastMatchCardItem> call, Throwable t) {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    mEmptyView.setVisibility(View.INVISIBLE);
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

                                                        Call<PastMatchCardItem> call = MainActivity.apiInterface.doGetPastFavTeam(TeamActivity.tempTeamName);
                                                        call.enqueue(new Callback<PastMatchCardItem>() {
                                                            @Override
                                                            public void onResponse(Call<PastMatchCardItem> call, Response<PastMatchCardItem> response) {
                                                                mProgressBar.setVisibility(View.GONE);

                                                                List<PastMatchCardItem> pastMatchcardItems = response.body().getResults();
                                                                if(pastMatchcardItems.size()==0)
                                                                {
                                                                    mEmptyView.setVisibility(View.VISIBLE);
                                                                    mEmptyView.setText(R.string.EmptyMatches);
                                                                }

                                                                PastMatchListAdapter = new PastMatchesListAdapter(pastMatchcardItems, getContext());
                                                                rv.setAdapter(PastMatchListAdapter);
                                                            }

                                                            @Override
                                                            public void onFailure(Call<PastMatchCardItem> call, Throwable t) {
                                                                mProgressBar.setVisibility(View.INVISIBLE);
                                                                mEmptyView.setVisibility(View.INVISIBLE);
                                                                Toast toast=Toast.makeText(getContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT);
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


        return rootView;
    }
}
