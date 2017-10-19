package com.debut.ellipsis.freehit.News;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.DecodeFormat;
import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.Glide.GlideApp;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsArticle extends AppCompatActivity {

    private Toolbar toolbar;
    APIInterface apiInterface;
    private ProgressBar mProgressBar;
    private String match_id;

    public NewsArticle() {
        // Required empty public constructor
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news_article_individual_item);
        match_id = getIntent().getStringExtra("news_id");
        apiInterface = ApiClient.getClient().create(APIInterface.class);

        View viewProgress = findViewById(R.id.progress);
        mProgressBar = (ProgressBar) viewProgress.findViewById(R.id.progress_bar);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        Call<NewsArticleItem> call = apiInterface.doGetNewsArticle(match_id);
        call.enqueue(new Callback<NewsArticleItem>() {
            @Override
            public void onResponse(Call<NewsArticleItem> call, Response<NewsArticleItem> response) {

                NewsArticleItem newsArticle = response.body();

                TextView headline = (TextView) findViewById(R.id.news_article_heading);
                headline.setText(newsArticle.getTitle());

                TextView article_description = (TextView) findViewById(R.id.news_article_description);
                article_description.setText(newsArticle.getDesc());

                TextView date = (TextView) findViewById(R.id.news_date);
                date.setText(newsArticle.getDate());

                TextView news_tag_1 = (TextView) findViewById(R.id.news_tag);
                news_tag_1.setText(newsArticle.getTag1());

                TextView news_tag_2 = (TextView) findViewById(R.id.news_tag_1);
                news_tag_2.setText(newsArticle.getTag2());

                TextView news_tag_3 = (TextView) findViewById(R.id.news_tag_2);
                news_tag_3.setText(newsArticle.getTag3());

                ImageView tag_1 = (ImageView) findViewById(R.id.news_tag_image);
                tag_1.setVisibility(View.VISIBLE);

                ImageView tag_2 = (ImageView) findViewById(R.id.news_tag_image_1);
                tag_2.setVisibility(View.VISIBLE);

                ImageView tag_3 = (ImageView) findViewById(R.id.news_tag_image_2);
                tag_3.setVisibility(View.VISIBLE);

                mProgressBar.setVisibility(View.GONE);

                final ImageView articleImage = (ImageView) findViewById(R.id.news_article_image);

                final String ImageURL = newsArticle.getImage();

                MainActivity.requestBuilder = GlideApp.with(getBaseContext()).load(ImageURL).placeholder(R.drawable.matches).format(DecodeFormat.PREFER_RGB_565);

                MainActivity.requestBuilder.into(articleImage);

            }

            @Override
            public void onFailure(Call<NewsArticleItem> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });


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
        NewsArticle.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);

    }

}