package com.debut.ellipsis.freehit.Matches.PastMatches;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.R;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PastMatchCard extends Fragment {
    APIInterface apiInterface;
    private ProgressBar mProgressBar;
    public PageIndicatorView indicator;
    private PastMatchCardItemAdapter mAdapter;

    public PastMatchCard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       final View rootView = inflater.inflate(R.layout.fragment_matches_match_cards_item, container, false);

        apiInterface = ApiClient.getClient().create(APIInterface.class);
        Call<PastMatchCardItem> call = apiInterface.doGetPastCardResources();
        call.enqueue(new Callback<PastMatchCardItem>() {
            @Override
            public void onResponse(Call<PastMatchCardItem> call, Response<PastMatchCardItem> response) {
                ViewPager vp =(ViewPager) rootView.findViewById(R.id.viewpager);

                mProgressBar= (ProgressBar) rootView.findViewById(R.id.progress_bar);
                mProgressBar.setVisibility(View.GONE);
                indicator = (PageIndicatorView) rootView.findViewById(R.id.indicator);
                indicator.setVisibility(View.INVISIBLE);
                indicator.setViewPager(vp);
                List<PastMatchCardItem> poll = response.body().getResults();
                mAdapter= new PastMatchCardItemAdapter(getActivity(), poll);
                indicator.setCount(mAdapter.getCount());
                IndicatorConfig();
                vp.setAdapter(new PastMatchCardItemAdapter(getContext(), poll));
            }

            @Override
            public void onFailure(Call<PastMatchCardItem> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed"+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

    private void IndicatorConfig(){
        indicator.setAnimationType(AnimationType.DROP);
        indicator.setUnselectedColor(Color.parseColor("#F06292"));
        indicator.setSelectedColor(Color.parseColor("#E91E63"));
        indicator.setInteractiveAnimation(true);
        indicator.setAnimationDuration(500);

    }
}


