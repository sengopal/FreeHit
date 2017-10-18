package com.debut.ellipsis.freehit.Social.Tweets;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
    public TabLayout socTabs;
    public SearchTimeline searchTimeline;
    public TweetTimelineRecyclerViewAdapter adapter;
    private String QueryToSearch = "#cricket";
    public RecyclerView rv;
    private ProgressBar mProgressBar;
    public TextView NotweetsText;
    public Button NotweetsButton;

    public SocialTweets() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View socTweets = inflater.inflate(R.layout.fragment_social_tweets, container, false);

        View viewRecycler = (View) socTweets.findViewById(R.id.tweets_recycler_layout);


        final SwipeRefreshLayout refLayout = (SwipeRefreshLayout) viewRecycler.findViewById(R.id.refresh_layout);

        View viewSocialTweetsTabs = (View) socTweets.findViewById(R.id.soc_tabs);
        socTabs = (TabLayout) viewSocialTweetsTabs.findViewById(R.id.tabs);
        setupTabs();


        //  Initializing the RecyclerView for Twitter feed
        rv = (RecyclerView) viewRecycler.findViewById(R.id.recycler_list);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        View viewProgress = (View) socTweets.findViewById(R.id.progress);

        mProgressBar = (ProgressBar) viewProgress.findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);

        final View No_tweets = socTweets.findViewById(R.id.No_tweets);

        NotweetsText = (TextView) No_tweets.findViewById(R.id.empty_view);
        NotweetsButton = (Button) No_tweets.findViewById(R.id.No_Live_Matches_button);


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
                        No_tweets.setVisibility(View.INVISIBLE);
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
            No_tweets.setVisibility(View.INVISIBLE);
            socTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    switch (tab.getPosition()) {
                        case 0:
                            tabCall(QueryToSearch, SearchTimeline.ResultType.POPULAR);
                            break;
                        case 1:
                            tabCall(QueryToSearch, SearchTimeline.ResultType.RECENT);
                            break;
                        default:

                            break;
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                // Refreshes the feed if a tab is reselected (quality of life)
                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                    refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            refLayout.setRefreshing(true);
                            adapter.refresh(new Callback<TimelineResult<Tweet>>() {
                                @Override
                                public void success(Result<TimelineResult<Tweet>> result) {
                                    mProgressBar.setVisibility(View.INVISIBLE);
                                    refLayout.setRefreshing(false);
                                }

                                @Override
                                public void failure(TwitterException exception) {
                                    Toast.makeText(getContext(), "Fetching Twitter Feed failed.", Toast.LENGTH_SHORT).show();
                                    refLayout.setRefreshing(false);
                                }
                            });
                        }
                    });
                }
            });
        } else {
            No_tweets.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.INVISIBLE);
            NotweetsButton.setVisibility(View.INVISIBLE);
            NotweetsText.setText(R.string.EmptyNews);
            NotweetsText.setText(R.string.no_internet_connection);
            socTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    switch (tab.getPosition()) {
                        case 0:
                            tabCall(QueryToSearch, SearchTimeline.ResultType.POPULAR);
                            break;
                        case 1:
                            tabCall(QueryToSearch, SearchTimeline.ResultType.RECENT);
                            break;
                        default:

                            break;
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
        }


        return socTweets;
    }
    // Function to add tabs, maintaining consistency in program.

    private void setupTabs() {
        socTabs.addTab(socTabs.newTab().setText("TOP"));
        socTabs.addTab(socTabs.newTab().setText("LATEST"));
    }

    // Simple function to set the adapter to the required search query and ResultType (RECENT,POPULAR,MIXED)
    public void tabCall(String query, SearchTimeline.ResultType type) {
        searchTimeline = new SearchTimeline.Builder().query(query).resultType(type).build();
        adapter = new TweetTimelineRecyclerViewAdapter.Builder(getContext()).setTimeline(searchTimeline).build();
        mProgressBar.setVisibility(View.INVISIBLE);
        rv.setAdapter(adapter);

    }
}