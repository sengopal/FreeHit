package com.debut.ellipsis.freehit;

import com.debut.ellipsis.freehit.Matches.LiveMatches.LiveMatchCardItem;
import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchCardItem;
import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchCardItem;
import com.debut.ellipsis.freehit.More.Player.BattingItem;
import com.debut.ellipsis.freehit.More.Player.BowlingItem;
import com.debut.ellipsis.freehit.More.Player.CareerItem;
import com.debut.ellipsis.freehit.More.Player.InfoItem;
import com.debut.ellipsis.freehit.More.Series.SeriesItem;
import com.debut.ellipsis.freehit.More.favorites.FavouriteTeam.FavTeamNewsItem;
import com.debut.ellipsis.freehit.News.NewsArticleItem;
import com.debut.ellipsis.freehit.News.NewsItem;
import com.debut.ellipsis.freehit.Social.Polls.PollCardItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("news")
    Call<NewsItem> doGetNewsListResources();

    @GET("news?")
    Call<NewsArticleItem> doGetNewsArticle(@Query("id") String id);

    @GET("playerbio?")
    Call<InfoItem> doGetInfoResources(@Query("url") String url);

    @GET("playerbio?")
    Call<BattingItem> doGetBattingInfo(@Query("url") String url);

    @GET("playerbio?")
    Call<BowlingItem> doGetBowlingInfo(@Query("url") String url);

    @GET("playerbio?")
    Call<CareerItem> doGetCareerInfo(@Query("url") String url);

    @GET("upcoming?max=6")
    Call<UpcomingMatchCardItem> doGetUpcomingMatchListResources();

    @GET("upcoming?max=25")
    Call<UpcomingMatchCardItem> doGetUpcomingCompleteMatchListResources();

    @GET("playerlist?")
    Call<PlayerCountryItem> doGetPlayerList(@Query("fav") String search);

    @GET("live")
    Call<LiveMatchCardItem> doGetLiveMatchResources();

    @GET("polls")
    Call<PollCardItem> doGetPollsListResources();

    @GET("polls?")
    Call<PollCardItem> doVotePollListResources(@Query("id") String id, @Query("cid") String cid);

    @GET("past?max=6")
    Call<PastMatchCardItem> doGetPastCardResources();

    @GET("past?max=25")
    Call<PastMatchCardItem> doGetCompletePastCardResources();

    @GET("news?")
    Call<FavTeamNewsItem> doGetNewsArticleTeam(@Query("fav") String team);

    @GET("upcoming?")
    Call<UpcomingMatchCardItem> doGetUpcomingFavTeam(@Query("fav") String team);

    @GET("past?")
    Call<PastMatchCardItem> doGetPastFavTeam(@Query("fav") String team);

    @GET("country")
    Call<CountryItem> doGetCountryResources();

    @GET("player?")
    Call<PlayerCountryItem> doGetFavTeamPlayers(@Query ("id") String TeamID);

    @GET("series")
    Call<SeriesItem> doGetSeries();

    @GET("past?")
    Call<PastMatchCardItem> doGetPastSeriesMatches(@Query("sfav") String team1, @Query("date") String date);

    @GET("upcoming?")
    Call<UpcomingMatchCardItem> doGetUpComingSeriesMatches(@Query("sfav") String team1,@Query("date") String date);


}
