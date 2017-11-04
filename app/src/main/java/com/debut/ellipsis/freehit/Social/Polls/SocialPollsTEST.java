package com.debut.ellipsis.freehit.Social.Polls;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SocialPollsTEST extends Fragment {

    private ProgressBar mProgressBar;
    public TextView NoPollsText;
    public Button NoPollsButton;
    public Button NoConnectionButton;
    private FloatingActionButton fab;
    PollsItemAdapterTEST mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_social_polls, container, false);

        MainActivity.apiInterface = ApiClient.getClient().create(APIInterface.class);

        View viewFAB = rootView.findViewById(R.id.fab);
        fab = viewFAB.findViewById(R.id.common_fab);

        fab.setImageResource(R.drawable.arrow_down);
        fab.setVisibility(View.INVISIBLE);

        final ListView listView = rootView.findViewById(R.id.list);

        View viewProgress = rootView.findViewById(R.id.progress);
        mProgressBar = viewProgress.findViewById(R.id.progress_bar);

        final SwipeRefreshLayout refLayout = rootView.findViewById(R.id.refresh_layout);

        final View No_polls = rootView.findViewById(R.id.No_news);

        NoPollsText = No_polls.findViewById(R.id.empty_view);
        NoPollsButton = No_polls.findViewById(R.id.No_Live_Matches_button);

        final View no_internet_connection = rootView.findViewById(R.id.Unavailable_connection);

        NoConnectionButton = no_internet_connection.findViewById(R.id.no_internet_refresh_button);

        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_YES:
                listView.setBackgroundColor(getResources().getColor(R.color.night_background));
                fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dark)));
                NoPollsText.setTextColor(Color.WHITE);
                NoPollsButton.setBackgroundResource(R.drawable.button_shape_dark);
                NoPollsButton.setTextColor(Color.BLACK);
                refLayout.setColorSchemeColors(Color.BLACK);
                break;
            default:
                refLayout.setColorSchemeResources(R.color.orange);
                break;
        }


        Call<PollCardItem> call = MainActivity.apiInterface.doGetPollsListResources();
        call.enqueue(new Callback<PollCardItem>() {
            @Override
            public void onResponse(Call<PollCardItem> call, Response<PollCardItem> response) {
                mProgressBar.setVisibility(View.INVISIBLE);

                if (getActivity() != null) {
                    List<PollCardItem> polls = response.body().getResults();
                    if (polls.size() == 0) {
                        No_polls.setVisibility(View.VISIBLE);
                        NoPollsText.setText(R.string.EmptyPolls);
                        NoPollsButton.setOnClickListener(new View.OnClickListener() {

                            public void onClick(View v) {
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.detach(SocialPollsTEST.this).attach(SocialPollsTEST.this).commit();
                            }
                        });
                    } else {
                        mAdapter = new PollsItemAdapterTEST(getContext(), polls);
                        listView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onFailure(Call<PollCardItem> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                no_internet_connection.setVisibility(View.VISIBLE);
                NoConnectionButton.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(SocialPollsTEST.this).attach(SocialPollsTEST.this).commit();

                    }
                });
                call.cancel();
            }
        });

        refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refLayout.setRefreshing(true);

                Call<PollCardItem> call = MainActivity.apiInterface.doGetPollsListResources();
                call.enqueue(new Callback<PollCardItem>() {
                    @Override
                    public void onResponse(Call<PollCardItem> call, Response<PollCardItem> response) {
                        mProgressBar.setVisibility(View.INVISIBLE);
                        if (getActivity() != null) {
                            List<PollCardItem> polls = response.body().getResults();

                            if (polls.size() == 0) {
                                No_polls.setVisibility(View.VISIBLE);
                                NoPollsText.setText(R.string.EmptyPolls);
                                NoPollsButton.setOnClickListener(new View.OnClickListener() {

                                    public void onClick(View v) {
                                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                                        ft.detach(SocialPollsTEST.this).attach(SocialPollsTEST.this).commit();
                                    }
                                });

                            } else {
                                mAdapter = new PollsItemAdapterTEST(getContext(), polls);
                                listView.setAdapter(mAdapter);
                                mAdapter.notifyDataSetChanged();

                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<PollCardItem> call, Throwable t) {
                        mProgressBar.setVisibility(View.GONE);
                        no_internet_connection.setVisibility(View.VISIBLE);
                        NoConnectionButton.setOnClickListener(new View.OnClickListener() {

                            public void onClick(View v) {
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.detach(SocialPollsTEST.this).attach(SocialPollsTEST.this).commit();

                            }
                        });
                        call.cancel();
                    }
                });
                refLayout.setRefreshing(false);
            }
        });

        return rootView;
    }

}