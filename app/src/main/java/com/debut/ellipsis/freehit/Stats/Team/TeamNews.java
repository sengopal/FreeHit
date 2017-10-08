package com.debut.ellipsis.freehit.Stats.Team;

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
import com.debut.ellipsis.freehit.News.NewsItemAdapter;
import com.debut.ellipsis.freehit.R;
import com.debut.ellipsis.freehit.favorites.FavouriteTeam.FavTeamNewsItem;
import com.debut.ellipsis.freehit.favorites.FavouriteTeam.FavTeamNewsItemAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TeamNews extends Fragment {
    APIInterface apiInterface;
    private NewsItemAdapter mAdapter;
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
        String tempTeamName = this.getContext().getString(Team);

        final String teamName = tempTeamName.toLowerCase();

        apiInterface = ApiClient.getClient().create(APIInterface.class);

        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.news_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final TextView emptyView = (TextView) rootView.findViewById(R.id.empty_view);

        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);

        final View No_news = rootView.findViewById(R.id.No_news);

        NoNewsText = (TextView) No_news.findViewById(R.id.empty_view);
        NoNewsButton = (Button) No_news.findViewById(R.id.No_Live_Matches_button);

        final SwipeRefreshLayout refLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh_layout);

        /**
         GET List Resources
         **/
        Call<FavTeamNewsItem> call = apiInterface.doGetNewsArticleTeam(teamName);
        call.enqueue(new Callback<FavTeamNewsItem>() {
            @Override
            public void onResponse(Call<FavTeamNewsItem> call, Response<FavTeamNewsItem> response) {

                mProgressBar.setVisibility(View.INVISIBLE);

                List<FavTeamNewsItem> news = response.body().getResults();
                if (news.size() == 0) {
                    No_news.setVisibility(View.VISIBLE);
                    NoNewsButton.setVisibility(View.INVISIBLE);
                    NoNewsText.setText(R.string.EmptyNews);

                }
                recyclerView.setAdapter(new FavTeamNewsItemAdapter(news, R.layout.fragment_news_list_item, getContext()));
            }

            @Override
            public void onFailure(Call<FavTeamNewsItem> call, Throwable t) {
                mProgressBar.setVisibility(View.INVISIBLE);
                No_news.setVisibility(View.INVISIBLE);
                Toast toast=Toast.makeText(getContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT);
                toast.show();
                call.cancel();
            }
        });


        refLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);
        refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Checking if connected or not on refresh
                refLayout.setRefreshing(true);

                Call<FavTeamNewsItem> call = apiInterface.doGetNewsArticleTeam(teamName);
                call.enqueue(new Callback<FavTeamNewsItem>() {
                    @Override
                    public void onResponse(Call<FavTeamNewsItem> call, Response<FavTeamNewsItem> response) {
                        mProgressBar.setVisibility(View.INVISIBLE);

                        List<FavTeamNewsItem> news = response.body().getResults();
                        if (news.size() == 0) {
                            No_news.setVisibility(View.VISIBLE);
                            NoNewsButton.setVisibility(View.INVISIBLE);
                            NoNewsText.setText(R.string.EmptyNews);

                        }
                        recyclerView.setAdapter(new FavTeamNewsItemAdapter(news, R.layout.fragment_news_list_item, getContext()));
                    }

                    @Override
                    public void onFailure(Call<FavTeamNewsItem> call, Throwable t) {
                        mProgressBar.setVisibility(View.INVISIBLE);
                        No_news.setVisibility(View.INVISIBLE);
                        Toast toast=Toast.makeText(getContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT);
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
