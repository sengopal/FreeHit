package com.debut.ellipsis.freehit.Matches.LiveMatches;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity.MY_PREFS_NAME;


public class LiveMatchCard extends Fragment {

    ProgressBar mProgressBar;
    PageIndicatorView indicator;
    LiveMatchCardAdapter mAdapter;
    ViewPager vp;
    Button NoConnectionButton;
    TextView NoLiveMatchesText;
    TextView NoConnectionText;
    Button NoLiveMatchesButton;
    View no_internet_connection;
    View No_live_matches;
    View common_match_cards;

    public LiveMatchCard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        View rootView = null;

        SharedPreferences prefs = this.getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            rootView = inflater.inflate(R.layout.fragment_matches_common_pager_dark, container, false);
        } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            rootView = inflater.inflate(R.layout.fragment_matches_common_pager, container, false);
        }


        Boolean AutoRereshState = prefs.getBoolean("auto_refresh", false);

        View viewProgress = rootView.findViewById(R.id.progress);
        mProgressBar = viewProgress.findViewById(R.id.progress_bar);

        common_match_cards = rootView.findViewById(R.id.common_match_cards);

        View viewViewPager = common_match_cards.findViewById(R.id.match_card_viewpagegr);

        vp = viewViewPager.findViewById(R.id.viewpager);

        indicator = common_match_cards.findViewById(R.id.indicator);
        final PullRefreshLayout refreshLayout = common_match_cards.findViewById(R.id.swipeRefreshLayout);

        no_internet_connection = rootView.findViewById(R.id.Unavailable_connection);

        NoConnectionText = no_internet_connection.findViewById(R.id.no_internet_connection_text);
        NoConnectionButton = no_internet_connection.findViewById(R.id.no_internet_refresh_button);

        No_live_matches = rootView.findViewById(R.id.No_Live_Matches);

        NoLiveMatchesText = No_live_matches.findViewById(R.id.empty_view);
        NoLiveMatchesButton = No_live_matches.findViewById(R.id.No_Live_Matches_button);


        liveCall();

        if(AutoRereshState) {
            //Auto Refresh after every 15 seconds
            final Handler refreshHandler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    liveCall();
                    refreshHandler.postDelayed(this, 15 * 1000);
                }
            };
            refreshHandler.postDelayed(runnable, 15 * 1000);
        }

        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // start refresh
                refreshLayout.setRefreshing(true);
                liveCall();
                refreshLayout.setRefreshing(false);
            }

        });


        return rootView;
    }

    private void IndicatorConfig() {
        indicator.setVisibility(View.VISIBLE);
        indicator.setAnimationType(AnimationType.WORM);
        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_YES:
                indicator.setUnselectedColor(Color.parseColor("#5e5e6a"));
                indicator.setSelectedColor(Color.parseColor("#0e0d19"));
                break;
            default:
                indicator.setUnselectedColor(getResources().getColor(R.color.colorPrimaryLight));
                indicator.setSelectedColor(getResources().getColor(R.color.colorPrimary));
                break;
        }
        indicator.setInteractiveAnimation(true);
        indicator.setAnimationDuration(500);

    }

    void liveCall() {
        Call<LiveMatchCardItem> call = MainActivity.apiInterface.doGetLiveMatchResources();
        call.enqueue(new Callback<LiveMatchCardItem>() {
            @Override
            public void onResponse(Call<LiveMatchCardItem> call, Response<LiveMatchCardItem> response) {
                if (response.isSuccessful()) {
                    common_match_cards.setVisibility(View.VISIBLE);
                    no_internet_connection.setVisibility(View.INVISIBLE);
                    indicator.setVisibility(View.VISIBLE);
                    indicator.setViewPager(vp);
                    List<LiveMatchCardItem> LiveMatches = response.body().getResults();
                    mProgressBar.setVisibility(View.INVISIBLE);

                    if (getActivity() != null) {
                        if (LiveMatches.size() == 0) {
                            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                                NoLiveMatchesText.setTextColor(Color.WHITE);
                                NoLiveMatchesButton.setBackgroundResource(R.drawable.button_shape_dark);
                                NoLiveMatchesButton.setTextColor(Color.BLACK);
                            }
                            No_live_matches.setVisibility(View.VISIBLE);
                            NoLiveMatchesButton.setOnClickListener(new View.OnClickListener() {

                                public void onClick(View v) {

                                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                                    ft.detach(LiveMatchCard.this).attach(LiveMatchCard.this).commit();
                                }
                            });

                        }
                        mAdapter = new LiveMatchCardAdapter(getActivity(), LiveMatches);
                        indicator.setViewPager(vp);
                        indicator.setCount(mAdapter.getCount());
                        IndicatorConfig();
                        vp.setAdapter(mAdapter);
                    }
                } else {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    no_internet_connection.setVisibility(View.VISIBLE);
                    common_match_cards.setVisibility(View.INVISIBLE);
                    NoConnectionText.setText(R.string.server_issues);
                    NoConnectionButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {

                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.detach(LiveMatchCard.this).attach(LiveMatchCard.this).commit();

                        }
                    });
                    call.cancel();

                }
            }

            @Override
            public void onFailure(Call<LiveMatchCardItem> call, Throwable t) {
                mProgressBar.setVisibility(View.INVISIBLE);
                no_internet_connection.setVisibility(View.VISIBLE);
                common_match_cards.setVisibility(View.INVISIBLE);
                NoConnectionButton.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(LiveMatchCard.this).attach(LiveMatchCard.this).commit();

                    }
                });
                call.cancel();
            }
        });

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }


}
