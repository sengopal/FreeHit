package com.debut.ellipsis.freehit.Home;


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
import com.debut.ellipsis.freehit.News.NewsArticle;
import com.debut.ellipsis.freehit.News.NewsItem;
import com.debut.ellipsis.freehit.R;

import java.util.List;

public class HomeNewsItemAdapter extends RecyclerView.Adapter<HomeNewsItemAdapter.NewsViewHolder> {

    private List<NewsItem> newsItems;
    private int rowLayout;
    private Context context;


    public class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView desc;
        TextView date;
        TextView tag;
        ImageView news_tag;
        RelativeLayout rlcontainer;
        CardView cardView;


        public NewsViewHolder(View v) {
            super(v);
            image = v.findViewById(R.id.image_view);
            title = v.findViewById(R.id.header_text_view);
            desc = v.findViewById(R.id.summary_text_view);
            date = v.findViewById(R.id.news_date);
            news_tag = v.findViewById(R.id.news_tag_image);
            tag = v.findViewById(R.id.news_tag);
            cardView = v.findViewById(R.id.card_view);
            rlcontainer = v.findViewById(R.id.news_layout);
        }
    }

    public HomeNewsItemAdapter(List<NewsItem> news, int rowLayout, Context context) {
        this.newsItems = news;
        this.rowLayout = rowLayout;
        this.context = context;
    }


    @Override
    public HomeNewsItemAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        }
        return new HomeNewsItemAdapter.NewsViewHolder(view);

    }


    @Override
    public void onBindViewHolder(HomeNewsItemAdapter.NewsViewHolder holder, int position) {
        holder.title.setText(newsItems.get(position).getTitle());
        holder.desc.setText(newsItems.get(position).getDesc());
        holder.date.setText(newsItems.get(position).getDate());
        if (newsItems.get(position).getTag().equals(" ")) {
            holder.tag.setVisibility(View.GONE);
            holder.news_tag.setVisibility(View.GONE);
        } else {
            holder.tag.setVisibility(View.VISIBLE);
            holder.news_tag.setVisibility(View.VISIBLE);
            holder.tag.setText(newsItems.get(position).getTag());
        }
        String URLNewsImage = newsItems.get(position).getImage();

        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_YES:
                holder.cardView.setBackgroundColor(Color.parseColor("#33343b"));
                holder.title.setTextColor(Color.WHITE);
                holder.desc.setTextColor(Color.WHITE);
                holder.news_tag.setColorFilter(Color.WHITE);
                MainActivity.requestBuilder = GlideApp.with(context).load(URLNewsImage).placeholder(R.drawable.placeholder_dark).format(DecodeFormat.PREFER_RGB_565);
                break;
            default:
                MainActivity.requestBuilder = GlideApp.with(context).load(URLNewsImage).placeholder(R.drawable.placeholder_light).format(DecodeFormat.PREFER_RGB_565);
                break;
        }


        MainActivity.requestBuilder.into(holder.image);

        RelativeLayout RLContainer = holder.rlcontainer;

        View.OnClickListener mClickListener;

        final int newsArticlePosition = position;
        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent NewsArticleIntent = new Intent(context, NewsArticle.class);

                NewsArticleIntent.putExtra("news_id", newsItems.get(newsArticlePosition).getId().toString());
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