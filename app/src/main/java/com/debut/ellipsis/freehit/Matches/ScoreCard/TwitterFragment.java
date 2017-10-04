package com.debut.ellipsis.freehit.Matches.ScoreCard;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.debut.ellipsis.freehit.CountryHash;
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
public class TwitterFragment extends Fragment {
    public TabLayout socTabs;
    private String Team1SN;
    private String Team2SN;
    public SearchTimeline searchTimeline;
    public TweetTimelineRecyclerViewAdapter adapter;
    public RecyclerView rv;

    public TwitterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View socTweets = inflater.inflate(R.layout.fragment_social_tweets, container, false);
        final SwipeRefreshLayout refLayout = (SwipeRefreshLayout) socTweets.findViewById(R.id.soc_refresh_layout);
        socTabs = (TabLayout) socTweets.findViewById(R.id.soc_tabs);
        setupTabs();

        Intent i = getActivity().getIntent();
        String Team1Name=i.getStringExtra("Team1Name");
        String Team2Name=i.getStringExtra("Team2Name");
        CountryHash countryHash = new CountryHash();
        Team1SN = countryHash.getCountrySN(Team1Name);
        Team2SN = countryHash.getCountrySN(Team2Name);

        final String QueryToSearch1 = "#"+Team1SN+"vs"+Team2SN;
        final String QueryToSearch2 = "#"+Team2SN+"vs"+Team1SN;

        //  Initializing the RecyclerView for Twitter feed
        rv = (RecyclerView) socTweets.findViewById(R.id.twit_feed);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        final RelativeLayout twitrel = (RelativeLayout) socTweets.findViewById(R.id.twit_layout);
        //  Using a SearchTimeline to search for a given query, can be changed with (UserTimeline, CollectionTimeline, TwitterListTimeline, or FixedTweetTimeline)
        //  Defining a recyclerView adapter for the given Timeline, Twitter builds all the icons and intents and all that shit. I love twitter.
        tabCall(QueryToSearch1+","+QueryToSearch2, SearchTimeline.ResultType.RECENT);

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) twitrel.getLayoutParams();
                params.height = (int) v.getX();
                params.width = (int) v.getY();
                twitrel.setLayoutParams(params);
            }
        });


        refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refLayout.setRefreshing(true);
                adapter.refresh(new Callback<TimelineResult<Tweet>>() {
                    @Override
                    public void success(Result<TimelineResult<Tweet>> result) {
                        refLayout.setRefreshing(false);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Toast.makeText(getContext(), "Fetching Twitter Feed failed.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        socTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tabCall(QueryToSearch1+","+QueryToSearch2, SearchTimeline.ResultType.RECENT);
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
                                refLayout.setRefreshing(false);
                            }

                            @Override
                            public void failure(TwitterException exception) {
                                Toast.makeText(getContext(), "Fetching Twitter Feed failed.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
        setupTabIcons();
        return socTweets;
    }
    // Function to add tabs, maintaining consistancy in program.

    private void setupTabs() {
        socTabs.addTab(socTabs.newTab());
    }

    private void setupTabIcons() {
        socTabs.getTabAt(0).setIcon(R.drawable.twitter);
        socTabs.getTabAt(0).getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
    }

    // Simple function to set the adapter to the required search query and ResultType (RECENT,POPULAR,MIXED)
    private void tabCall(String query, SearchTimeline.ResultType type) {
        searchTimeline = new SearchTimeline.Builder().query(query).resultType(type).build();
        adapter = new TweetTimelineRecyclerViewAdapter.Builder(getContext()).setTimeline(searchTimeline).build();
        rv.setAdapter(adapter);
    }
}
