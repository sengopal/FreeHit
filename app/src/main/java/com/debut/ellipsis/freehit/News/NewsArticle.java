package com.debut.ellipsis.freehit.News;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class NewsArticle extends AppCompatActivity {

    private Toolbar toolbar;
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

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);

        MainActivity.apiInterface = ApiClient.getClient().create(APIInterface.class);

        View viewProgress = findViewById(R.id.progress);
        mProgressBar = viewProgress.findViewById(R.id.progress_bar);

        NestedScrollView scrollView = findViewById(R.id.nested_scroll_view);

        LinearLayout linearLayout = findViewById(R.id.description);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            linearLayout.setBackgroundColor(Color.parseColor("#484a4f"));
            scrollView.setBackgroundColor(Color.parseColor("#484a4f"));
            collapsingToolbarLayout.setBackgroundColor(getResources().getColor(R.color.dark));
            collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.dark));
            Window window = getWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.BLACK);
            }
        }


        Call<NewsArticleItem> call = MainActivity.apiInterface.doGetNewsArticle(match_id);
        call.enqueue(new Callback<NewsArticleItem>() {
            @Override
            public void onResponse(Call<NewsArticleItem> call, Response<NewsArticleItem> response) {
                if (response.isSuccessful()) {
                    NewsArticleItem newsArticle = response.body();

                    TextView headline = findViewById(R.id.news_article_heading);
                    headline.setText(newsArticle.getTitle());

                    TextView article_description = findViewById(R.id.news_article_description);
                    article_description.setText(newsArticle.getDesc());

                    TextView date = findViewById(R.id.news_date);
                    date.setText(newsArticle.getDate());

                    mProgressBar.setVisibility(View.GONE);

                    final ImageView articleImage = findViewById(R.id.news_article_image);

                    final String ImageURL = newsArticle.getImage();

                    if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                        headline.setTextColor(Color.WHITE);
                        article_description.setTextColor(Color.WHITE);
                        date.setTextColor(Color.WHITE);
                    }

                    MainActivity.requestBuilder = GlideApp.with(getBaseContext()).load(ImageURL).format(DecodeFormat.PREFER_RGB_565);

                    MainActivity.requestBuilder.into(articleImage);

                } else {
                    mProgressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsArticleItem> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
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