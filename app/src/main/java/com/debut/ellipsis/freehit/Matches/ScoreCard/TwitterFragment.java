package com.debut.ellipsis.freehit.Matches.ScoreCard;


import android.content.Context;
import android.content.Intent;
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
    public SearchTimeline searchTimeline;
    public TweetTimelineRecyclerViewAdapter adapter;
    public RecyclerView rv;
    private ProgressBar mProgressBar;
    public TextView NotweetsText;
    public Button NotweetsButton;

    public TwitterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View socTweets = inflater.inflate(R.layout.fragment_social_tweets, container, false);

        View viewRecycler = (View) socTweets.findViewById(R.id.tweets_recycler_layout);

        final SwipeRefreshLayout refLayout = (SwipeRefreshLayout) viewRecycler.findViewById(R.id.refresh_layout);

        View viewMatchesTweetsTab = (View) socTweets.findViewById(R.id.soc_tabs);
        socTabs = (TabLayout) viewMatchesTweetsTab.findViewById(R.id.tabs);
        socTabs.setVisibility(View.GONE);

        View viewProgress = (View) socTweets.findViewById(R.id.progress);

        mProgressBar = (ProgressBar) viewProgress.findViewById(R.id.progress_bar);

        Intent i = getActivity().getIntent();
        String Team1Name = i.getStringExtra("Team1Name");
        String Team2Name = i.getStringExtra("Team2Name");
        CountryHash countryHash = new CountryHash();


        final String QueryToSearch1 = "#" + Team1Name + "vs" + Team2Name;
        final String QueryToSearch2 = "#" + Team2Name + "vs" + Team1Name;

        //  Initializing the RecyclerView for Twitter feed
        rv = (RecyclerView) viewRecycler.findViewById(R.id.recycler_list);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

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
                        Toast.makeText(getContext(),R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                        refLayout.setRefreshing(false);
                    }
                });
            }
        });

        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            No_tweets.setVisibility(View.INVISIBLE);
            tabCall(QueryToSearch1 + "," + QueryToSearch2, SearchTimeline.ResultType.RECENT);

        } else {
            No_tweets.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.INVISIBLE);
            NotweetsButton.setVisibility(View.INVISIBLE);
            NotweetsText.setText(R.string.EmptyNews);
            NotweetsText.setText(R.string.no_internet_connection);
            tabCall(QueryToSearch1 + "," + QueryToSearch2, SearchTimeline.ResultType.RECENT);

        }

        return socTweets;
    }


    // Simple function to set the adapter to the required search query and ResultType (RECENT,POPULAR,MIXED)
    private void tabCall(String query, SearchTimeline.ResultType type) {
        searchTimeline = new SearchTimeline.Builder().query(query).resultType(type).build();
        adapter = new TweetTimelineRecyclerViewAdapter.Builder(getContext()).setTimeline(searchTimeline).build();
        rv.setAdapter(adapter);
        mProgressBar.setVisibility(View.INVISIBLE);
    }
}
