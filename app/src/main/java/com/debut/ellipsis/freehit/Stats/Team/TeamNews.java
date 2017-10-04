package com.debut.ellipsis.freehit.Stats.Team;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.News.NewsItem;
import com.debut.ellipsis.freehit.News.NewsItemAdapter;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TeamNews extends Fragment {
    APIInterface apiInterface;
    private NewsItemAdapter mAdapter;
    private ProgressBar mProgressBar;

    public TeamNews() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_list, container, false);

        Intent i = getActivity().getIntent();
        int Team = i.getIntExtra("CountryName", 0);
        String twmpTeamName = this.getContext().getString(Team);

        String teamName = twmpTeamName.toLowerCase();

        apiInterface = ApiClient.getClient().create(APIInterface.class);

        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.news_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final TextView emptyView = (TextView) rootView.findViewById(R.id.empty_view);

        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);

        /**
         GET List Resources
         **/
        Call<NewsItem> call = apiInterface.doGetNewsArticleTeam(teamName);
        call.enqueue(new Callback<NewsItem>() {
            @Override
            public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {

                Log.d("TAG", response.code() + "");
                mProgressBar.setVisibility(View.INVISIBLE);

                List<NewsItem> news = response.body().getResults();
                if (news.size() == 0) {
                    emptyView.setText(R.string.EmptyNews);
                    emptyView.setVisibility(View.VISIBLE);
                }
                recyclerView.setAdapter(new NewsItemAdapter(news, R.layout.fragment_news_list_item, getContext()));
            }

            @Override
            public void onFailure(Call<NewsItem> call, Throwable t) {
                emptyView.setText("CHECK YOUR INTERNET CONNECTION");
                call.cancel();
            }
        });


        return rootView;
    }

}
