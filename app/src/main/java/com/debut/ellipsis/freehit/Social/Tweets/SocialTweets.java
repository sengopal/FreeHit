package com.debut.ellipsis.freehit.Social.Tweets;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.debut.ellipsis.freehit.R;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TimelineResult;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SocialTweets extends Fragment {
    public SearchTimeline searchTimeline;
    public TweetTimelineRecyclerViewAdapter adapter;
    public RecyclerView rv;
    private ProgressBar mProgressBar;
    public Button NoConnectionButton;

    public SocialTweets() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View socTweets = inflater.inflate(R.layout.fragment_social_tweets, container, false);

        View viewRecycler = socTweets.findViewById(R.id.tweets_recycler_layout);


        final SwipeRefreshLayout refLayout = (SwipeRefreshLayout) viewRecycler.findViewById(R.id.refresh_layout);


        //  Initializing the RecyclerView for Twitter feed
        rv = (RecyclerView) viewRecycler.findViewById(R.id.recycler_list);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        View viewProgress = socTweets.findViewById(R.id.progress);

        mProgressBar = (ProgressBar) viewProgress.findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);

        final View no_internet_connection = socTweets.findViewById(R.id.Unavailable_connection);

        NoConnectionButton = (Button) no_internet_connection.findViewById(R.id.no_internet_refresh_button);


        final RelativeLayout twitrel = (RelativeLayout) socTweets.findViewById(R.id.twit_layout);
        //  Using a SearchTimeline to search for a given query, can be changed with (UserTimeline, CollectionTimeline, TwitterListTimeline, or FixedTweetTimeline)
        //  Defining a recyclerView adapter for the given Timeline, Twitter builds all the icons and intents and all that shit. I love twitter.


        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) twitrel.getLayoutParams();
                params.height = (int) v.getX();
                params.width = (int) v.getY();
                twitrel.setLayoutParams(params);
            }
        });

        refLayout.setColorSchemeResources(R.color.orange);
        refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refLayout.setRefreshing(true);
                adapter.refresh(new Callback<TimelineResult<Tweet>>() {
                    @Override
                    public void success(Result<TimelineResult<Tweet>> result) {
                        no_internet_connection.setVisibility(View.INVISIBLE);
                        mProgressBar.setVisibility(View.INVISIBLE);
                        refLayout.setRefreshing(false);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                        refLayout.setRefreshing(false);
                    }
                });
            }
        });


        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            mProgressBar.setVisibility(View.INVISIBLE);
            no_internet_connection.setVisibility(View.INVISIBLE);
            String queryToSearch = "#cricket";
            tabCall(queryToSearch, SearchTimeline.ResultType.RECENT);

        } else {
            no_internet_connection.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.INVISIBLE);
            NoConnectionButton.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                   /* Intent i = new Intent(getContext(), MainActivity.class);//which is your mainActivity-Launcher
                    i.putExtra("Main_tab",2);
                    i.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);*/
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.detach(SocialTweets.this).attach(SocialTweets.this).commit();

                }
            });
        }


        return socTweets;
    }

    // Simple function to set the adapter to the required search query and ResultType (RECENT,POPULAR,MIXED)
    public void tabCall(String query, SearchTimeline.ResultType type) {
        searchTimeline = new SearchTimeline.Builder().query(query).resultType(type).build();
        adapter = new TweetTimelineRecyclerViewAdapter.Builder(getContext()).setTimeline(searchTimeline).build();
        mProgressBar.setVisibility(View.INVISIBLE);
        rv.setAdapter(adapter);

    }
}