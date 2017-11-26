package com.debut.ellipsis.freehit.Matches.PastMatches;

import android.graphics.Color;
import android.os.Bundle;
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
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PastMatchCard extends Fragment {

    ProgressBar mProgressBar;
    PageIndicatorView indicator;
    PastMatchCardItemAdapter mAdapter;
    ViewPager vp;
    Button NoConnectionButton;
    TextView NoConnectionText;
    View common_match_cards;
    View no_internet_connection;

    public PastMatchCard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = null;

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            rootView = inflater.inflate(R.layout.fragment_matches_past_pager_dark, container, false);
        } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            rootView = inflater.inflate(R.layout.fragment_matches_past_pager, container, false);
        }

        View viewProgress = rootView.findViewById(R.id.progress);
        mProgressBar = viewProgress.findViewById(R.id.progress_bar);

        common_match_cards = rootView.findViewById(R.id.common_match_cards);

        View viewViewPager = common_match_cards.findViewById(R.id.match_card_viewpagegr);

        vp = viewViewPager.findViewById(R.id.viewpager);

        vp.setOffscreenPageLimit(5);

        indicator = common_match_cards.findViewById(R.id.indicator);
        final PullRefreshLayout refreshLayout = common_match_cards.findViewById(R.id.swipeRefreshLayout);


        no_internet_connection = rootView.findViewById(R.id.Unavailable_connection);

        NoConnectionText = no_internet_connection.findViewById(R.id.no_internet_connection_text);
        NoConnectionButton = no_internet_connection.findViewById(R.id.no_internet_refresh_button);

        PastCall();

        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
                                               @Override
                                               public void onRefresh() {
                                                   // Checking if connected or not on refresh
                                                   refreshLayout.setRefreshing(true);
                                                   PastCall();
                                                   refreshLayout.setRefreshing(false);
                                               }
                                           }
        );


        return rootView;
    }

    void PastCall() {
        Call<PastMatchCardItem> call = MainActivity.apiInterface.doGetPastCardResources();
        call.enqueue(new Callback<PastMatchCardItem>() {
            @Override
            public void onResponse(Call<PastMatchCardItem> call, Response<PastMatchCardItem> response) {
                if (response.isSuccessful()) {
                    no_internet_connection.setVisibility(View.INVISIBLE);
                    List<PastMatchCardItem> pastMatchCardItems = response.body().getResults();
                    if (getActivity() != null) {
                        mAdapter = new PastMatchCardItemAdapter(getActivity(), pastMatchCardItems);
                        indicator.setCount(mAdapter.getCount());
                        IndicatorConfig();
                        vp.setAdapter(new PastMatchCardItemAdapter(getContext(), pastMatchCardItems));
                        mProgressBar.setVisibility(View.GONE);
                        common_match_cards.setVisibility(View.VISIBLE);
                        indicator.setVisibility(View.VISIBLE);
                        indicator.setViewPager(vp);
                    }
                } else {
                    NoConnectionText.setText(R.string.server_issues);
                    mProgressBar.setVisibility(View.GONE);
                    no_internet_connection.setVisibility(View.VISIBLE);
                    common_match_cards.setVisibility(View.INVISIBLE);
                    NoConnectionButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.detach(PastMatchCard.this).attach(PastMatchCard.this).commit();

                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<PastMatchCardItem> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                no_internet_connection.setVisibility(View.INVISIBLE);
                common_match_cards.setVisibility(View.INVISIBLE);
                Toast toast = Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                toast.show();
                call.cancel();
            }
        });
    }

    void IndicatorConfig() {
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
}