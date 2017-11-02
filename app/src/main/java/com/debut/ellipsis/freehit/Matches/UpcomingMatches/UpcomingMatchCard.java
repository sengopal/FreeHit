package com.debut.ellipsis.freehit.Matches.UpcomingMatches;

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
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingMatchCard extends Fragment {

    private ProgressBar mProgressBar;
    public PageIndicatorView indicator;
    private UpcomingMatchesItemAdapter mAdapter;
    public ViewPager vp;
    public Button NoConnectionButton;

    public UpcomingMatchCard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = null ;

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            rootView = inflater.inflate(R.layout.fragment_matches_common_pager_dark, container, false);
        }
        else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            rootView = inflater.inflate(R.layout.fragment_matches_common_pager, container, false);
        }

        MainActivity.apiInterface = ApiClient.getClient().create(APIInterface.class);

        View viewProgress = rootView.findViewById(R.id.progress);
        mProgressBar = viewProgress.findViewById(R.id.progress_bar);

        final View common_match_cards = rootView.findViewById(R.id.common_match_cards);

        View viewViewPager = common_match_cards.findViewById(R.id.match_card_viewpagegr);

        vp = viewViewPager.findViewById(R.id.viewpager);

        indicator = common_match_cards.findViewById(R.id.indicator);
        final PullRefreshLayout refreshLayout = common_match_cards.findViewById(R.id.swipeRefreshLayout);


        final View no_internet_connection = rootView.findViewById(R.id.Unavailable_connection);

        NoConnectionButton = no_internet_connection.findViewById(R.id.no_internet_refresh_button);

        Call<UpcomingMatchCardItem> call = MainActivity.apiInterface.doGetUpcomingMatchListResources();
        call.enqueue(new Callback<UpcomingMatchCardItem>() {
            @Override
            public void onResponse(Call<UpcomingMatchCardItem> call, Response<UpcomingMatchCardItem> response) {

                mProgressBar.setVisibility(View.GONE);

                common_match_cards.setVisibility(View.VISIBLE);
                no_internet_connection.setVisibility(View.INVISIBLE);

                indicator.setVisibility(View.VISIBLE);

                indicator.setViewPager(vp);

                List<UpcomingMatchCardItem> upcomingMatches = response.body().getResults();

                if (getActivity() != null) {
                    mAdapter = new UpcomingMatchesItemAdapter(getActivity(), upcomingMatches);
                    indicator.setViewPager(vp);
                    indicator.setCount(mAdapter.getCount());
                    IndicatorConfig();
                    vp.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<UpcomingMatchCardItem> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                no_internet_connection.setVisibility(View.VISIBLE);
                common_match_cards.setVisibility(View.INVISIBLE);
                NoConnectionButton.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(UpcomingMatchCard.this).attach(UpcomingMatchCard.this).commit();

                    }
                });
                call.cancel();
            }
        });

        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // start refresh
                refreshLayout.setRefreshing(true);
                Call<UpcomingMatchCardItem> call = MainActivity.apiInterface.doGetUpcomingMatchListResources();
                call.enqueue(new Callback<UpcomingMatchCardItem>() {
                    @Override
                    public void onResponse(Call<UpcomingMatchCardItem> call, Response<UpcomingMatchCardItem> response) {

                        mProgressBar.setVisibility(View.GONE);

                        common_match_cards.setVisibility(View.VISIBLE);
                        no_internet_connection.setVisibility(View.INVISIBLE);

                        indicator.setVisibility(View.VISIBLE);

                        indicator.setViewPager(vp);

                        List<UpcomingMatchCardItem> UpcomingMatches = response.body().getResults();

                        if (getActivity() != null) {
                            mAdapter = new UpcomingMatchesItemAdapter(getActivity(), UpcomingMatches);
                            indicator.setViewPager(vp);
                            indicator.setCount(mAdapter.getCount());
                            indicator.setVisibility(View.INVISIBLE);
                            IndicatorConfig();
                            vp.setAdapter(mAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<UpcomingMatchCardItem> call, Throwable t) {
                        mProgressBar.setVisibility(View.INVISIBLE);
                        common_match_cards.setVisibility(View.VISIBLE);
                        Toast toast = Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                        toast.show();
                        call.cancel();
                    }
                });
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

}
