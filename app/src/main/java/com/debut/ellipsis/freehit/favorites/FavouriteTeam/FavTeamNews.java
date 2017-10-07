package com.debut.ellipsis.freehit.favorites.FavouriteTeam;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.News.NewsItem;
import com.debut.ellipsis.freehit.News.NewsItemAdapter;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavTeamNews extends AppCompatActivity {

    private ProgressBar mProgressBar;
    APIInterface apiInterface;
    private Toolbar toolbar;
    public ImageView NoConnectionImage;
    public Button NoConnectionButton;
    public TextView NoNewsText;
    public Button NoNewsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fav_team_news_list);
        Intent i = getIntent();
        final String Team = i.getStringExtra("CountryName");

        apiInterface = ApiClient.getClient().create(APIInterface.class);

        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.news_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        final SwipeRefreshLayout refLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);

        final View no_internet_connection = findViewById(R.id.Unavailable_connection);

        NoConnectionImage = (ImageView) no_internet_connection.findViewById(R.id.no_internet_connection);
        NoConnectionButton = (Button) no_internet_connection.findViewById(R.id.no_internet_refresh_button);


        final View No_news = findViewById(R.id.No_news);

        NoNewsText = (TextView) No_news.findViewById(R.id.empty_view);
        NoNewsButton = (Button) No_news.findViewById(R.id.No_Live_Matches_button);

        toolbar = (Toolbar) findViewById(R.id.toolbar_for_news_list);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.category_news);

        /**
         GET List Resources
         **/
        Call<NewsItem> call = apiInterface.doGetNewsArticleTeam(Team.toLowerCase());
        call.enqueue(new Callback<NewsItem>() {
            @Override
            public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {

                mProgressBar.setVisibility(View.INVISIBLE);
                no_internet_connection.setVisibility(View.INVISIBLE);
                List<NewsItem> news = response.body().getResults();
                    if (news.size() == 0) {
                        No_news.setVisibility(View.VISIBLE);
                        NoNewsText.setText(R.string.EmptyNews);
                        NoNewsButton.setOnClickListener(new View.OnClickListener() {

                            public void onClick(View v) {
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);//which is your mainActivity-Launcher
                                i.putExtra("Main_tab",4);
                                i.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(i);
                            }
                        });
                    }
                    recyclerView.setAdapter(new NewsItemAdapter(news, R.layout.fragment_news_list_item, getApplicationContext()));
            }


            @Override
            public void onFailure(Call<NewsItem> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                no_internet_connection.setVisibility(View.VISIBLE);
                NoConnectionButton.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);//which is your mainActivity-Launcher
                        i.putExtra("Main_tab",4);
                        i.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);

                    }
                });

                call.cancel();
            }
        });


        refLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);
        refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                           @Override
                                           public void onRefresh() {
                                               // Checking if connected or not on refresh
                                               refLayout.setRefreshing(true);

                                               Call<NewsItem> call = apiInterface.doGetNewsArticleTeam(Team.toLowerCase());
                                               call.enqueue(new Callback<NewsItem>() {
                                                   @Override
                                                   public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {
                                                       mProgressBar.setVisibility(View.INVISIBLE);
                                                       no_internet_connection.setVisibility(View.INVISIBLE);
                                                           List<NewsItem> NewsListItem = response.body().getResults();
                                                           if (NewsListItem.size() == 0) {
                                                               No_news.setVisibility(View.VISIBLE);
                                                               NoNewsText.setText(R.string.EmptyNews);
                                                               NoNewsButton.setOnClickListener(new View.OnClickListener() {

                                                                   public void onClick(View v) {
                                                                       Intent i = new Intent(getApplicationContext(), MainActivity.class);//which is your mainActivity-Launcher
                                                                       i.putExtra("Main_tab",4);
                                                                       i.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                                                                       i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                                       startActivity(i);
                                                                   }
                                                               });
                                                           }
                                                           recyclerView.setVisibility(View.VISIBLE);
                                                           recyclerView.setAdapter(new NewsItemAdapter(NewsListItem, R.layout.fragment_news_list_item, getApplicationContext()));
                                                   }

                                                   @Override
                                                   public void onFailure(Call<NewsItem> call, Throwable t) {
                                                       mProgressBar.setVisibility(View.INVISIBLE);
                                                       Toast toast=Toast.makeText(getApplicationContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT);
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

