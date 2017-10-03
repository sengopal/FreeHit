package com.debut.ellipsis.freehit.News;

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
import com.debut.ellipsis.freehit.R;

import java.util.List;


public class NewsItemAdapter extends RecyclerView.Adapter<com.debut.ellipsis.freehit.News.NewsItemAdapter.NewsViewHolder> {

    private List<NewsItem> newsItems;
    private int rowLayout;
    private Context context;


    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout newsLayout;
        ImageView image;
        TextView title;
        TextView desc;
        TextView date;
        TextView tag;
        RelativeLayout rlcontainer;


        public NewsViewHolder(View v) {
            super(v);
            newsLayout = (RelativeLayout) v.findViewById(R.id.news_layout);
            image = (ImageView) v.findViewById(R.id.image_view);
            title = (TextView) v.findViewById(R.id.header_text_view);
            desc = (TextView) v.findViewById(R.id.summary_text_view);
            date = (TextView) v.findViewById(R.id.news_date);
            tag = (TextView) v.findViewById(R.id.news_tag);
            rlcontainer = (RelativeLayout) v.findViewById(R.id.news_layout);
        }
    }

    public NewsItemAdapter(List<NewsItem> news, int rowLayout, Context context) {
        this.newsItems = news;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public com.debut.ellipsis.freehit.News.NewsItemAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                                              int viewType) {
        View view = null;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        }
        return new com.debut.ellipsis.freehit.News.NewsItemAdapter.NewsViewHolder(view);

    }


    @Override
    public void onBindViewHolder(com.debut.ellipsis.freehit.News.NewsItemAdapter.NewsViewHolder holder, final int position) {
        holder.title.setText(newsItems.get(position).getTitle());
        holder.desc.setText(newsItems.get(position).getDesc());
        holder.date.setText(newsItems.get(position).getDate());
        holder.tag.setText(newsItems.get(position).getTag());
        Glide.with(context).load(newsItems.get(position).getImage()).centerCrop().placeholder(R.drawable.matches).into(holder.image);

        RelativeLayout RLcontainer = holder.rlcontainer;

        View.OnClickListener mClickListener;


        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent UpcomingMatchScoreCardIntent = new Intent(context, NewsArticle.class);

                String pos = String.valueOf(position);
                UpcomingMatchScoreCardIntent.putExtra("match_id", newsItems.get(position).getId().toString());
                    /*ActivityOptions.makeCustomAnimation(context,R.anim.animation_entry,R.anim.animation_exit);*/
                UpcomingMatchScoreCardIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(UpcomingMatchScoreCardIntent);

            }
        };
        RLcontainer.setOnClickListener(mClickListener);


    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }
}