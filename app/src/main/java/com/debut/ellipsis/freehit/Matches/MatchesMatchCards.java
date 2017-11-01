package com.debut.ellipsis.freehit.Matches;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.Matches.LiveMatches.LiveMatchCardAdapter;
import com.debut.ellipsis.freehit.Matches.LiveMatches.LiveMatchCardItem;
import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchCardItem;
import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchCardItemAdapter;
import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchCardItem;
import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchesItemAdapter;
import com.debut.ellipsis.freehit.R;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchesMatchCards extends Fragment {

    private ProgressBar mProgressBar;
    public PageIndicatorView indicator;
    private LiveMatchCardAdapter mLiveAdapter;
    private UpcomingMatchesItemAdapter mUpcomingAdapter;
    private PastMatchCardItemAdapter mPastAdapter;
    public ViewPager vp;
    public Button NoConnectionButton;
    public TextView NoLiveMatchesText;
    public Button NoLiveMatchesButton;

    public MatchesMatchCards() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        String fragment_name = getArguments().getString("fragment_name");
        final View rootView = inflater.inflate(R.layout.fragment_matches_common_pager, container, false);

        MainActivity.apiInterface = ApiClient.getClient().create(APIInterface.class);

        View viewProgress = rootView.findViewById(R.id.progress);
        mProgressBar = (ProgressBar) viewProgress.findViewById(R.id.progress_bar);

        final View common_match_cards = rootView.findViewById(R.id.common_match_cards);

        View viewViewPager = common_match_cards.findViewById(R.id.match_card_viewpagegr);

        vp = (ViewPager) viewViewPager.findViewById(R.id.viewpager);

        indicator = (PageIndicatorView) common_match_cards.findViewById(R.id.indicator);
        final PullRefreshLayout refreshLayout = (PullRefreshLayout) common_match_cards.findViewById(R.id.swipeRefreshLayout);


        final View no_internet_connection = rootView.findViewById(R.id.Unavailable_connection);

       /* NoConnectionImage = (ImageView) no_internet_connection.findViewById(R.id.no_internet_connection);*/
        NoConnectionButton = (Button) no_internet_connection.findViewById(R.id.no_internet_refresh_button);


        final View No_live_matches = rootView.findViewById(R.id.No_Live_Matches);

        NoLiveMatchesText = (TextView) No_live_matches.findViewById(R.id.empty_view);
        NoLiveMatchesButton = (Button) No_live_matches.findViewById(R.id.No_Live_Matches_button);

        if (fragment_name.equals("LIVE")) {
            Call<LiveMatchCardItem> call = MainActivity.apiInterface.doGetLiveMatchResources();
            call.enqueue(new Callback<LiveMatchCardItem>() {
                @Override
                public void onResponse(Call<LiveMatchCardItem> call, Response<LiveMatchCardItem> response) {


                    common_match_cards.setVisibility(View.VISIBLE);
                    no_internet_connection.setVisibility(View.INVISIBLE);
                    mProgressBar.setVisibility(View.INVISIBLE);
                    indicator.setVisibility(View.VISIBLE);

                    indicator.setViewPager(vp);

                    List<LiveMatchCardItem> LiveMatches = response.body().getResults();

                    if (getActivity() != null) {

                        if (LiveMatches.size() == 0) {
                            No_live_matches.setVisibility(View.VISIBLE);
                            NoLiveMatchesButton.setOnClickListener(new View.OnClickListener() {

                                public void onClick(View v) {

                                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                                    ft.detach(MatchesMatchCards.this).attach(MatchesMatchCards.this).commit();
                                }
                            });

                        }
                        mLiveAdapter = new LiveMatchCardAdapter(getActivity(), LiveMatches);
                        indicator.setViewPager(vp);
                        indicator.setCount(mLiveAdapter.getCount());
                        IndicatorConfig();
                        vp.setAdapter(mLiveAdapter);
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
                            ft.detach(MatchesMatchCards.this).attach(MatchesMatchCards.this).commit();

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
                    Call<LiveMatchCardItem> call = MainActivity.apiInterface.doGetLiveMatchResources();
                    call.enqueue(new Callback<LiveMatchCardItem>() {
                        @Override
                        public void onResponse(Call<LiveMatchCardItem> call, Response<LiveMatchCardItem> response) {

                            mProgressBar.setVisibility(View.INVISIBLE);

                            common_match_cards.setVisibility(View.VISIBLE);
                            no_internet_connection.setVisibility(View.INVISIBLE);

                            indicator.setVisibility(View.VISIBLE);

                            indicator.setViewPager(vp);

                            List<LiveMatchCardItem> LiveMatches = response.body().getResults();

                            if (getActivity() != null) {
                                if (LiveMatches.size() == 0) {
                                    No_live_matches.setVisibility(View.VISIBLE);
                                    NoLiveMatchesButton.setOnClickListener(new View.OnClickListener() {

                                        public void onClick(View v) {
                                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                                            ft.detach(MatchesMatchCards.this).attach(MatchesMatchCards.this).commit();
                                        }
                                    });
                                }
                                mLiveAdapter = new LiveMatchCardAdapter(getActivity(), LiveMatches);
                                indicator.setViewPager(vp);
                                indicator.setCount(mLiveAdapter.getCount());
                                indicator.setVisibility(View.INVISIBLE);
                                IndicatorConfig();
                                vp.setAdapter(mLiveAdapter);
                            }
                        }

                        @Override
                        public void onFailure(Call<LiveMatchCardItem> call, Throwable t) {
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
        }

        if (fragment_name.equals("UPCOMING")) {
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
                        mUpcomingAdapter = new UpcomingMatchesItemAdapter(getActivity(), upcomingMatches);
                        indicator.setViewPager(vp);
                        indicator.setCount(mUpcomingAdapter.getCount());
                        IndicatorConfig();
                        vp.setAdapter(mUpcomingAdapter);
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
                            ft.detach(MatchesMatchCards.this).attach(MatchesMatchCards.this).commit();

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
                                mUpcomingAdapter = new UpcomingMatchesItemAdapter(getActivity(), UpcomingMatches);
                                indicator.setViewPager(vp);
                                indicator.setCount(mUpcomingAdapter.getCount());
                                indicator.setVisibility(View.INVISIBLE);
                                IndicatorConfig();
                                vp.setAdapter(mUpcomingAdapter);
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
        }

        if (fragment_name.equals("PAST")) {
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
                        mPastAdapter = new PastMatchCardItemAdapter(getActivity(), pastMatchCardItems);
                        indicator.setCount(mPastAdapter.getCount());
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

                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.detach(MatchesMatchCards.this).attach(MatchesMatchCards.this).commit();

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
                                                                   mPastAdapter = new PastMatchCardItemAdapter(getActivity(), pastMatchCardItems);
                                                                   indicator.setCount(mPastAdapter.getCount());
                                                                   IndicatorConfig();
                                                                   vp.setAdapter(new PastMatchCardItemAdapter(getContext(), pastMatchCardItems));
                                                               }
                                                           }

                                                           @Override
                                                           public void onFailure(Call<PastMatchCardItem> call, Throwable t) {
                                                               mProgressBar.setVisibility(View.INVISIBLE);
                                                               common_match_cards.setVisibility(View.VISIBLE);
                                                               Toast toast = Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                                                               toast.show();
                                                               call.cancel();

                                                           }
                                                       });
                                                       refreshLayout.setRefreshing(false);
                                                   }
                                               }
            );
        }

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
