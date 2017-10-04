package com.debut.ellipsis.freehit.News;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.R;

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

        /*mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar); */

        /**
         GET List Resources
         **/
        Call<NewsItem> call = apiInterface.doGetNewsListResources();
        call.enqueue(new Callback<NewsItem>() {
            @Override
            public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {



                List<NewsItem> news = response.body().getResults();
                recyclerView.setAdapter(new NewsItemAdapter(news, R.layout.fragment_news_list_item, getContext()));
            }

            @Override
            public void onFailure(Call<NewsItem> call, Throwable t) {
                call.cancel();
            }
        });



        return rootView;
    }

}
