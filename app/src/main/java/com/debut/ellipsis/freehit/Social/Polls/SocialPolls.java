package com.debut.ellipsis.freehit.Social.Polls;


import android.graphics.Color;
import android.os.Bundle;
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
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SocialPolls extends Fragment {

    private ProgressBar mProgressBar;
    public TextView NoPollsText;
    public Button NoPollsButton;
    public Button NoConnectionButton;
    public TextView NoConnection;
    PollsItemAdapter mAdapter;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_social_polls, container, false);

        MainActivity.apiInterface = ApiClient.getClient().create(APIInterface.class);

        listView = rootView.findViewById(R.id.list);

        View viewProgress = rootView.findViewById(R.id.progress);
        mProgressBar = viewProgress.findViewById(R.id.progress_bar);

        final SwipeRefreshLayout refLayout = rootView.findViewById(R.id.refresh_layout);

        final View No_polls = rootView.findViewById(R.id.No_news);

        NoPollsText = No_polls.findViewById(R.id.empty_view);
        NoPollsButton = No_polls.findViewById(R.id.No_Live_Matches_button);

        final View no_internet_connection = rootView.findViewById(R.id.Unavailable_connection);

        NoConnectionButton = no_internet_connection.findViewById(R.id.no_internet_refresh_button);

        NoConnection = no_internet_connection.findViewById(R.id.no_internet_connection_text);

        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_YES:
                listView.setBackgroundColor(getResources().getColor(R.color.night_background));
                NoPollsText.setTextColor(Color.WHITE);
                NoPollsButton.setBackgroundResource(R.drawable.button_shape_dark);
                NoPollsButton.setTextColor(Color.BLACK);
                refLayout.setColorSchemeColors(Color.BLACK);
                NoConnectionButton.setTextColor(Color.BLACK);
                NoConnection.setTextColor(Color.WHITE);
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
                                ft.detach(SocialPolls.this).attach(SocialPolls.this).commit();
                            }
                        });
                    } else {
                        mAdapter = new PollsItemAdapter(getContext(), polls);
                        listView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onFailure(Call<PollCardItem> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                listView.setAdapter(null);
                no_internet_connection.setVisibility(View.VISIBLE);
                NoConnectionButton.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(SocialPolls.this).attach(SocialPolls.this).commit();

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
                                        ft.detach(SocialPolls.this).attach(SocialPolls.this).commit();
                                    }
                                });

                            } else {
                                mAdapter = new PollsItemAdapter(getContext(), polls);
                                listView.setAdapter(mAdapter);
                                mAdapter.notifyDataSetChanged();

                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<PollCardItem> call, Throwable t) {
                        mProgressBar.setVisibility(View.GONE);
                        Toast toast = Toast.makeText(getContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT);
                        toast.show();
                        call.cancel();
                    }
                });
                refLayout.setRefreshing(false);
            }
        });

        return rootView;
    }

}