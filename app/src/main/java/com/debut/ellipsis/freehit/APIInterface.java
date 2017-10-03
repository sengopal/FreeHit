package com.debut.ellipsis.freehit;

import com.debut.ellipsis.freehit.Matches.LiveMatches.LiveMatchCardItem;
import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchCardItem;
import com.debut.ellipsis.freehit.News.NewsArticleItem;
import com.debut.ellipsis.freehit.News.NewsItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("news")
    Call<NewsItem> doGetNewsListResources();

    @GET("news?")
    Call<NewsArticleItem> doGetNewsArticle(@Query("id") String id);




















































    @GET("upcoming?max=6")
    Call<UpcomingMatchCardItem> doGetUpcomingMatchListResources();

    @GET("upcoming?max=25")
    Call<UpcomingMatchCardItem> doGetUpcomingCompleteMatchListResources();

    @GET("live")
    Call<LiveMatchCardItem> doGetLiveMatchResources();


}
