package com.debut.ellipsis.freehit.Matches.LiveMatches;


import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.R;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveMatchCard extends Fragment {

    APIInterface apiInterface;
    private LiveMatchCardAdapter mAdapter;
    private ProgressBar mProgressBar;
    public ViewPager viewPager;
    public PageIndicatorView indicator;

    public LiveMatchCard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_matches_match_cards_item, container, false);

        apiInterface = ApiClient.getClient().create(APIInterface.class);
        indicator = (PageIndicatorView) rootView.findViewById(R.id.indicator);
        indicator.setVisibility(View.INVISIBLE);
        indicator.setViewPager(viewPager);

        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);

        final PullRefreshLayout layout = (PullRefreshLayout)rootView.findViewById(R.id.swipeRefreshLayout);

        final ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(getContext().CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        /**
         GET List Resources
         **/
        Call<LiveMatchCardItem> call = apiInterface.doGetLiveMatchResources();
        call.enqueue(new Callback<LiveMatchCardItem>() {
            @Override
            public void onResponse(Call<LiveMatchCardItem> call, Response<LiveMatchCardItem> response) {

                List<LiveMatchCardItem> LiveMatches = response.body().getResults();
                viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
                mProgressBar.setVisibility(View.GONE);
                if(getActivity()!=null) {
                    mAdapter = new LiveMatchCardAdapter(getActivity(), LiveMatches);
                    indicator.setViewPager(viewPager);
                    indicator.setCount(mAdapter.getCount());
                    indicator.setVisibility(View.INVISIBLE);
                    IndicatorConfig();
                    viewPager.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<LiveMatchCardItem> call, Throwable t) {
                call.cancel();
            }
        });


        final TextView emptyView = (TextView) rootView.findViewById(R.id.empty_view);


        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // start refresh
                layout.setRefreshing(true);
                Call<LiveMatchCardItem> call = apiInterface.doGetLiveMatchResources();
                call.enqueue(new Callback<LiveMatchCardItem>() {
                    @Override
                    public void onResponse(Call<LiveMatchCardItem> call, Response<LiveMatchCardItem> response) {

                        List<LiveMatchCardItem> LiveMatches = response.body().getResults();
                        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
                        mProgressBar.setVisibility(View.GONE);
                        if(getActivity()!=null) {
                            if (LiveMatches.size() == 0) {
                                emptyView.setText(R.string.EmptyNews);
                                emptyView.setVisibility(View.VISIBLE);
                            }
                            mAdapter = new LiveMatchCardAdapter(getActivity(), LiveMatches);
                            indicator.setViewPager(viewPager);
                            indicator.setCount(mAdapter.getCount());
                            indicator.setVisibility(View.INVISIBLE);
                            IndicatorConfig();
                            viewPager.setAdapter(mAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<LiveMatchCardItem> call, Throwable t) {
                        viewPager.setAdapter(null);
                        emptyView.setText("PLEASE CHECK YOUR INTERNET CONNECTION");
                        call.cancel();
                    }
                });
                layout.setRefreshing(false);
            }

        });





        return rootView;
    }

    private void IndicatorConfig() {
        indicator.setVisibility(View.VISIBLE);
        indicator.setAnimationType(AnimationType.DROP);
        indicator.setUnselectedColor(Color.parseColor("#F06292"));
        indicator.setSelectedColor(Color.parseColor("#E91E63"));
        indicator.setInteractiveAnimation(true);
        indicator.setAnimationDuration(500);

    }

}
