package com.debut.ellipsis.freehit.News;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.DecodeFormat;
import com.debut.ellipsis.freehit.Glide.GlideApp;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;

import java.util.List;


public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.NewsViewHolder> {

    private List<NewsItem> newsItems;
    private int rowLayout;
    private Context context;


    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView desc;
        TextView date;
        TextView tag;
        RelativeLayout rlcontainer;
        CardView cardView;


        public NewsViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image_view);
            title = (TextView) v.findViewById(R.id.header_text_view);
            desc = (TextView) v.findViewById(R.id.summary_text_view);
            date = (TextView) v.findViewById(R.id.news_date);
            tag = (TextView) v.findViewById(R.id.news_tag);
            cardView = (CardView) v.findViewById(R.id.card_view);
            rlcontainer = (RelativeLayout) v.findViewById(R.id.news_layout);
        }
    }

    public NewsItemAdapter(List<NewsItem> news, int rowLayout, Context context) {
        this.newsItems = news;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public NewsItemAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        }
        return new NewsItemAdapter.NewsViewHolder(view);

    }


    @Override
    public void onBindViewHolder(NewsItemAdapter.NewsViewHolder holder, final int position) {
        holder.title.setText(newsItems.get(position).getTitle());
        holder.desc.setText(newsItems.get(position).getDesc());
        holder.date.setText(newsItems.get(position).getDate());
        holder.tag.setText(newsItems.get(position).getTag());

        String URLNewsImage = newsItems.get(position).getImage();

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            holder.cardView.setBackgroundColor(Color.parseColor("#484a4f"));
            holder.title.setTextColor(Color.WHITE);
            holder.desc.setTextColor(Color.WHITE);
            MainActivity.requestBuilder = GlideApp.with(context).load(URLNewsImage).placeholder(R.drawable.placeholder_dark).format(DecodeFormat.PREFER_RGB_565);
        }
        else
        {
            MainActivity.requestBuilder = GlideApp.with(context).load(URLNewsImage).placeholder(R.drawable.placeholder_light).format(DecodeFormat.PREFER_RGB_565);

        }


        MainActivity.requestBuilder.into(holder.image);

        RelativeLayout RLContainer = holder.rlcontainer;

        View.OnClickListener mClickListener;


        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent NewsArticleIntent = new Intent(context, NewsArticle.class);

                NewsArticleIntent.putExtra("news_id", newsItems.get(position).getId().toString());
                NewsArticleIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(NewsArticleIntent);

            }
        };
        RLContainer.setOnClickListener(mClickListener);



    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }
}