package com.debut.ellipsis.freehit.Matches.UpcomingMatches;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.R;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingMatchCard extends Fragment {

    APIInterface apiInterface;
    private UpcomingMatchesItemAdapter mAdapter;
    private ProgressBar mProgressBar;
    public ViewPager viewPager;
    public PageIndicatorView indicator;

    public UpcomingMatchCard() {
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

        /**
         GET List Resources
         **/
        Call<UpcomingMatchCardItem> call = apiInterface.doGetUpcomingMatchListResources();
        call.enqueue(new Callback<UpcomingMatchCardItem>() {
            @Override
            public void onResponse(Call<UpcomingMatchCardItem> call, Response<UpcomingMatchCardItem> response) {

                List<UpcomingMatchCardItem> upcomingMatches = response.body().getResults();
                viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
                mProgressBar.setVisibility(View.GONE);
                if(getActivity()!=null) {
                    mAdapter = new UpcomingMatchesItemAdapter(getActivity(), upcomingMatches);
                    indicator.setViewPager(viewPager);
                    indicator.setCount(mAdapter.getCount());
                    IndicatorConfig();
                    viewPager.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<UpcomingMatchCardItem> call, Throwable t) {
                call.cancel();
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
