package com.debut.ellipsis.freehit.More.favorites.FavouriteTeam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavTeamNews extends AppCompatActivity {

    private ProgressBar mProgressBar;
    APIInterface apiInterface;
    private Toolbar toolbar;
    public TextView NoNewsText;
    public Button NoNewsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_more_fav_team_news_list);
        Intent i = getIntent();
        String Team = i.getStringExtra("CountryName");

        final String TeamName = Team.toLowerCase();

        apiInterface = ApiClient.getClient().create(APIInterface.class);

        View viewRecycler = (View) findViewById(R.id.fav_team_news_list);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        View viewProgress = (View) findViewById(R.id.progress);
        mProgressBar = (ProgressBar) viewProgress.findViewById(R.id.progress_bar);


        final SwipeRefreshLayout refLayout = (SwipeRefreshLayout) viewRecycler.findViewById(R.id.refresh_layout);

        final View No_news = findViewById(R.id.No_news);

        NoNewsText = (TextView) No_news.findViewById(R.id.empty_view);
        NoNewsButton = (Button) No_news.findViewById(R.id.No_Live_Matches_button);

        View viewToolbar = (View) findViewById(R.id.fav_team_news_toolbar);

        toolbar = (Toolbar) viewToolbar.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.category_news);

        /* GET NEws List For Favourite Team */
        Call<FavTeamNewsItem> call = apiInterface.doGetNewsArticleTeam(TeamName);
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
                recyclerView.setAdapter(new FavTeamNewsItemAdapter(news, R.layout.fragment_news_list_item, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<FavTeamNewsItem> call, Throwable t) {
                mProgressBar.setVisibility(View.INVISIBLE);
                No_news.setVisibility(View.INVISIBLE);
                Toast toast = Toast.makeText(getApplicationContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
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

                                               Call<FavTeamNewsItem> call = apiInterface.doGetNewsArticleTeam(TeamName);
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
                                                       recyclerView.setAdapter(new FavTeamNewsItemAdapter(news, R.layout.fragment_news_list_item, getApplicationContext()));
                                                   }

                                                   @Override
                                                   public void onFailure(Call<FavTeamNewsItem> call, Throwable t) {
                                                       mProgressBar.setVisibility(View.INVISIBLE);
                                                       No_news.setVisibility(View.INVISIBLE);
                                                       Toast toast = Toast.makeText(getApplicationContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                                                       toast.show();
                                                       call.cancel();

                                                   }
                                               });

                                               refLayout.setRefreshing(false);
                                           }
                                       }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(0, R.anim.exit_to_right);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FavTeamNews.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);

    }
}

