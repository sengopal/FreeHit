package com.debut.ellipsis.freehit.Social.Polls;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SocialPolls extends Fragment {

    APIInterface apiInterface;
    private ProgressBar mProgressBar;
    RelativeLayout rlcontainer;

    public SocialPolls() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_list, container, false);
        View fragView = inflater.inflate(R.layout.fragment_social_polls_list_item, container, false);
        apiInterface = ApiClient.getClient().create(APIInterface.class);

        rlcontainer = (RelativeLayout) fragView.findViewById(R.id.parent_layout);

        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.news_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);

        final SwipeRefreshLayout refLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh_layout);

        final TextView emptyView = (TextView) rootView.findViewById(R.id.empty_view);

        /**
         GET Polls List Resources
         **/
        final Call<PollCardItem> call = apiInterface.doGetPollsListResources();
        call.enqueue(new Callback<PollCardItem>() {
            @Override
            public void onResponse(Call<PollCardItem> call, Response<PollCardItem> response) {
                ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(getContext().CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    emptyView.setVisibility(View.INVISIBLE);
                    if (getActivity() != null) {
                        List<PollCardItem> polls = response.body().getResults();
                        emptyView.setVisibility(View.INVISIBLE);
                        if (polls.size() == 0) {
                            emptyView.setText(R.string.EmptyPolls);
                            emptyView.setVisibility(View.VISIBLE);
                        }
                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.setAdapter(new PollItemAdapter(polls, R.layout.fragment_social_polls_list_item, getContext()));
                    }
                } else {
                    List<PollCardItem> polls = new ArrayList<PollCardItem>(1);
                    mProgressBar.setVisibility(View.INVISIBLE);
                    recyclerView.setAdapter(new PollItemAdapter(polls, R.layout.fragment_social_polls_list_item, getContext()));
                    emptyView.setText("CONNECT AND REOPEN THE APP");
                }
            }

            @Override
            public void onFailure(Call<PollCardItem> call, Throwable t) {;
                List<PollCardItem> polls = new ArrayList<PollCardItem>(1);
                recyclerView.setAdapter(new PollItemAdapter(polls, R.layout.fragment_social_polls_list_item, getContext()));
                emptyView.setText("CHECK YOUR INTERNET CONNECTION");
                mProgressBar.setVisibility(View.INVISIBLE);
                call.cancel();
            }
        });
        refLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);
        refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                           @Override
                                           public void onRefresh() {
                                               // Checking if connected or not on refresh
                                               refLayout.setRefreshing(true);
                                               ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(getContext().CONNECTIVITY_SERVICE);
                                               NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                                               if (networkInfo != null && networkInfo.isConnected()) {

                                                   Call<PollCardItem> call = apiInterface.doGetPollsListResources();
                                                   call.enqueue(new Callback<PollCardItem>() {
                                                       @Override
                                                       public void onResponse(Call<PollCardItem> call, Response<PollCardItem> response) {
                                                           mProgressBar.setVisibility(View.INVISIBLE);
                                                           emptyView.setVisibility(View.INVISIBLE);
                                                           if (getActivity() != null) {
                                                               List<PollCardItem> polls = response.body().getResults();
                                                               if (polls.size() == 0) {
                                                                   emptyView.setText(R.string.EmptyPolls);
                                                                   emptyView.setVisibility(View.VISIBLE);
                                                               }
                                                               recyclerView.setVisibility(View.VISIBLE);
                                                               recyclerView.setAdapter(new PollItemAdapter(polls, R.layout.fragment_social_polls_list_item, getContext()));
                                                           }
                                                       }


                                                       @Override
                                                       public void onFailure(Call<PollCardItem> call, Throwable t) {
                                                           mProgressBar.setVisibility(View.INVISIBLE);
                                                           recyclerView.setVisibility(View.INVISIBLE);
                                                           emptyView.setText("CHECK YOUR INTERNET CONNECTION");
                                                           call.cancel();

                                                       }
                                                   });
                                               }
                                               else
                                               {
                                                   recyclerView.setVisibility(View.INVISIBLE);
                                                   mProgressBar.setVisibility(View.INVISIBLE);
                                                   emptyView.setText("CHECK YOUR INTERNET CONNECTION");
                                                   emptyView.setVisibility(View.VISIBLE);
                                               }

                                               refLayout.setRefreshing(false);
                                           }
                                       }
        );

        return rootView;
    }

}
