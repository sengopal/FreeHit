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
import android.widget.ImageView;
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


public class SocialPolls extends Fragment {

    private ProgressBar mProgressBar;
    public TextView NoPollsText;
    public Button NoPollsButton;
    public Button NoConnectionButton;
    public TextView NoConnection;
    PollsItemAdapter mAdapter;
    private ListView listView;
    public ImageView NoConnectionImage;
    View no_internet_connection;
    View No_polls;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_social_polls, container, false);

        MainActivity.apiInterface = ApiClient.getClient().create(APIInterface.class);

        listView = rootView.findViewById(R.id.list);

        View viewProgress = rootView.findViewById(R.id.progress);
        mProgressBar = viewProgress.findViewById(R.id.progress_bar);

        final SwipeRefreshLayout refLayout = rootView.findViewById(R.id.refresh_layout);

        No_polls = rootView.findViewById(R.id.No_news);

        NoPollsText = No_polls.findViewById(R.id.empty_view);
        NoPollsButton = No_polls.findViewById(R.id.No_Live_Matches_button);

        no_internet_connection = rootView.findViewById(R.id.Unavailable_connection);

        NoConnectionButton = no_internet_connection.findViewById(R.id.no_internet_refresh_button);

        NoConnection = no_internet_connection.findViewById(R.id.no_internet_connection_text);

        NoConnectionImage = no_internet_connection.findViewById(R.id.no_internet_connection);

        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_YES:
                listView.setBackgroundColor(getResources().getColor(R.color.night_background));
                NoPollsText.setTextColor(Color.WHITE);
                NoPollsButton.setBackgroundResource(R.drawable.button_shape_dark);
                NoPollsButton.setTextColor(Color.BLACK);
                refLayout.setColorSchemeColors(Color.BLACK);
                NoConnectionButton.setTextColor(Color.BLACK);
                NoConnection.setTextColor(Color.WHITE);
                NoConnectionImage.setColorFilter(Color.WHITE);
                break;
            default:
                refLayout.setColorSchemeResources(R.color.orange);
                break;
        }

        Polls();


        refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refLayout.setRefreshing(true);
                Polls();
                refLayout.setRefreshing(false);
            }
        });

        return rootView;
    }

    void Polls() {
        Call<PollCardItem> call = MainActivity.apiInterface.doGetPollsListResources();
        call.enqueue(new Callback<PollCardItem>() {
            @Override
            public void onResponse(Call<PollCardItem> call, Response<PollCardItem> response) {
                if (response.isSuccessful()) {
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
                } else {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    no_internet_connection.setVisibility(View.VISIBLE);
                    NoConnection.setText(R.string.server_issues);
                    NoConnectionButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.detach(SocialPolls.this).attach(SocialPolls.this).commit();
                        }
                    });
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
    }

}