package com.debut.ellipsis.freehit.News;


import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsFragment extends Fragment {

    private ProgressBar mProgressBar;
    public Button NoConnectionButton;
    public TextView NoNewsText;
    public Button NoNewsButton;
    private FloatingActionButton fab;
    private LinearLayoutManager mLinearLayoutManager;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_news_list, container, false);

        MainActivity.apiInterface = ApiClient.getClient().create(APIInterface.class);

        View viewRecycler = rootView.findViewById(R.id.news_list);

        View viewFAB = rootView.findViewById(R.id.fab);
        fab = viewFAB.findViewById(R.id.common_fab);
        fab.hide();
        fab.setImageResource(R.drawable.arrow_up);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        final RecyclerView recyclerView = viewRecycler.findViewById(R.id.recycler_list);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            recyclerView.setBackgroundColor(getResources().getColor(R.color.night_background));
            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dark)));
        }

        View viewProgress = rootView.findViewById(R.id.progress);
        mProgressBar = viewProgress.findViewById(R.id.progress_bar);

        final SwipeRefreshLayout refLayout = viewRecycler.findViewById(R.id.refresh_layout);

        final View no_internet_connection = rootView.findViewById(R.id.Unavailable_connection);

        NoConnectionButton = no_internet_connection.findViewById(R.id.no_internet_refresh_button);


        final View No_news = rootView.findViewById(R.id.No_news);

        NoNewsText = No_news.findViewById(R.id.empty_view);
        NoNewsButton = No_news.findViewById(R.id.No_Live_Matches_button);


        Call<NewsItem> call = MainActivity.apiInterface.doGetNewsListResources();
        call.enqueue(new Callback<NewsItem>() {
            @Override
            public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {


                List<NewsItem> news = response.body().getResults();
                if (news.size() == 0) {
                    No_news.setVisibility(View.VISIBLE);
                    NoNewsText.setText(R.string.EmptyNews);
                    NoNewsButton.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {

                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.detach(NewsFragment.this).attach(NewsFragment.this).commit();
                        }
                    });
                }
                if (getActivity() != null) {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    no_internet_connection.setVisibility(View.INVISIBLE);
                    recyclerView.setAdapter(new NewsItemAdapter(news, R.layout.fragment_news_list_item, getContext()));
                }
            }

            @Override
            public void onFailure(Call<NewsItem> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                no_internet_connection.setVisibility(View.VISIBLE);
                NoConnectionButton.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(NewsFragment.this).attach(NewsFragment.this).commit();

                    }
                });

                call.cancel();
            }
        });

        refLayout.setColorSchemeResources(R.color.orange);
        refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                           @Override
                                           public void onRefresh() {
                                               // Checking if connected or not on refresh
                                               refLayout.setRefreshing(true);
                                               fab.hide();
                                               Call<NewsItem> call = MainActivity.apiInterface.doGetNewsListResources();
                                               call.enqueue(new Callback<NewsItem>() {
                                                   @Override
                                                   public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {
                                                       mProgressBar.setVisibility(View.INVISIBLE);
                                                       no_internet_connection.setVisibility(View.INVISIBLE);
                                                       No_news.setVisibility(View.INVISIBLE);
                                                       fab.show();
                                                       if (getActivity() != null) {
                                                           List<NewsItem> NewsListItem = response.body().getResults();
                                                           if (NewsListItem.size() == 0) {
                                                               No_news.setVisibility(View.VISIBLE);

                                                               NoNewsText.setText(R.string.EmptyNews);
                                                               NoNewsButton.setOnClickListener(new View.OnClickListener() {

                                                                   public void onClick(View v) {

                                                                       FragmentTransaction ft = getFragmentManager().beginTransaction();
                                                                       ft.detach(NewsFragment.this).attach(NewsFragment.this).commit();
                                                                   }
                                                               });
                                                           }
                                                           recyclerView.setVisibility(View.VISIBLE);
                                                           recyclerView.setAdapter(new NewsItemAdapter(NewsListItem, R.layout.fragment_news_list_item, getContext()));
                                                       }
                                                   }

                                                   @Override
                                                   public void onFailure(Call<NewsItem> call, Throwable t) {
                                                       mProgressBar.setVisibility(View.INVISIBLE);
                                                       fab.hide();
                                                       Toast toast = Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                                                       toast.show();
                                                       call.cancel();


                                                   }
                                               });


                                               refLayout.setRefreshing(false);
                                           }
                                       }
        );

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (mLinearLayoutManager.findFirstVisibleItemPosition() == 0) {
                    fab.hide();

                } else {
                    fab.show();
                }
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int firstVisibleItemIndex = mLinearLayoutManager.findFirstVisibleItemPosition();
                if (firstVisibleItemIndex > 0) {
                    mLinearLayoutManager.smoothScrollToPosition(recyclerView, null, 0);
                }
            }
        });


        return rootView;
    }

}