package com.debut.ellipsis.freehit;

import com.debut.ellipsis.freehit.Home.RecentItem;
import com.debut.ellipsis.freehit.Matches.LiveMatches.LiveMatchCardItem;
import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchCardItem;
import com.debut.ellipsis.freehit.Matches.ScoreCard.CommentaryElements.CommentaryItem;
import com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardItem;
import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchCardItem;
import com.debut.ellipsis.freehit.More.Player.PlayerItem;
import com.debut.ellipsis.freehit.More.Rankings.RankingItem;
import com.debut.ellipsis.freehit.More.Series.PerformanceItem;
import com.debut.ellipsis.freehit.More.Series.SeriesItem;
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
    Call<PlayerItem> doGetPlayerInfo(@Query("url") String url);

    @GET("upcoming?max=6")
    Call<UpcomingMatchCardItem> doGetUpcomingMatchListResources();

    @GET("upcoming")
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

    @GET("past")
    Call<PastMatchCardItem> doGetCompletePastCardResources();

    @GET("news?")
    Call<NewsItem> doGetNewsArticleTeam(@Query("fav") String team);

    @GET("upcoming?")
    Call<UpcomingMatchCardItem> doGetUpcomingFavTeam(@Query("fav") String team);

    @GET("past?")
    Call<PastMatchCardItem> doGetPastFavTeam(@Query("fav") String team);

    @GET("country")
    Call<CountryItem> doGetCountryResources();

    @GET("player?")
    Call<PlayerCountryItem> doGetFavTeamPlayers(@Query ("fav") String Team);

    @GET("player?")
    Call<PlayerCountryItem> doGetTeamPlayers(@Query("fav") String PlayerName);

    @GET("series")
    Call<SeriesItem> doGetSeries();

    @GET("past?")
    Call<PastMatchCardItem> doGetPastSeriesMatches(@Query("sfav") String team1, @Query("date") String date);

    @GET("upcoming?")
    Call<UpcomingMatchCardItem> doGetUpComingSeriesMatches(@Query("sfav") String team1,@Query("date") String date);

    @GET("scorecard?")
    Call<ScoreCardItem> doGetMatchScoreCard(@Query("ndid") String match_id);

    @GET("scorecard?")
    Call<ScoreCardItem> doGetMatchScoreCardCache(@Query("ndid") String match_id,@Query("cache") String cahceCall);

    @GET("scorecardlive?")
    Call<ScoreCardItem> doGetLiveMatchScoreCard(@Query("ndid") String match_id);

    @GET("scorecardlive?")
    Call<ScoreCardItem> doGetLiveMatchScoreCardCache(@Query("ndid") String match_id,@Query("cache") String cacheCall);

    @GET("ranking")
    Call<RankingItem> doGetRankingResources();

    @GET("ranking?")
    Call<RankingItem> doGetRankingCache(@Query("cache") String cacheCall);

    @GET("series?")
    Call<PerformanceItem> doGetSeriesPerformance(@Query("id") String id);

    @GET("comm?")
    Call<CommentaryItem> doGetLiveMatchCommentary(@Query("ndid") String match_id );

    @GET("home")
    Call<RecentItem> doGetRecentResources();

    @GET("news?")
    Call<NewsItem> doGetHomeFavNews(@Query("max") int value,@Query("fav") String favTeam);

    @GET("news?max=5")
    Call<NewsItem> doGetHomeNews();


}