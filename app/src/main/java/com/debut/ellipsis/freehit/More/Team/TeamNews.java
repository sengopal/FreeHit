package com.debut.ellipsis.freehit.More.Team;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.debut.ellipsis.freehit.News.NewsItem;
import com.debut.ellipsis.freehit.News.NewsItemAdapter;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TeamNews extends Fragment {
    APIInterface apiInterface;
    private ProgressBar mProgressBar;
    public TextView NoNewsText;
    public Button NoNewsButton;

    public TeamNews() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_list, container, false);

        Intent i = getActivity().getIntent();
        int Team = i.getIntExtra("CountryName", 0);
        String tempTeamName ;
        String favTeam = i.getStringExtra("fav_country");

        if(Team == 0)
        {
            tempTeamName = favTeam;
        }
        else
        {
            tempTeamName = this.getContext().getString(Team);
        }

        final String teamName = tempTeamName.toLowerCase();

        apiInterface = ApiClient.getClient().create(APIInterface.class);

        View viewRecycler = rootView.findViewById(R.id.news_list);

        View viewFAB = rootView.findViewById(R.id.fab);
        viewFAB.setVisibility(View.GONE);

        final RecyclerView recyclerView = (RecyclerView) viewRecycler.findViewById(R.id.recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);

        final View No_news = rootView.findViewById(R.id.No_news);

        NoNewsText = (TextView) No_news.findViewById(R.id.empty_view);
        NoNewsButton = (Button) No_news.findViewById(R.id.No_Live_Matches_button);

        final SwipeRefreshLayout refLayout = (SwipeRefreshLayout) viewRecycler.findViewById(R.id.refresh_layout);

        Call<NewsItem> call = apiInterface.doGetNewsArticleTeam(teamName);
        call.enqueue(new Callback<NewsItem>() {
            @Override
            public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {

                mProgressBar.setVisibility(View.INVISIBLE);

                List<NewsItem> news = response.body().getResults();
                if (news.size() == 0) {
                    No_news.setVisibility(View.VISIBLE);
                    NoNewsButton.setVisibility(View.INVISIBLE);
                    NoNewsText.setText(R.string.EmptyNews);

                }
                recyclerView.setAdapter(new NewsItemAdapter(news, R.layout.fragment_news_list_item, getContext()));
            }

            @Override
            public void onFailure(Call<NewsItem> call, Throwable t) {
                mProgressBar.setVisibility(View.INVISIBLE);
                No_news.setVisibility(View.INVISIBLE);
                Toast toast = Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                toast.show();
                call.cancel();
            }
        });


        refLayout.setColorSchemeResources(R.color.orange);
        refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                           @Override
                                           public void onRefresh() {
                                               // Checking if connected or not on refresh
                                               refLayout.setRefreshing(true);

                                               Call<NewsItem> call = apiInterface.doGetNewsArticleTeam(teamName);
                                               call.enqueue(new Callback<NewsItem>() {
                                                   @Override
                                                   public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {
                                                       mProgressBar.setVisibility(View.INVISIBLE);

                                                       List<NewsItem> news = response.body().getResults();
                                                       if (news.size() == 0) {
                                                           No_news.setVisibility(View.VISIBLE);
                                                           NoNewsButton.setVisibility(View.INVISIBLE);
                                                           NoNewsText.setText(R.string.EmptyNews);

                                                       }
                                                       recyclerView.setAdapter(new NewsItemAdapter(news, R.layout.fragment_news_list_item, getContext()));
                                                   }

                                                   @Override
                                                   public void onFailure(Call<NewsItem> call, Throwable t) {
                                                       mProgressBar.setVisibility(View.INVISIBLE);
                                                       No_news.setVisibility(View.INVISIBLE);
                                                       Toast toast = Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                                                       toast.show();
                                                       call.cancel();

                                                   }
                                               });

                                               refLayout.setRefreshing(false);
                                           }
                                       }
        );


        return rootView;
    }

}
