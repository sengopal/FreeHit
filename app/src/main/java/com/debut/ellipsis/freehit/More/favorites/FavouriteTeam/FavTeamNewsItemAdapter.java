package com.debut.ellipsis.freehit.More.favorites.FavouriteTeam;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.debut.ellipsis.freehit.News.NewsArticle;
import com.debut.ellipsis.freehit.R;

import java.util.List;



public class FavTeamNewsItemAdapter extends RecyclerView.Adapter<FavTeamNewsItemAdapter.NewsViewHolder> {

    private List<FavTeamNewsItem> newsItems;
    private int rowLayout;
    private Context context;


    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView desc;
        TextView date;
        TextView tag;
        RelativeLayout rlcontainer;


        public NewsViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image_view);
            title = (TextView) v.findViewById(R.id.header_text_view);
            desc = (TextView) v.findViewById(R.id.summary_text_view);
            date = (TextView) v.findViewById(R.id.news_date);
            tag = (TextView) v.findViewById(R.id.news_tag);
            rlcontainer = (RelativeLayout) v.findViewById(R.id.news_layout);
        }
    }

    public FavTeamNewsItemAdapter(List<FavTeamNewsItem> news, int rowLayout, Context context) {
        this.newsItems = news;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public FavTeamNewsItemAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View view = null;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        }
        return new FavTeamNewsItemAdapter.NewsViewHolder(view);

    }


    @Override
    public void onBindViewHolder(FavTeamNewsItemAdapter.NewsViewHolder holder, final int position) {
        holder.title.setText(newsItems.get(position).getTitle());
        holder.desc.setText(newsItems.get(position).getDesc());
        holder.date.setText(newsItems.get(position).getDate());
        holder.tag.setText(newsItems.get(position).getTag());
        Glide.with(context).load(newsItems.get(position).getImage()).apply(new RequestOptions().placeholder(R.drawable.matches).centerCrop()).into(holder.image);

        RelativeLayout RLcontainer = holder.rlcontainer;

        View.OnClickListener mClickListener;


        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent NewsArticleIntent = new Intent(context, NewsArticle.class);

                String pos = String.valueOf(position);
                NewsArticleIntent.putExtra("match_id", newsItems.get(position).getId().toString());
                    /*ActivityOptions.makeCustomAnimation(context,R.anim.animation_entry,R.anim.animation_exit);*/
                NewsArticleIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(NewsArticleIntent);

            }
        };
        RLcontainer.setOnClickListener(mClickListener);


    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }
}
