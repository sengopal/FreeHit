package com.debut.ellipsis.freehit;

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
    @GET("playerbio")
    Call<InfoItems>doGetInfoResources();




















































    @GET("upcoming?max=6")
    Call<UpcomingMatchCardItem> doGetUpcomingMatchListResources();

    @GET("upcoming?max=25")
    Call<UpcomingMatchCardItem> doGetUpcomingCompleteMatchListResources();

    @GET("live")
    Call<LiveMatchCardItem> doGetLiveMatchResources();

    @GET("polls")
    Call<PollCardItem> doGetPollsListResources();

    @GET("polls?")
    Call<PollCardItem> doVotePollListResources(@Query("id") String id, @Query("cid") String cid);

    @GET("polls?")
    Call<PollCardItem> doGetSinglePollResources(@Query("id") String id);



    @GET("past?max=6")
    Call<PastMatchCardItem> doGetPastCardResources();

    @GET("past?max=50")
    Call<PastMatchCardItem> doGetCompletePastCardResources();

    @GET("news?")
    Call<NewsItem> doGetNewsArticleTeam(@Query("fav") String team);






}
