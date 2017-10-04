package com.debut.ellipsis.freehit.News;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    APIInterface apiInterface;
    private NewsItemAdapter mAdapter;
    private ProgressBar mProgressBar;
    private TextView mEmptyView;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_list, container, false);

        apiInterface = ApiClient.getClient().create(APIInterface.class);

        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.news_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mEmptyView = (TextView) rootView.findViewById(R.id.empty_view);

        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);

        final SwipeRefreshLayout refLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh_layout);

        /**
         GET NewsList Resources
         **/
        Call<NewsItem> call = apiInterface.doGetNewsListResources();
        call.enqueue(new Callback<NewsItem>() {
            @Override
            public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {
                ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(getContext().CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {

                    mProgressBar.setVisibility(View.INVISIBLE);
                    List<NewsItem> news = response.body().getResults();
                    if (getActivity() != null) {
                        if (news.size() == 0) {
                            mEmptyView.setText(R.string.EmptyPolls);
                            mEmptyView.setVisibility(View.VISIBLE);
                        }
                        recyclerView.setAdapter(new NewsItemAdapter(news, R.layout.fragment_news_list_item, getContext()));
                    }
                } else {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    List<NewsItem> newsListitems = new ArrayList<NewsItem>(1);
                    recyclerView.setAdapter(new NewsItemAdapter(newsListitems, R.layout.fragment_social_polls_list_item, getContext()));
                    mEmptyView.setText("CHECK YOUR INTERNET CONNECTION");
                }
            }

            @Override
            public void onFailure(Call<NewsItem> call, Throwable t) {
                mProgressBar.setVisibility(View.INVISIBLE);
                List<NewsItem> newsListitems = new ArrayList<NewsItem>(1);
                recyclerView.setAdapter(new NewsItemAdapter(newsListitems, R.layout.fragment_social_polls_list_item, getContext()));
                mEmptyView.setText("CHECK YOUR INTERNET CONNECTION");

                call.cancel();
            }
        });

        refLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);
        refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                           @Override
                                           public void onRefresh() {
                                               // Checking if connected or not on refresh
                                               refLayout.setRefreshing(true);
                                               ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(getContext().CONNECTIVITY_SERVICE);
                                               NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                                               if (networkInfo != null && networkInfo.isConnected()) {

                                                   Call<NewsItem> call = apiInterface.doGetNewsListResources();
                                                   call.enqueue(new Callback<NewsItem>() {
                                                       @Override
                                                       public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {
                                                           mProgressBar.setVisibility(View.INVISIBLE);
                                                           mEmptyView.setVisibility(View.INVISIBLE);
                                                           if (getActivity() != null) {
                                                               List<NewsItem> NewsListItem = response.body().getResults();
                                                               if (NewsListItem.size() == 0) {
                                                                   mEmptyView.setText(R.string.EmptyNews);
                                                                   mEmptyView.setVisibility(View.VISIBLE);
                                                               }
                                                               recyclerView.setVisibility(View.VISIBLE);
                                                               recyclerView.setAdapter(new NewsItemAdapter(NewsListItem, R.layout.fragment_news_list_item, getContext()));
                                                           }
                                                       }
                                                       
                                                       @Override
                                                       public void onFailure(Call<NewsItem> call, Throwable t) {
                                                           mProgressBar.setVisibility(View.INVISIBLE);
                                                            recyclerView.setVisibility(View.INVISIBLE);
                                                           mEmptyView.setText("CHECK YOUR INTERNET CONNECTION");
                                                           call.cancel();

                                                       }
                                                   });
                                               }

                                               else
                                               {
                                                   recyclerView.setVisibility(View.INVISIBLE);
                                                   mProgressBar.setVisibility(View.INVISIBLE);
                                                   mEmptyView.setText("CHECK YOUR INTERNET CONNECTION");
                                                   mEmptyView.setVisibility(View.VISIBLE);
                                               }

                                               refLayout.setRefreshing(false);
                                           }
                                       }
        );


        return rootView;
    }

}
