package com.debut.ellipsis.freehit.Matches.PastMatches;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

public class PastMatchCard extends Fragment {

    private ProgressBar mProgressBar;
    public PageIndicatorView indicator;
    private PastMatchCardItemAdapter mAdapter;
    public ViewPager vp;
    public ImageView NoConnectionImage;
    public Button NoConnectionButton;

    public PastMatchCard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_matches_common_pager, container, false);

        MainActivity.apiInterface = ApiClient.getClient().create(APIInterface.class);

        View viewProgress= rootView.findViewById(R.id.progress);
        mProgressBar = (ProgressBar) viewProgress.findViewById(R.id.progress_bar);

        final View common_match_cards = rootView.findViewById(R.id.common_match_cards);

        View viewViewPager = common_match_cards.findViewById(R.id.match_card_viewpagegr);

        vp = (ViewPager) viewViewPager.findViewById(R.id.viewpager);
        indicator = (PageIndicatorView) common_match_cards.findViewById(R.id.indicator);
        final PullRefreshLayout refreshLayout = (PullRefreshLayout) common_match_cards.findViewById(R.id.swipeRefreshLayout);


        final View no_internet_connection = rootView.findViewById(R.id.Unavailable_connection);

        NoConnectionImage = (ImageView) no_internet_connection.findViewById(R.id.no_internet_connection);
        NoConnectionButton = (Button) no_internet_connection.findViewById(R.id.no_internet_refresh_button);


        Call<PastMatchCardItem> call = MainActivity.apiInterface.doGetPastCardResources();
        call.enqueue(new Callback<PastMatchCardItem>() {
            @Override
            public void onResponse(Call<PastMatchCardItem> call, Response<PastMatchCardItem> response) {
                mProgressBar.setVisibility(View.GONE);

                common_match_cards.setVisibility(View.VISIBLE);
                no_internet_connection.setVisibility(View.INVISIBLE);

                indicator.setVisibility(View.VISIBLE);

                indicator.setViewPager(vp);

                List<PastMatchCardItem> pastMatchCardItems = response.body().getResults();
                if (getActivity() != null) {
                    mAdapter = new PastMatchCardItemAdapter(getActivity(), pastMatchCardItems);
                    indicator.setCount(mAdapter.getCount());
                    IndicatorConfig();
                    vp.setAdapter(new PastMatchCardItemAdapter(getContext(), pastMatchCardItems));
                }
            }

            @Override
            public void onFailure(Call<PastMatchCardItem> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                no_internet_connection.setVisibility(View.VISIBLE);
                common_match_cards.setVisibility(View.INVISIBLE);
                NoConnectionButton.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), MainActivity.class);//which is your mainActivity-Launcher
                        i.putExtra("Main_tab",0);
                        i.putExtra("Sub_tab",2);
                        i.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);

                    }
                });
                call.cancel();
            }
        });

        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
                                               @Override
                          public void onRefresh() {
                          // Checking if connected or not on refresh
                          refreshLayout.setRefreshing(true);

                      Call<PastMatchCardItem> call = MainActivity.apiInterface.doGetPastCardResources();
                      call.enqueue(new Callback<PastMatchCardItem>() {
                          @Override
                          public void onResponse(Call<PastMatchCardItem> call, Response<PastMatchCardItem> response) {
                              mProgressBar.setVisibility(View.GONE);

                              common_match_cards.setVisibility(View.VISIBLE);
                              no_internet_connection.setVisibility(View.INVISIBLE);

                              indicator.setVisibility(View.VISIBLE);

                              indicator.setViewPager(vp);
                              List<PastMatchCardItem> pastMatchCardItems = response.body().getResults();
                              if (getActivity() != null) {
                                  mAdapter = new PastMatchCardItemAdapter(getActivity(), pastMatchCardItems);
                                  indicator.setCount(mAdapter.getCount());
                                  IndicatorConfig();
                                  vp.setAdapter(new PastMatchCardItemAdapter(getContext(), pastMatchCardItems));
                              }
                          }

                          @Override
                          public void onFailure(Call<PastMatchCardItem> call, Throwable t) {
                              mProgressBar.setVisibility(View.INVISIBLE);
                              common_match_cards.setVisibility(View.VISIBLE);
                              Toast toast=Toast.makeText(getContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT);
                              toast.show();
                              call.cancel();

                          }
                      });
                      refreshLayout.setRefreshing(false);
                       }
                        }
        );


        return rootView;
    }


    private void IndicatorConfig() {
        indicator.setAnimationType(AnimationType.WORM);
        indicator.setUnselectedColor(Color.parseColor("#f94d44"));
        indicator.setSelectedColor(Color.parseColor("#bf031b"));
        indicator.setInteractiveAnimation(true);
        indicator.setAnimationDuration(500);

    }
}


