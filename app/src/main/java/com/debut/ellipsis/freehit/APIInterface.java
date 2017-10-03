package com.debut.ellipsis.freehit;

import com.debut.ellipsis.freehit.News.NewsArticleItem;
import com.debut.ellipsis.freehit.News.NewsItem;
import com.debut.ellipsis.freehit.Stats.Player.InfoItems;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("news")
    Call<NewsItem> doGetNewsListResources();

    @GET("news?")
    Call<NewsArticleItem> doGetNewsArticle(@Query("id") String id);
    @GET("playerbio")
    Call<InfoItems>doGetInfoResources();
}
